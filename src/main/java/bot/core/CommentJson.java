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
public class CommentJson {

    public static final String DEFAULT_DOMAIN = "https://xueqiu.com/statuses/search.json";
    public static final int DEFAULT_COUNT = 10;
    public static final int DEFAULT_COMMENT = 0;
    public static final int DEFAULT_HL = 0;
    public static final String DEFAULT_SOURCE = "all";
    public static final String DEFAULT_SORT = "alpha";
    public static final int DEFAULT_PAGE = 1;

    public static void main(String[] args) {
        String result = new CommentJson().getJSONData("SH600000");
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
            conn.addRequestProperty("Cookie", "aliyungf_tc=AQAAAANWekozxAoAXlGi03PxdX8MiuMr; s=ga14oxx775; xq_a_token=8" +
                    "76f2519b10cea9dc131b87db2e5318e5d4ea64f; xq_r_token=709abdc1ccb40ac956166989385ffd603ad6ab6f; u=331" +
                    "496285545742; __utma=1.216155465.1496285546.1496285546.1496288461.2; __utmc=1; __utmz=1.1496285" +
                    "546.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); Hm_lvt_1db88642e346389874251b5a1eded6e3" +
                    "=1496285546; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1496308327");
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
                + "&page=1" + DEFAULT_PAGE;
    }
}
