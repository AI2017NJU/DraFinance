package bot.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hermit on 2017/6/1.
 */
public class XueqiuCommentJson {

    public static final String DEFAULT_DOMAIN = "https://xueqiu.com/statuses/search.json";
    public static final int DEFAULT_COUNT = 10;
    public static final int DEFAULT_COMMENT = 0;
    public static final int DEFAULT_HL = 0;
    public static final String DEFAULT_SOURCE = "all";
    public static final String DEFAULT_SORT = "alpha";
    public static final int DEFAULT_PAGE = 1;

    public static void main(String[] args) {
        String result = new XueqiuCommentJson().getJSONData("SH600000");
        System.out.println(result);
    }

    public String getJSONData(String symbol) {
        BufferedReader br = null;
        String result = null;
        StringBuilder sbuilder = new StringBuilder();

        try {
            String path = modifyPath(symbol);
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.addRequestProperty("Cache-Control", "no-cache");
            conn.addRequestProperty("Connection", "keep-alive");
            conn.addRequestProperty("Content-type", "text/json");
            conn.addRequestProperty("Accept-Charset", "utf-8");
            conn.addRequestProperty("contentType", "utf-8");
            conn.addRequestProperty("Cookie", "u=171496314516601; s=fe12lk2eir; aliyungf_tc=AQAAAO8c1Fg2jAkAW1Gi0+WS6UpVfTE+; xq_a_token=876f2519b10cea9dc131b87db2e5318e5d4ea64f; xq_r_token=709abdc1ccb40ac956166989385ffd603ad6ab6f; __utmt=1; Hm_lvt_1db88642e346389874251b5a1eded6e3=1496314518,1496839466; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1496839545; __utma=1.1747867414.1496314528.1496314528.1496839466.2; __utmb=1.4.9.1496839466; __utmc=1; __utmz=1.1496314528.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");
            conn.addRequestProperty("Host", "xueqiu.com");
            conn.addRequestProperty("Pragma", "no-cache");
            conn.addRequestProperty("Upgrade-Insecure-Requests", "1");

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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String modifyPath(String symbol) {
        return DEFAULT_DOMAIN
                + "?count=" + DEFAULT_COUNT
                + "&comment=" + DEFAULT_COMMENT
                + "&symbol=" + symbol
                + "&hl=" + DEFAULT_HL
                + "&source=" + DEFAULT_SOURCE
                + "&sort=" + DEFAULT_SORT
                + "&page=" + DEFAULT_PAGE;
    }
}
