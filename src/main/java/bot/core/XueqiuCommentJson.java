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
            conn.addRequestProperty("Cookie", "aliyungf_tc=AQAAAGj7/UPErgoAR6ji3efmP07/2yU9; xq_a_token=0a52c567442f1fdd8b09c27e0abb26438e274a7e; xq_a_token.sig=dR_XY4cJjuYM6ujKxH735NKcOpw; xq_r_token=43c6fed2d6b5cc8bc38cc9694c6c1cf121d38471; xq_r_token.sig=8d4jOYdZXEWqSBXOB9N5KuMMZq8; u=281499430234078; device_id=fd8ab109db1c34dee086363bc422fdb0; s=fa16x934lm; __utmt=1; Hm_lvt_1db88642e346389874251b5a1eded6e3=1499430235; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1499430269; __utma=1.1938545482.1499430253.1499430253.1499430253.1; __utmb=1.2.10.1499430253; __utmc=1; __utmz=1.1499430253.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");
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
