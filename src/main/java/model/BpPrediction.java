package model;

/**
 * Created by stonezhang on 2017/6/13.
 */
public class BpPrediction {
    private int id;
    private String symbol;
    private String date;
    private double priceMiddle;
    private double priceHigh;
    private double priceLow;
    private double actualPrice;

    @Override
    public String toString() {
        return "BpPrediction{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", date='" + date + '\'' +
                ", priceMiddle=" + priceMiddle +
                ", priceHigh=" + priceHigh +
                ", priceLow=" + priceLow +
                ", actualPrice=" + actualPrice +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPriceMiddle() {
        return priceMiddle;
    }

    public void setPriceMiddle(double priceMiddle) {
        this.priceMiddle = priceMiddle;
    }

    public double getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(double priceHigh) {
        this.priceHigh = priceHigh;
    }

    public double getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(double priceLow) {
        this.priceLow = priceLow;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }
}
