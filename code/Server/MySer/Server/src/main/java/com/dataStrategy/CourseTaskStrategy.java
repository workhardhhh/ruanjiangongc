package com.dataStrategy;

import com.Interface.DataStrategy;
import com.mybatis.entity.CourseTask;
import com.mybatis.mapper.CourseTaskMapper;
import com.tool.Constant;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class CourseTaskStrategy implements DataStrategy<CourseTask> {
    public List<CourseTask> findAll(CourseTask courses) throws Exception {
        //1、读取配置文件
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        //5、使用SqlSession 创建 dao接口的代理对象
        CourseTaskMapper mapper = session.getMapper(CourseTaskMapper.class);
        //6、使用代理对象执行查询所有的方法
        List<CourseTask> all = mapper.findAll(courses);
        for (CourseTask course : all) {
            System.out.println(course);
        }
        //7、释放资源
        session.close();
        in.close();
        return all;
    }

    public void insert(CourseTask courses) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CourseTaskMapper mapper = session.getMapper(CourseTaskMapper.class);
        mapper.insert(courses);
        session.commit();
        session.close();
        in.close();
    }

    public void update(CourseTask courses) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CourseTaskMapper mapper = session.getMapper(CourseTaskMapper.class);
        mapper.update(courses);
        session.commit();
        session.close();
        in.close();
    }

    public void delete(CourseTask Sno) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CourseTaskMapper mapper = session.getMapper(CourseTaskMapper.class);
        mapper.delete(Sno);
        session.commit();
        session.close();
        in.close();
    }

    public void createTable(CourseTask TableName) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CourseTaskMapper mapper = session.getMapper(CourseTaskMapper.class);
        mapper.createTable(TableName);
        session.commit();
        session.close();
        in.close();
    }

    public void deleteTable(CourseTask TableName) throws Exception {
        InputStream in = Resources.getResourceAsStream(Constant.SQLXML);
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CourseTaskMapper mapper = session.getMapper(CourseTaskMapper.class);
        mapper.deleteTable(TableName);
        session.commit();
        session.close();
        in.close();
    }
}
