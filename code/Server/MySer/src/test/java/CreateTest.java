import com.mybatis.entity.CourseTask;
import com.mybatis.mapper.CourseTaskMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class CreateTest {
    public static void main(String[] args) throws IOException {
        CourseTask task = new CourseTask();
        task.setTaskId("1");
        task.setContent("啥也不是");
        task.setTime(new Date());
        task.setType("无类别");
        task.setTableName("李白高等数学");

        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、创建 SqlSessionFactoryBuilder 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、使用构建者模式创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4、使用SqlSessionFactory 生产 SqlSession
        SqlSession session = factory.openSession();
        CourseTaskMapper mapper = session.getMapper(CourseTaskMapper.class);
        mapper.createTable(task);
        session.commit();
        session.close();
        in.close();
    }
}
