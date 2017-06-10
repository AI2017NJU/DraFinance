package bot.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.Hotspot;
import model.News;
import model.StockRank;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by NJU on 2016/6/10.
 */
public class TotalNews {

//    public static void main(String[] args) {
//        List<News> newsList = getStockNews();
//        for (News n: newsList) {
//            System.out.println(n.title);
//        }
//
//        List<Hotspot> hotspotList = getHotspot();
//        for (Hotspot s: hotspotList) {
//            System.out.println(s.description);
//        }
//    }

    public static List<News> getStockNews() {
        List<News> result = new ArrayList<News>();
        try {
            Document doc = Jsoup.connect("http://finance.sina.com.cn/stock/").get();
//            System.out.println(doc.body());
            Elements head = doc.select("#directad01 > div.hdline > h2 > a");
//
            Elements news1 = doc.select("#directad01 > ul:nth-child(3) > li:nth-child(1) > a");
            Elements news2 = doc.select("#directad01 > ul:nth-child(3) > li:nth-child(2) > a");
            Elements news3 = doc.select("#directad01 > ul:nth-child(3) > li:nth-child(3) > a");
            Elements news4 = doc.select("#directad01 > ul:nth-child(3) > li:nth-child(4) > a");
            Elements news5 = doc.select("#directad01 > ul:nth-child(3) > li:nth-child(5) > a");
            Elements news6 = doc.select("#directad01 > ul:nth-child(3) > li:nth-child(6) > a");
            Elements news7 = doc.select("#directad01 > ul:nth-child(3) > li:nth-child(7) > a");
            Elements news8 = doc.select("#directad01 > ul:nth-child(3) > li:nth-child(8) > a");
            Elements news9 = doc.select("#directad01 > ul:nth-child(3) > li:nth-child(9) > a");

            News h1 = new News(null, head.text(), head.attr("href"));
//            News h2 = new News(null, headSecond.text(), head.attr("href"));
//            News h3 = new News(null, headThird.text(), head.attr("href"));
            News n1 = new News(null, news1.text(), news1.attr("href"));
            News n2 = new News(null, news2.text(), news2.attr("href"));
            News n3 = new News(null, news3.text(), news3.attr("href"));
            News n4 = new News(null, news4.text(), news4.attr("href"));
            News n5 = new News(null, news5.text(), news5.attr("href"));
            News n6 = new News(null, news6.text(), news6.attr("href"));
            News n7 = new News(null, news7.text(), news7.attr("href"));
            News n8 = new News(null, news8.text(), news8.attr("href"));
            News n9 = new News(null, news9.text(), news9.attr("href"));

            result.add(h1);
            result.add(n1);
            result.add(n2);
            result.add(n3);
            result.add(n4);
            result.add(n5);
            result.add(n6);
            result.add(n7);
            result.add(n8);
            result.add(n9);
//            System.out.println(news7.attr("href"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<News> getCompanyNews() {
        List<News> result = new ArrayList<News>();
        try {
            Document doc = Jsoup.connect("http://finance.sina.com.cn/stock/").get();
//            System.out.println(doc.body());
            Elements head = doc.select("#p02_m_s_c01 > div.hdline > h2 > a");
//
            Elements news1 = doc.select("#p02_m_s_c01 > ul:nth-child(2) > li:nth-child(1) > p > a");
            Elements news2 = doc.select("#p02_m_s_c01 > ul:nth-child(2) > li:nth-child(2) > p > a");
            Elements news3 = doc.select("#p02_m_s_c01 > ul:nth-child(2) > li:nth-child(3) > p > a");
            Elements news4 = doc.select("#p02_m_s_c01 > ul:nth-child(2) > li:nth-child(4) > p > a");
            Elements news5 = doc.select("#p02_m_s_c01 > ul:nth-child(2) > li:nth-child(5) > p > a");
            Elements news6 = doc.select("#p02_m_s_c01 > ul:nth-child(2) > li:nth-child(6) > p > a");
            Elements news7 = doc.select("#p02_m_s_c01 > ul:nth-child(2) > li:nth-child(7) > p > a");
            Elements news8 = doc.select("#p02_m_s_c01 > ul:nth-child(2) > li:nth-child(8) > p > a");
            Elements news9 = doc.select("#p02_m_s_c01 > ul:nth-child(2) > li:nth-child(9) > p > a");

            News h1 = new News(null, head.text(), head.attr("href"));
//            News h2 = new News(null, headSecond.text(), head.attr("href"));
//            News h3 = new News(null, headThird.text(), head.attr("href"));
            News n1 = new News(null, news1.text(), news1.attr("href"));
            News n2 = new News(null, news2.text(), news2.attr("href"));
            News n3 = new News(null, news3.text(), news3.attr("href"));
            News n4 = new News(null, news4.text(), news4.attr("href"));
            News n5 = new News(null, news5.text(), news5.attr("href"));
            News n6 = new News(null, news6.text(), news6.attr("href"));
            News n7 = new News(null, news7.text(), news7.attr("href"));
            News n8 = new News(null, news8.text(), news8.attr("href"));
            News n9 = new News(null, news9.text(), news9.attr("href"));

            result.add(h1);
            result.add(n1);
            result.add(n2);
            result.add(n3);
            result.add(n4);
            result.add(n5);
            result.add(n6);
            result.add(n7);
            result.add(n8);
            result.add(n9);
//            System.out.println(news7.attr("href"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<News> getFinanceNews() {
        List<News> result = new ArrayList<News>();
        try {
            Document doc = Jsoup.connect("http://finance.sina.com.cn/").get();
//            System.out.println(doc.body());
            Elements head = doc.select("#blk_hdline_01 > h3:nth-child(1) > a");
//
            Elements news1 = doc.select("#blk_hdline_01 > h3:nth-child(3) > a");
            Elements news2 = doc.select("#blk_hdline_01 > h3:nth-child(5) > a");
            Elements news3 = doc.select("#directAd_samsung_id > li:nth-child(1) > a");
            Elements news4 = doc.select("#directAd_samsung_id > li:nth-child(2) > a");
            Elements news5 = doc.select("#directAd_samsung_id > li:nth-child(3) > a");
            Elements news6 = doc.select("#directAd_samsung_id > li:nth-child(4) > a");
            Elements news7 = doc.select("#directAd_samsung_id_02 > li:nth-child(1) > a:nth-child(1)");
            Elements news8 = doc.select("#directAd_samsung_id_02 > li:nth-child(2) > a");
            Elements news9 = doc.select("#directAd_samsung_id_02 > li:nth-child(3) > a");

            News h1 = new News(null, head.text(), head.attr("href"));
//            News h2 = new News(null, headSecond.text(), head.attr("href"));
//            News h3 = new News(null, headThird.text(), head.attr("href"));
            News n1 = new News(null, news1.text(), news1.attr("href"));
            News n2 = new News(null, news2.text(), news2.attr("href"));
            News n3 = new News(null, news3.text(), news3.attr("href"));
            News n4 = new News(null, news4.text(), news4.attr("href"));
            News n5 = new News(null, news5.text(), news5.attr("href"));
            News n6 = new News(null, news6.text(), news6.attr("href"));
            News n7 = new News(null, news7.text(), news7.attr("href"));
            News n8 = new News(null, news8.text(), news8.attr("href"));
            News n9 = new News(null, news9.text(), news9.attr("href"));

            result.add(h1);
            result.add(n1);
            result.add(n2);
            result.add(n3);
            result.add(n4);
            result.add(n5);
            result.add(n6);
            result.add(n7);
            result.add(n8);
            result.add(n9);
//            System.out.println(news7.attr("href"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Hotspot> getHotspot() {
        List<Hotspot> result = new ArrayList<Hotspot>();
        try {
            LocalDate today = LocalDate.now();
            while(result.size() != 4) {
                String basUrl = "http://gupiao.baidu.com/concept/?searchdate=" + today;
//                System.out.println(basUrl);
                Document doc = Jsoup.connect(basUrl).get();
                Element infoList = doc.select("#list-body").first();
                int cursor = 2;
                while(true) {
                    Elements info = infoList.select(String.format("div:nth-child(%d)", cursor));
//                    System.out.println(info.text());
                    if(info.isEmpty() || info.select("a > div.concept-header.column1 > h2").text().trim().equals("")) {
                        break;
                    }

                    String keyword = info.select("a > div.concept-header.column1 > h2").text().trim();
                    String date = info.select("a > div.concept-header.column1 > p:nth-child(3)").text().trim();
                    String description = info.select("#a > div.concept-header.column1 > p:nth-child(4)").text().trim();
                    String drivingEvent = info.select("> a > div.concept-event.column3").text().trim();
                    String url = "http://gupiao.baidu.com" + info.select("a").attr("href");
                    String stockName1 = info.select("ul > li:nth-child(1) > " +
                            "div:nth-child(1) > a > div:nth-child(1)").text().trim();
                    String stockName2 = info.select("ul > li:nth-child(2) > " +
                            "div:nth-child(1) > a > div:nth-child(1)").text().trim();
                    String stockName3 = info.select("ul > li:nth-child(3) > " +
                            "div:nth-child(1) > a > div:nth-child(1)").text().trim();;
                    String stockID1 = info.select("ul > li:nth-child(1) > " +
                            "div:nth-child(1) > a > div.code").text().trim();
                    String stockID2 = info.select("ul > li:nth-child(2) > " +
                            "div:nth-child(1) > a > div.code").text().trim();
                    String stockID3 = info.select("ul > li:nth-child(3) > " +
                            "div:nth-child(1) > a > div.code").text().trim();;
                    String stockPrice1 = info.select("ul > li:nth-child(1) > div:nth-child(2)").text().trim();
                    String stockPrice2 = info.select("ul > li:nth-child(2) > div:nth-child(2)").text().trim();
                    String stockPrice3 = info.select("ul > li:nth-child(3) > div:nth-child(2)").text().trim();
                    String devia1 = info.select("ul > li:nth-child(1) > div:nth-child(3)").text().trim();
                    String devia2 = info.select("ul > li:nth-child(2) > div:nth-child(3)").text().trim();
                    String devia3 = info.select("ul > li:nth-child(3) > div:nth-child(3)").text().trim();

                    result.add(new Hotspot(keyword, date, description, drivingEvent, url,
                            stockName1, stockName2, stockName3, stockID1, stockID2, stockID3,
                            stockPrice1, stockPrice2, stockPrice3, devia1, devia2, devia3));

                    if (result.size() == 4) {
                        break;
                    }

                    cursor ++;
                }
                today = today.plusDays(-1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JSONArray getStockUp() {
        JSONArray dataarray = new JSONArray();

        String url = "http://hq.sinajs.cn/format=json&list=new_all_changepercent_up";
        try {
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GBK"));
            String lines=reader.readLine().substring(37);
            lines = ","+lines.substring(1, lines.length()-2);
            String[] data = lines.split("]");
            for (int i=0;i<data.length;i+=1)  {
                String each = data[i];
//                System.out.println(each);
//                System.out.println("###########################");
                String[] temp = each.split(",");
                Map<String, String> map = new HashMap<String, String>();
//                System.out.println(temp[0]);
//                System.out.println("++++++++++++++++++++++++++++++++");
                map.put("rank", String.valueOf(i));
                map.put("stockid", temp[1].substring(2, temp[1].length()-1));
                map.put("stockname", temp[2].substring(1, temp[2].length()-1));
                map.put("price", temp[3].substring(1, temp[3].length()-1));
                map.put("deviation", temp[4].substring(1, temp[4].length()-1));
                map.put("turnover", temp[6].substring(1, temp[6].length()-1));
                JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
                dataarray.add(jsonObject);
            }
//            JSONObject record = new JSONObject(lines);
//            System.out.println(record);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataarray;
    }

    public static JSONArray getIndustryUp() {
        JSONArray dataarray = new JSONArray();

        String url = "http://hq.sinajs.cn/format=json&list=sinaindustry_up";
        try {
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GBK"));
            String lines=reader.readLine().substring(28);
            lines = ","+lines.substring(1, lines.length()-2);
//            System.out.println(lines);
            String[] data = lines.split("'");
            for (int i=1;i<data.length;i+=2) {
                String each = data[i];
//                System.out.println(each);
//                System.out.println("###########################");
                String[] temp = each.split(",");
//                for (int k =0;k<temp.length;k++) {
//                    System.out.println(temp[k]);
//                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("rank", String.valueOf((i+1)/2+1));
                map.put("industryname", temp[1]);
                map.put("industrydevia", temp[5]);
                map.put("stockname", temp[12]);
                map.put("stockdevia", temp[9]);
                map.put("stockid", temp[8]);
                JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
                dataarray.add(jsonObject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataarray;
    }

    public static void main(String[] args) {
        System.out.println(getHotspot());
    }
}
