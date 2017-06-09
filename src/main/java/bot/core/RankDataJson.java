package bot.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hermit on 2017/6/9.
 */
public class RankDataJson {

    public static void main(String[] args) {
        RankDataJson json = new RankDataJson();
        System.out.println(json.getDeviaUpJsonData());
        System.out.println(json.getDeviaUp5MinutesJsonData());
        System.out.println(json.getTurnoverJsonData());
    }

    public String getDeviaUpJsonData() {
        String path = "http://q.jrjimg.cn/?q=cn|s|sa&c=s,ta,tm,sl,tr&n=hqapld&o=pl,d&p=5&_dc=1497012715146&_=1497012715147";
        return getJsonData(path);
    }

    public String getDeviaUp5MinutesJsonData() {
        String path = "http://q.jrjimg.cn/?q=cn|s|sa&c=s,min5pl&n=hqamin5pld&o=min5pl,d&p=5&_dc=1497012715147&_=1497012715148";
        return getJsonData(path);
    }

    public String getTurnoverJsonData() {
        String path = "http://q.jrjimg.cn/?q=cn|s|sa&c=s,ta,tm,sl,tr&n=hqatrd&o=tr,d&p=5&_dc=1497012715149&_=1497012715149";
        return getJsonData(path);
    }

    private String getJsonData(String path) {
        BufferedReader br = null;
        String result = null;
        StringBuilder sbuilder = new StringBuilder();

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.connect();

            InputStream input = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(input, "GBK"));

            String tmpRead = null;
            while ((tmpRead = br.readLine()) != null) {
                sbuilder.append(tmpRead);
                sbuilder.append("\n");
            }

            br.close();
            result = sbuilder.toString();

            conn.disconnect();

            int start = result.indexOf('{');
            int end = result.lastIndexOf('}');

            result = result.substring(start, end + 1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

}
