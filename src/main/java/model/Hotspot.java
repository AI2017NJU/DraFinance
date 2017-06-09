package model;

import java.util.List;

/**
 * Created by NJU on 2016/6/10.
 */
public class Hotspot {
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
        this.keyword = keyword;
        this.date = date;
        this.description = description;
        this.drivingEvent = drivingEvent;
        this.url = url;
        this.stockName1 = stockName1;
        this.stockName2 = stockName2;
        this.stockName3 = stockName3;
        this.stockID1 = stockID1;
        this.stockID2 = stockID2;
        this.stockID3 = stockID3;
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
        this.stockID1 = stockID1;
    }

    public String getStockID2() {
        return stockID2;
    }

    public void setStockID2(String stockID2) {
        this.stockID2 = stockID2;
    }

    public String getStockID3() {
        return stockID3;
    }

    public void setStockID3(String stockID3) {
        this.stockID3 = stockID3;
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
