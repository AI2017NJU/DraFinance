package model;

/**
 * Created by stonezhang on 2017/5/27.
 */
public class DayK {
    private String id;
    private String name;
    private double price;
    private double deviation;
    private double devRatio;
    private double low;
    private double high;
    private double lowFT;
    private double highFT;
    private double totalValue;
    private double pe;
    private double dealAmount;
    private double dealValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDeviation() {
        return deviation;
    }

    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }

    public double getDevRatio() {
        return devRatio;
    }

    public void setDevRatio(double devRatio) {
        this.devRatio = devRatio;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLowFT() {
        return lowFT;
    }

    public void setLowFT(double lowFT) {
        this.lowFT = lowFT;
    }

    public double getHighFT() {
        return highFT;
    }

    public void setHighFT(double highFT) {
        this.highFT = highFT;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
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

    @Override
    public String toString() {
        return "DayK{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", deviation=" + deviation +
                ", devRatio=" + devRatio +
                ", low=" + low +
                ", high=" + high +
                ", lowFT=" + lowFT +
                ", highFT=" + highFT +
                ", totalValue=" + totalValue +
                ", pe=" + pe +
                ", dealAmount=" + dealAmount +
                ", dealValue=" + dealValue +
                '}';
    }
}
