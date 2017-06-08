package model;

/**
 * Created by NJU on 2016/5/29.
 */
public class BenchCurrent {
    public String price;
    public String devia_val;
    public String devia_per;
    public String high;
    public String low;
    public String open;
    public String close;
    public String volume;
    public String amount;
    public String up_num;
    public String down_num;
    public String neutral_num;

    public Integer high_attri;
    public Integer low_attri;
    public Integer open_attri;

    public BenchCurrent(String price, String devia_val, String devia_per, String high, String low, String open,
                        String close, String volume, String amount, String up_num, String down_num, String neutral_num,
                        Integer high_attri, Integer low_attri, Integer open_attri) {
        this.price = price;
        this.devia_val = devia_val;
        this.devia_per = devia_per;
        this.high = high;
        this.low = low;
        this.open = open;
        this.close = close;
        this.volume = volume;
        this.amount = amount;
        this.up_num = up_num;
        this.down_num = down_num;
        this.neutral_num = neutral_num;
        this.high_attri = high_attri;
        this.low_attri = low_attri;
        this.open_attri = open_attri;
    }

    public BenchCurrent() {}

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDevia_val() {
        return devia_val;
    }

    public void setDevia_val(String devia_val) {
        this.devia_val = devia_val;
    }

    public String getDevia_per() {
        return devia_per;
    }

    public void setDevia_per(String devia_per) {
        this.devia_per = devia_per;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUp_num() {
        return up_num;
    }

    public void setUp_num(String up_num) {
        this.up_num = up_num;
    }

    public String getDown_num() {
        return down_num;
    }

    public void setDown_num(String down_num) {
        this.down_num = down_num;
    }

    public String getNeutral_num() {
        return neutral_num;
    }

    public void setNeutral_num(String neutral_num) {
        this.neutral_num = neutral_num;
    }

    public Integer getHigh_attri() {
        return high_attri;
    }

    public void setHigh_attri(Integer high_attri) {
        this.high_attri = high_attri;
    }

    public Integer getLow_attri() {
        return low_attri;
    }

    public void setLow_attri(Integer low_attri) {
        this.low_attri = low_attri;
    }

    public Integer getOpen_attri() {
        return open_attri;
    }

    public void setOpen_attri(Integer open_attri) {
        this.open_attri = open_attri;
    }
}
