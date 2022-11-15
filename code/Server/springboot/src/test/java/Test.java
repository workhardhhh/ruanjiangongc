import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String a = "102192113";
        String b = "a582540851";
        try {
            String[] args1 = new String[] { "python", "C:\\Users\\ouyang\\Test1.py", a, b };
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gb2312"));
            String line = null;
            StringBuffer buf = new StringBuffer();
            while ((line = in.readLine()) != null) {
                System.out.println(line + "\n");
                buf.append(line);
            }

            JSONObject json=(JSONObject) JSONObject.toJSON(JSON.parse(buf.toString()));

          //  System.out.println(json.get("1"));
            System.out.println(json);

            JSONObject garbages = json.getJSONObject("parsel_data");
            for (int i = 0;i < 11;i++)
            {
                System.out.println("星期一" + garbages.getJSONArray("星期一").get(i));
                System.out.println("星期二" + garbages.getJSONArray("星期二").get(i));
                System.out.println("星期三" + garbages.getJSONArray("星期三").get(i));
                System.out.println("星期四" + garbages.getJSONArray("星期四").get(i));
                System.out.println("星期五" + garbages.getJSONArray("星期五").get(i));
                System.out.println("星期六" + garbages.getJSONArray("星期六").get(i));
                System.out.println("星期日" + garbages.getJSONArray("星期日").get(i));

            }






            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
