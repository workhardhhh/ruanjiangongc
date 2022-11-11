package com.dataStrategy;

import com.Interface.DataStrategy;
import com.mybatis.entity.CourseStudent;
import com.mybatis.mapper.CourseStudentMapper;
import com.tool.Constant;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class CourseStudentStrategy implements DataStrategy<CourseStudent> {
    public List<CourseStudent> findAll(CourseStudent courses) throws Exception {
        //1、读取配置文件
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        //5、使用SqlSession 创建 dao接口的代理对象
        CourseStudentMapper mapper = session.getMapper(CourseStudentMapper.class);
        //6、使用代理对象执行查询所有的方法
        List<CourseStudent> all = mapper.findAll(courses);
        for (CourseStudent course : all) {
            System.out.println(course);
        }
        //7、释放资源
        session.close();
        in.close();
        return all;
    }

    public void insert(CourseStudent courses) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CourseStudentMapper mapper = session.getMapper(CourseStudentMapper.class);
        mapper.insert(courses);
        session.commit();
        session.close();
        in.close();
    }

    public void update(CourseStudent courses) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CourseStudentMapper mapper = session.getMapper(CourseStudentMapper.class);
        mapper.update(courses);
        session.commit();
        session.close();
        in.close();
    }

    public void delete(CourseStudent Sno) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CourseStudentMapper mapper = session.getMapper(CourseStudentMapper.class);
        mapper.delete(Sno);
        session.commit();
        session.close();
        in.close();
    }

    public void createTable(CourseStudent TableName) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CourseStudentMapper mapper = session.getMapper(CourseStudentMapper.class);
        mapper.createTable(TableName);
        session.commit();
        session.close();
        in.close();
    }

    public void deleteTable(CourseStudent TableName) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CourseStudentMapper mapper = session.getMapper(CourseStudentMapper.class);
        mapper.deleteTable(TableName);
        session.commit();
        session.close();
        in.close();
    }
}
