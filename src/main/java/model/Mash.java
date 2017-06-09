package model;

/**
 * Created by stonezhang on 2017/6/9.
 */
public class Mash {
    private int id;
    private String symbol;
    private String date;
    private double ma5Volume;
    private double ma5Price;
    private double ma10Volume;
    private double ma10Price;
    private double ma20Volume;
    private double ma20Price;
    private double diff;
    private double dea;
    private double macd;
    private double k;
    private double d;
    private double j;
    private double rsi1;
    private double rsi2;
    private double rsi3;

    @Override
    public String toString() {
        return "Mash{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", date='" + date + '\'' +
                ", ma5Volume=" + ma5Volume +
                ", ma5Price=" + ma5Price +
                ", ma10Volume=" + ma10Volume +
                ", ma10Price=" + ma10Price +
                ", ma20Volume=" + ma20Volume +
                ", ma20Price=" + ma20Price +
                ", diff=" + diff +
                ", dea=" + dea +
                ", macd=" + macd +
                ", k=" + k +
                ", d=" + d +
                ", j=" + j +
                ", rsi1=" + rsi1 +
                ", rsi2=" + rsi2 +
                ", rsi3=" + rsi3 +
                '}';
    }

    public Mash() {}

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

    public double getMa5Volume() {
        return ma5Volume;
    }

    public void setMa5Volume(double ma5Volume) {
        this.ma5Volume = ma5Volume;
    }

    public double getMa5Price() {
        return ma5Price;
    }

    public void setMa5Price(double ma5Price) {
        this.ma5Price = ma5Price;
    }

    public double getMa10Volume() {
        return ma10Volume;
    }

    public void setMa10Volume(double ma10Volume) {
        this.ma10Volume = ma10Volume;
    }

    public double getMa10Price() {
        return ma10Price;
    }

    public void setMa10Price(double ma10Price) {
        this.ma10Price = ma10Price;
    }

    public double getMa20Volume() {
        return ma20Volume;
    }

    public void setMa20Volume(double ma20Volume) {
        this.ma20Volume = ma20Volume;
    }

    public double getMa20Price() {
        return ma20Price;
    }

    public void setMa20Price(double ma20Price) {
        this.ma20Price = ma20Price;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }

    public double getDea() {
        return dea;
    }

    public void setDea(double dea) {
        this.dea = dea;
    }

    public double getMacd() {
        return macd;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }

    public double getRsi1() {
        return rsi1;
    }

    public void setRsi1(double rsi1) {
        this.rsi1 = rsi1;
    }

    public double getRsi2() {
        return rsi2;
    }

    public void setRsi2(double rsi2) {
        this.rsi2 = rsi2;
    }

    public double getRsi3() {
        return rsi3;
    }

    public void setRsi3(double rsi3) {
        this.rsi3 = rsi3;
    }
}
