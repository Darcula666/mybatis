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
import java.util.List;

public class MybatisTest {
    /**
     * 1.根据xml配置文件（全局配置文件）创建一个SqlSessionFactory
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
    public  void testAdd() throws IOException {
        //2.获取sqlSession实例，能直接执行已经映射的sql语句

        SqlSessionFactory sessionFactory=  getSqlSessionFactory();
        SqlSession sqlSession=getSqlSessionFactory().openSession();
        try {
            //sql唯一标识：statement
            //执行sql要用的参数
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getById(1);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }

    }
    @Test
    public  void testSelect() throws IOException {
      SqlSessionFactory sessionFactory=  getSqlSessionFactory();
        SqlSession sqlSession=getSqlSessionFactory().openSession();
    }
}
