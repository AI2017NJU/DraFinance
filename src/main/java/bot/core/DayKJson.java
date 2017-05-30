package bot.core;

import model.DayK;
import org.json.JSONArray;
import org.json.JSONML;
import org.json.JSONObject;

import javax.json.Json;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by stonezhang on 2017/5/29.
 */
public class DayKJson {
    public static List<DayK> demo(String url) {
        List<DayK> result=  new ArrayList<>();

        String jsonContent = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();

            //   "Accept": "application/json, text/javascript, */*; q=0.01",
//            "Accept-Encoding": "gzip, deflate, sdch, br",
//                    "Accept-Language": "zh-CN,zh;q=0.8,en;q=0.6,en-US;q=0.4",
//                    "Cache-Control": "no-cache",
//                    "Connection": "keep-alive",
//                    "Cookie": cookies[0],
//                    "host": "xueqiu.com",
//                    "Pragma": "no-cache",
//                    "Referer": "https://xueqiu.com/hq",
//                    "User-Agent": random.choice(UA),
//                    "X-Requested-With": "XMLHttpRequest"

            connection.addRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            connection.addRequestProperty("Accept-Encoding", "gzip, deflate, sdch, br");
            connection.addRequestProperty("Cache-Control", "no-cache");
            connection.addRequestProperty("Connection", "keep-alive");
            connection.addRequestProperty("Cookie", "aliyungf_tc=AQAAADvGpmLaDgsAb1Gi0/g/rjOa/iJj; s=fv120q5i4b; " +
                    "xq_a_token=876f2519b10cea9dc131b87db2e5318e5d4ea64f; " +
                    "xq_r_token=709abdc1ccb40ac956166989385ffd603ad6ab6f; " +
                    "u=651495873728701; __utma=1.335231814.1495873729.1495956256.1496024223.6; " +
                    "__utmc=1; __utmz=1.1495873729.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); " +
                    "Hm_lvt_1db88642e346389874251b5a1eded6e3=1495873729; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1496029396");
            connection.addRequestProperty("host", "xueqiu.com");
            connection.addRequestProperty("Pragma", "no-cache");
            connection.addRequestProperty("Referer", "https://xueqiu.com/hq");
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
            connection.addRequestProperty("X-Requested-With", "XMLHttpRequest");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                jsonContent += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //得到的json数据
        try {
            jsonContent = new String(jsonContent.getBytes("ascii"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonContent);
        return result;
        //解析,
//        JSONObject jsonObj = JSON.parseObject(result);
//        JSONArray jarr =  jsonObj.getJSONArray("data");
//        JSONObject j0 = (JSONObject)jarr.get(0);
//        //输出该ip对应的地理位置
//        System.out.println(j0.get("location"));
    }
}
