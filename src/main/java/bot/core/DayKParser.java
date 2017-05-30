package bot.core;

import model.DayK;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by stonezhang on 2017/5/27.
 */
public class DayKParser {
    private static Pattern doubleP = Pattern.compile("[-|+]?[0-9]+(\\.[0-9]+)?");
    private static Pattern doubleRange = Pattern.compile("[-|+]?[0-9]+(\\.[0-9]+)? - [-|+]?[0-9]+(\\.[0-9]+)?");
    private static Pattern deviationR = Pattern.compile("[-|+]?[0-9]+(\\.[0-9]+)?%");
    private static Pattern bigChineseValue = Pattern.compile("[-|+]?[0-9]+(\\.[0-9]+)?[亿|万]?");

    public static List<DayK> parseDayK(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).method(Connection.Method.POST).get();
        } catch (IOException e) {
            return new ArrayList<>();
        }
        System.out.println(doc);
        List<DayK> result = new ArrayList<>();
        Elements stockBlockList = doc.select("#stockList > div.tableContainer.new-portfolio > table > tbody");
//        #stockList > div.tableContainer.new-portfolio > table > tbody > tr:nth-child(1)

        System.out.println(stockBlockList);
        int i = 1;
        Elements stockBlock = stockBlockList.select("tr:nth-child(" + i + ")");
        while(!stockBlock.isEmpty()) {
            DayK dayK = new DayK();
            dayK.setId(stockBlock.select("td:nth-child(1)").text());
            dayK.setName(stockBlock.select("td:nth-child(2)").text());

            String price = stockBlock.select("td:nth-child(3)").text();
            if(match(price, doubleP)) {
                dayK.setPrice(Double.parseDouble(price));
            }

            String deviation = stockBlock.select("td:nth-child(4)").text();
            if(match(deviation, doubleP)) {
                dayK.setDeviation(Double.parseDouble(deviation));
            }

            String devRatio = stockBlock.select("td:nth-child(5)").text();
            if(match(devRatio, deviationR)) {
                dayK.setDevRatio(Double.parseDouble(devRatio.substring(0, devRatio.length()-1)));
            }

            String range = stockBlock.select("td:nth-child(6)").text();
            if(match(range, doubleRange)) {
                dayK.setLow(Double.parseDouble(range.split(" - ")[0]));
                dayK.setHigh(Double.parseDouble(range.split(" - ")[1]));
            }

            String rangeFT = stockBlock.select("td:nth-child(7)").text();
            if(match(rangeFT, doubleRange)) {
                dayK.setLowFT(Double.parseDouble(rangeFT.split(" - ")[0]));
                dayK.setHighFT(Double.parseDouble(rangeFT.split(" - ")[1]));
            }

            String totalValue = stockBlock.select("td:nth-child(8)").text();
            if(match(totalValue, bigChineseValue)) {
                dayK.setTotalValue(Double.parseDouble(totalValue.substring(0, totalValue.length()-1)));
            }


            String pe = stockBlock.select("td:nth-child(9)").text();
            if(match(pe, doubleP)) {
                dayK.setPe(Double.parseDouble(pe));
            }

            String dealAmount = stockBlock.select("td:nth-child(10)").text();
            if(match(dealAmount, bigChineseValue)) {
                dayK.setDealAmount(Double.parseDouble(dealAmount.substring(0, dealAmount.length() - 1)));
            }

            String dealValue = stockBlock.select("td:nth-child(11)").text();
            if (match(dealValue, bigChineseValue)) {

                dayK.setDealValue((Double.parseDouble(dealValue.substring(0, dealValue.length() - 1))));
            }

            result.add(dayK);

            i++;
            stockBlock = stockBlockList.select("tr:nth-child(" + i + ")");
        }
        return result;
    }

    private static boolean match(String str, Pattern p) {
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
