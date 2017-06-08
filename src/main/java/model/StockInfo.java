package model;

/**
 * Created by stonezhang on 2017/6/8.
 */
public class StockInfo {
    private String symbol;
    private String name;

    @Override
    public String toString() {
        return "StockInfo{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
