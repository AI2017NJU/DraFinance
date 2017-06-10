package model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by NJU on 2016/6/10.
 */
public class Hotspot {
    @Override
    public String toString() {
        return "Hotspot{" +
                "keyword='" + keyword + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", drivingEvent='" + drivingEvent + '\'' +
                ", url='" + url + '\'' +
                ", stockName1='" + stockName1 + '\'' +
                ", stockName2='" + stockName2 + '\'' +
                ", stockName3='" + stockName3 + '\'' +
                ", stockID1='" + stockID1 + '\'' +
                ", stockID2='" + stockID2 + '\'' +
                ", stockID3='" + stockID3 + '\'' +
                ", stockPrice1='" + stockPrice1 + '\'' +
                ", stockPrice2='" + stockPrice2 + '\'' +
                ", stockPrice3='" + stockPrice3 + '\'' +
                ", devia1='" + devia1 + '\'' +
                ", devia2='" + devia2 + '\'' +
                ", devia3='" + devia3 + '\'' +
                '}';
    }

    // 在上海证券交易所上市的证券，根据上交所“证券编码实施方案”，采用6位数编制方法，前3位数为区别证券品种，
    // 具体见下表所列：
    // 001×××国债现货；110×××120×××企业债券；129×××100×××可转换债券；201×××国债回购；310×××国债期货；
    // 500×××550×××基金；600×××A股；700×××配股；710×××转配股；701×××转配股再配股；711×××转配股再转配股；
    // 720×××红利；730×××新股申购；735×××新基金申购；737×××新股配售；900×××B股。
    private static final String shRe = "001[0-9]{3}|110[0-9]{3}|120[0-9]{3}|100[0-9]{3}|201[0-9]{3}|" +
            "310[0-9]{3}]500[0-9]{3}|550[0-9]{3}|600[0-9]{3}|700[0-9]{3}|710[0-9]{3}|701[0-9]{3}|711[0-9]{3}|" +
            "720[0-9]{3}|730[0-9]{3}|735[0-9]{3}|737[0-9]{3}|900[0-9]{3}";

    private static final String szRe = "00[0-9]{4}|03[0-9]{4}|07[0-9]{4}|08[0-9]{4}|09[0-9]{4}|10[0-9]{4}|11[0-9]{4}|" +
            "12[0-9]{4}|13[0-9]{4}|17[0-9]{4}|18[0-9]{4}|20[0-9]{4}|27[0-9]{4}|28[0-9]{4}|30[0-9]{4}|37[0-9]{4}|" +
            "38[0-9]{4}|39[0-9]{4}";

    public String keyword;
    public String date;
    public String description;
    public String drivingEvent;
    public String url;
    public String stockName1;
    public String stockName2;
    public String stockName3;
    public String stockID1;
    public String stockID2;
    public String stockID3;
    public String stockPrice1;
    public String stockPrice2;
    public String stockPrice3;
    public String devia1;
    public String devia2;
    public String devia3;

    public Hotspot(String keyword, String date, String description, String drivingEvent, String url,
                   String stockName1, String stockName2, String stockName3, String stockID1, String stockID2,
                   String stockID3, String stockPrice1, String stockPrice2 ,
                   String stockPrice3, String devia1, String devia2, String devia3) {
        Pattern pSh = Pattern.compile(shRe);
        Pattern pSz = Pattern.compile(szRe);
        Matcher mSh = pSh.matcher(stockID1);
        Matcher mSz = pSz.matcher(stockID1);
        if(mSh.matches()) {
            this.stockID1 = "SH" + stockID1;
        }
        else if(mSz.matches()) {
            this.stockID1 = "SZ" + stockID1;
        }
        else {
            this.stockID1 = stockID1;
        }

        mSh = pSh.matcher(stockID2);
        mSz = pSz.matcher(stockID2);
        if(mSh.matches()) {
            this.stockID2 = "SH" + stockID2;
        }
        else if(mSz.matches()) {
            this.stockID2 = "SZ" + stockID2;
        }
        else {
            this.stockID2 = stockID2;
        }

        mSh = pSh.matcher(stockID3);
        mSz = pSz.matcher(stockID3);
        if(mSh.matches()) {
            this.stockID3 = "SH" + stockID3;
        }
        else if(mSz.matches()) {
            this.stockID3 = "SZ" + stockID3;
        }
        else {
            this.stockID3 = stockID3;
        }

        this.keyword = keyword;
        this.date = date;
        this.description = description;
        this.drivingEvent = drivingEvent;
        this.url = url;
        this.stockName1 = stockName1;
        this.stockName2 = stockName2;
        this.stockName3 = stockName3;
        this.stockPrice1 = stockPrice1;
        this.stockPrice2 = stockPrice2;
        this.stockPrice3 = stockPrice3;
        this.devia1 = devia1;
        this.devia2 = devia2;
        this.devia3 = devia3;
    }

