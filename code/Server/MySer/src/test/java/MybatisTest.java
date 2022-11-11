import com.CouFactory.CoursesFactory;
import com.Interface.DataStrategy;
import com.dataStrategy.CoursesStrategy;
import com.dataStrategy.StrategyContext;
import com.mybatis.entity.Courses;
import com.tool.Constant;

import java.io.Serializable;

public class MybatisTest {

    public static void main(String[] args) throws Exception {
        StrategyContext<Courses> strategyContext = new StrategyContext();
        DataStrategy coursesStrategy = new CoursesStrategy();
        strategyContext.setContext(coursesStrategy);
        CoursesFactory coursesFactory = new CoursesFactory();
        Courses courses = coursesFactory.getCourses(6, "高等小白", "李白", "102192113", "王维", "Courses");
        strategyContext.setSql(courses);
        strategyContext.executeSql(Constant.INSERT, coursesStrategy);

    }
}
