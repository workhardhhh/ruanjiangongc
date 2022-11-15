import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.logic.LoginLogic;
import com.mysql.cj.log.Log;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
@MapperScan("com.app.mapper")

public class Tess {

    public static void main(String[] args) throws IOException {


        BufferedReader in = new BufferedReader(new FileReader("课表.json"));
        String line = null;
        StringBuffer buf = new StringBuffer();
        while ((line = in.readLine()) != null) {
            System.out.println(line + "\n");
            buf.append(line);
        }
        JSONObject json=(JSONObject) JSONObject.toJSON(JSON.parse(buf.toString()));

        //  System.out.println(json.get("1"));
        System.out.println(json);
        JSONObject course_data = json.getJSONObject("parsel_data");

        LoginLogic loginLogic = new LoginLogic();
        loginLogic.JsonParse(course_data, "102192113");
        System.out.println(course_data.toString());



        in.close();


    }
}