    public Hotspot() {}

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDrivingEvent() {
        return drivingEvent;
    }

    public void setDrivingEvent(String drivingEvent) {
        this.drivingEvent = drivingEvent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStockName1() {
        return stockName1;
    }

    public void setStockName1(String stockName1) {
        this.stockName1 = stockName1;
    }

    public String getStockName2() {
        return stockName2;
    }

    public void setStockName2(String stockName2) {
        this.stockName2 = stockName2;
    }

    public String getStockName3() {
        return stockName3;
    }

    public void setStockName3(String stockName3) {
        this.stockName3 = stockName3;
    }

    public String getStockID1() {
        return stockID1;
    }

    public void setStockID1(String stockID1) {
        Pattern pSh = Pattern.compile(shRe);
        Pattern pSz = Pattern.compile(szRe);
        Matcher mSh = pSh.matcher(stockID1);
        Matcher mSz = pSz.matcher(stockID1);
        if(mSh.matches()) {
            this.stockID1 = "SH" + stockID1;
        }
        else if(mSz.matches()) {
            this.stockID1 = "SZ" + stockID1;
        }
        else {
            this.stockID1 = stockID1;
        }
    }

    public String getStockID2() {
        return stockID2;
    }

    public void setStockID2(String stockID2) {
        Pattern pSh = Pattern.compile(shRe);
        Pattern pSz = Pattern.compile(szRe);
        Matcher mSh = pSh.matcher(stockID2);
        Matcher mSz = pSz.matcher(stockID2);
        if(mSh.matches()) {
            this.stockID2 = "SH" + stockID2;
        }
        else if(mSz.matches()) {
            this.stockID2 = "SZ" + stockID2;
        }
        else {
            this.stockID2 = stockID2;
        }
    }

    public String getStockID3() {
        return stockID3;
    }

    public void setStockID3(String stockID3) {
        Pattern pSh = Pattern.compile(shRe);
        Pattern pSz = Pattern.compile(szRe);
        Matcher mSh = pSh.matcher(stockID3);
        Matcher mSz = pSz.matcher(stockID3);
        if(mSh.matches()) {
            this.stockID3 = "SH" + stockID3;
        }
        else if(mSz.matches()) {
            this.stockID3 = "SZ" + stockID3;
        }
        else {
            this.stockID3 = stockID3;
        }
    }

    public String getStockPrice1() {
        return stockPrice1;
    }

    public void setStockPrice1(String stockPrice1) {
        this.stockPrice1 = stockPrice1;
    }

    public String getStockPrice2() {
        return stockPrice2;
    }

    public void setStockPrice2(String stockPrice2) {
        this.stockPrice2 = stockPrice2;
    }

    public String getStockPrice3() {
        return stockPrice3;
    }

    public void setStockPrice3(String stockPrice3) {
        this.stockPrice3 = stockPrice3;
    }

    public String getDevia1() {
        return devia1;
    }

    public void setDevia1(String devia1) {
        this.devia1 = devia1;
    }

    public String getDevia2() {
        return devia2;
    }

    public void setDevia2(String devia2) {
        this.devia2 = devia2;
    }

    public String getDevia3() {
        return devia3;
    }

    public void setDevia3(String devia3) {
        this.devia3 = devia3;
    }
}
