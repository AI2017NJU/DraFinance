package model;

/**
 * Created by stonezhang on 2017/7/7.
 */
public class BackTestRaw {
    private String symbol;
    private String date;
    private double high;
    private double low;
    private double open;
    private double close;
    private double dealAmount;
    private double ma20;
    private int state; // 0:未决定; 1: 买; 2:卖

    public BackTestRaw() {}

    @Override
    public String toString() {
        return "BackTestRaw{" +
                "symbol='" + symbol + '\'' +
                ", date='" + date + '\'' +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", close=" + close +
                ", dealAmount=" + dealAmount +
                ", ma20=" + ma20 +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BackTestRaw that = (BackTestRaw) o;

        if (state != that.state) return false;
        if (!symbol.equals(that.symbol)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + state;
        return result;
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

    public double getMa20() {
        return ma20;
    }

    public void setMa20(double ma20) {
        this.ma20 = ma20;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
