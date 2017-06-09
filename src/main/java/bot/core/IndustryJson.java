package bot.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hermit on 2017/6/9.
 */
public class IndustryJson {

    public static final String DEFAULT_DOMAIN = "https://gupiao.baidu.com/api/stocks/stockbasicinfo";

    public static void main(String[] args) throws UnsupportedEncodingException {
        IndustryJson json = new IndustryJson();
        System.out.println(json.getJsonData("sh600000"));
    }

    public String getJsonData(String symbol) {
        BufferedReader br = null;
        String result = null;
        StringBuilder sbuilder = new StringBuilder();

        try {
            String path = modifyPath(symbol);
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.addRequestProperty("Accept", "*/*");
//            conn.addRequestProperty("Accept-Encoding", "gzip, deflate, sdch, br");
            conn.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
            conn.addRequestProperty("Cache-Control", "no-cache");
            conn.addRequestProperty("Connection", "keep-alive");
            conn.addRequestProperty("Content-type", "text/json");
            conn.addRequestProperty("Accept-Charset", "utf-8");
            conn.addRequestProperty("contentType", "utf-8");
            conn.addRequestProperty("Cookie", "BAIDUID=419D2FAE6BFF58EBFBD3820E80576CE7:FG=1; PSTM=1493388421; BIDUPSID=794187422582758A19CE9B7F6F804238; pgv_pvi=3556903936; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; PSINO=1; H_PS_PSSID=1445_21079_20928; Hm_lvt_35d1e71f4c913c126b8703586f1d2307=1496991698,1496996236,1497004686,1497005240; Hm_lpvt_35d1e71f4c913c126b8703586f1d2307=1497005248");
            conn.addRequestProperty("Host", "gupiao.baidu.com");
            conn.addRequestProperty("Pragma", "no-cache");
            conn.addRequestProperty("X-Requested-With", "XMLHttpRequest");

            conn.connect();

            InputStream input = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(input, "UTF-8"));

            String tmpRead = null;
            while ((tmpRead = br.readLine()) != null) {
                sbuilder.append(tmpRead);
                sbuilder.append("\n");
            }

            br.close();
            result = sbuilder.toString();

            conn.disconnect();

            JSONObject j = JSON.parseObject(result);
            result = j.toJSONString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String modifyPath(String symbol) {
        return DEFAULT_DOMAIN + "?from=pc&os_ver=1&cuid=xxx" +
                "&vv=100&format=json&stock_code=" + symbol;
    }
}
