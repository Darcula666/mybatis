package com.gfdz.mybatis;

import com.gfdz.mybatis.dao.EmployeeMapper;
import com.gfdz.mybatis.entity.Employee;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 查询
     *
     * @throws IOException
     */
    @Test
    public void MybatisTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        try {
            Employee employee = mapper.getEmpById(1);
        } finally {
            openSession.close();
        }

    }

    /**
     * 添加
     *
     * @throws IOException
     */
    @Test
    public void MybatisTest2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        try {
            mapper.addEmp(new Employee(null, "dajiji", "dajiji@google.com", "1"));
            openSession.commit();
        } finally {
            openSession.close();
        }

    }

    /**
     * 修改
     *
     * @throws IOException
     */
    @Test
    public void MybatisTest3() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        try {
            mapper.updateEmp(new Employee(1, "xiaojiji", "xiaojiji@google.com", "1"));
            openSession.commit();
        } finally {
            openSession.close();
        }

    }

    /**
     * 删除
     *
     * @throws IOException
     */
    @Test
    public void MybatisTest4() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        try {
            mapper.deleteEmpById(1);
            openSession.commit();
        } finally {
            openSession.close();
        }

    }
}
