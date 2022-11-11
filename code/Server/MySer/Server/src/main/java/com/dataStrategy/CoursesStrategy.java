package com.dataStrategy;

import com.Interface.DataStrategy;
import com.mybatis.entity.Courses;
import com.mybatis.mapper.CoursesMapper;
import com.tool.Constant;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class CoursesStrategy implements DataStrategy<Courses> {



    public List<Courses> findAll(Courses courses) throws Exception {

        //1、读取配置文件
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        //5、使用SqlSession 创建 dao接口的代理对象
        CoursesMapper mapper = session.getMapper(CoursesMapper.class);
        //6、使用代理对象执行查询所有的方法
        List<Courses> all = mapper.findAll(courses);
        for (Courses course : all) {
            System.out.println(course);
        }
        //7、释放资源
        session.close();
        in.close();
        return all;
    }

    public void insert(Courses courses) throws Exception {

        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CoursesMapper mapper = session.getMapper(CoursesMapper.class);
        mapper.insert(courses);
        session.commit();
        session.close();
        in.close();
    }

    public void update(Courses courses) throws Exception {

        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CoursesMapper mapper = session.getMapper(CoursesMapper.class);
        mapper.update(courses);
        session.commit();
        session.close();
        in.close();
    }

    public void delete(Courses Sno) throws Exception {

        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CoursesMapper mapper = session.getMapper(CoursesMapper.class);
        mapper.delete(Sno);
        session.commit();
        session.close();
        in.close();

    }

    public void createTable(Courses TableName) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CoursesMapper mapper = session.getMapper(CoursesMapper.class);
        mapper.createTable(TableName);
        session.commit();
        session.close();
        in.close();
    }

    public void deleteTable(Courses TableName) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CoursesMapper mapper = session.getMapper(CoursesMapper.class);
        mapper.deleteTable(TableName);
        session.commit();
        session.close();
        in.close();
    }


}
