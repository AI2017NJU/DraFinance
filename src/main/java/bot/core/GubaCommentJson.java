package bot.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tools.StockHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hermit on 2017/6/13.
 */
public class GubaCommentJson {

    public static void main(String[] args) {
        GubaCommentJson gubaCommentJson = new GubaCommentJson();
        System.out.println(gubaCommentJson.getJsonData("SH600000").size());
    }

    public JSONArray getJsonData(String ID) {
        ID = StockHelper.gubaCodeReflect(ID);
        String path = "http://guba.eastmoney.com/list," + ID + ".html";

        JSONArray result = new JSONArray();

        Document doc = null;
        try {
            doc = Jsoup.connect(path).get();

            Elements hiperLink = doc.select("#articlelistnew > div > span.l3 > a");

            for (int i=0;i<5;i++) {
                JSONObject object = new JSONObject();

                Element e = hiperLink.get(i);

                String href = e.attr("href");

                Document d = Jsoup.connect("http://guba.eastmoney.com/" + href).get();
                Element title = d.getElementById("zwconttbt");
                Element author = d.getElementById("zw_header");
                Element text = d.getElementById("zw_body");

                object.put("title", title.toString());
                object.put("author", author.toString());

                String description = "";
                if (text.toString().length() > 100) {
                    description = text.toString().substring(0, 100);
                } else {
                    description = text.toString();
                }

                object.put("description", description);
                object.put("text", text.toString());

                result.add(object);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
