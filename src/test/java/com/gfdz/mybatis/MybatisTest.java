package com.gfdz.mybatis;

import com.gfdz.mybatis.dao.EmployeeMapper;
import com.gfdz.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {
    /**
     * 1.根据xml配置文件（全局配置文件）创建一个SqlSessionFactory
     *
     * @return
     * @throws IOException
     */
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testMBG() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/MBG.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    public void testSelect() throws IOException {
        //2.获取sqlSession实例，能直接执行已经映射的sql语句
        //获取到的sqlSession不会自动提交数据
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            //sql唯一标识：statement
            //执行sql要用的参数
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getById(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }
    @Test
    public void testSelect02() throws IOException {
        //2.获取sqlSession实例，能直接执行已经映射的sql语句
        //获取到的sqlSession不会自动提交数据
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            //sql唯一标识：statement
            //执行sql要用的参数
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpByIdAndLastName(2, "timo");
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void testSelect03() throws IOException {
        //2.获取sqlSession实例，能直接执行已经映射的sql语句
        //获取到的sqlSession不会自动提交数据
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            //sql唯一标识：statement
            //执行sql要用的参数
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 3);
            map.put("lastName", "jetty");
            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }
    @Test
    public void testSelect04() throws IOException {
        //2.获取sqlSession实例，能直接执行已经映射的sql语句
        //获取到的sqlSession不会自动提交数据
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            //sql唯一标识：statement
            //执行sql要用的参数
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
           List<Employee> employeeList=mapper.getEmpsByLastNameLike("%e%");
           for (Employee employee:employeeList){
               System.out.println(employee);
           }
        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void testSelect05() throws IOException {
        //2.获取sqlSession实例，能直接执行已经映射的sql语句
        //获取到的sqlSession不会自动提交数据
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            //sql唯一标识：statement
            //执行sql要用的参数
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map map=mapper.getEmpByIdReturnMap(2);
            System.out.println(map.get("lastName"));
        } finally {
            sqlSession.close();
        }

    }


    @Test
    public void testSelect06() throws IOException {
        //2.获取sqlSession实例，能直接执行已经映射的sql语句
        //获取到的sqlSession不会自动提交数据
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            //sql唯一标识：statement
            //执行sql要用的参数
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<Integer,Employee> map=mapper.getEmpByLastNameLikeReturnMap("%e%");
            System.out.println(map);
        } finally {
            sqlSession.close();
        }

    }
    @Test
    public void testSelect07() throws IOException {
        //2.获取sqlSession实例，能直接执行已经映射的sql语句
        //获取到的sqlSession不会自动提交数据
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            //sql唯一标识：statement
            //执行sql要用的参数
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee empAndDept = mapper.getEmpAndDeptById(2);
            System.out.println(empAndDept);
            System.out.println(empAndDept.getDepartment());
        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void testAdd() throws IOException {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null, "jetty01", "tk@qq.com", "1");
            mapper.addEmp(employee);
            System.out.println(employee.getId());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testUpdate() throws IOException {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(1, "jetty", "jetty@qq.com", "0");
            mapper.updateEmp(employee);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testDelete() throws IOException {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.deleteEmpById(1);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
