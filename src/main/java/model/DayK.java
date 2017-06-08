package model;

/**
 * Created by stonezhang on 2017/6/8.
 */
public class DayK {
    private int id;
    private String symbol;
    private double high;
    private double low;
    private double open;
    private double close;
    private double dealAmount;
    private double dealValue;
    private String date;

    @Override
    public String toString() {
        return "DayK{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", close=" + close +
                ", dealAmount=" + dealAmount +
                ", dealValue=" + dealValue +
                ", date='" + date + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(double dealAmount) {
        this.dealAmount = dealAmount;
    }

    public double getDealValue() {
        return dealValue;
    }

    public void setDealValue(double dealValue) {
        this.dealValue = dealValue;
    }
}
