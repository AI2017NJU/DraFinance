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
            conn.addRequestProperty("Cookie", "u=171496314516601; s=fe12lk2eir; webp=1; aliyungf_tc=AQAAAGTAjRpsLQcAW1Gi0/MMfPIwOxi1; xq_a_token=445b4b15f59fa37c8bd8133949f910e7297a52ef; xq_a_token.sig=5qsKG3NMR_Go5O8QjcKxalfFwhM; xq_r_token=132b2ba19b0053bc7f04401788b6e0d24f35d365; xq_r_token.sig=1w18Bj12xS0s6jGzDJnEQgA8IGo; device_id=6525b7abb38f09ec976375905cde23bc; __utmt=1; Hm_lvt_1db88642e346389874251b5a1eded6e3=1496839466,1496992608,1497092810,1497323678; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1497323692; __utma=1.1747867414.1496314528.1496994586.1497323682.5; __utmb=1.2.10.1497323682; __utmc=1; __utmz=1.1496314528.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");
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
