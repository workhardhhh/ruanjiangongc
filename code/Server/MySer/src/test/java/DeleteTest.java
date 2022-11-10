import com.mybatis.entity.Courses;
import com.mybatis.mapper.CoursesMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DeleteTest {
    public static void main(String[] args) throws IOException {
        Courses courses = new Courses();
        courses.setSno("111111111");
        courses.setSname("李白");
        courses.setCname("高等数学");
        courses.setTname("王维");
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CoursesMapper mapper = session.getMapper(CoursesMapper.class);
        mapper.delete(courses);
        session.commit();
        session.close();
        in.close();
    }
}
