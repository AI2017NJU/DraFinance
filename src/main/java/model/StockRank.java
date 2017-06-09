package model;

/**
 * Created by Hermit on 2017/6/9.
 */
public class StockRank {

    private String symbol;
    private String name;
    private double lastest_price;
    private double devia_per;

    public StockRank() {}

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLastest_price() {
        return lastest_price;
    }

    public void setLastest_price(double lastest_price) {
        this.lastest_price = lastest_price;
    }

    public double getDevia_per() {
        return devia_per;
    }

    public void setDevia_per(double devia_per) {
        this.devia_per = devia_per;
    }
}
