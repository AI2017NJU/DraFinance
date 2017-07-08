package model;

import java.util.List;

/**
 * Created by stonezhang on 2017/7/7.
 */
public class BackTest {
    private double balance;
    private String date;
    private double value;

    @Override
    public String toString() {
        return "BackTest{" +
                "balance=" + balance +
                ", date='" + date + '\'' +
                ", value=" + value +
                '}';
    }

    public BackTest(double balance, double value, String date) {
        this.balance = balance;
        this.date = date;
        this.value = value;
    }

    public BackTest() {}

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
