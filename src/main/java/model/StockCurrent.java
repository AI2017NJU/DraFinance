package model;

/**
 * Created by zcj on 16/5/8.
 * 股票实时数据
 */
public class StockCurrent {

    public String price;            // 实时价格
    public String devia_val;        // 涨跌值
    public String devia_per;        // 涨跌幅
    public String open;             // 今开
    public String volume;           // 成交量(*手)
    public String high;             // 最高
    public String up_stop;          // 涨停
    public String inner_count;      // 内盘(*手)
    public String amount;           // 成交额(*100000000)
    public String committee;        // 委比
    public String avail_amount;     // 流通市值(*100000000)
    public String pe;               // 市盈率
    public String profit_per;       // 每股收益
    public String total_volume;     // 总股本(*100000000)
    public String close ;           // 作收
    public String turnover;         // 换手率
    public String low;              // 最低
    public String down_stop;        // 跌停
    public String outer_count;      // 外盘(*手)
    public String amplitude;        // 振幅
    public String quantity_ratio;   // 量比
    public String total_amount;     // 总市值(*100000000)
    public String pb;               // 市净率
    public String value_per_stock;  // 每股净资产
    public String available_stock;  // 流通股本(*100000000)

    //----------------------------------------------------------

    public Integer open_attri;
    public Integer high_attri;          //
    public Integer low_attri;           //
    public Integer up_attri;            //
    public Integer down_attri;          //

    //----------------------------------------------------------

    public Double out5;
    public Integer out5_vol;
    public Double out4;
    public Integer out4_vol;
    public Double out3;
    public Integer out3_vol;
    public Double out2;
    public Integer out2_vol;
    public Double out1;
    public Integer out1_vol;

    //-----------------------------------------------------------

    public Double in5;
    public Integer in5_vol;
    public Double in4;
    public Integer in4_vol;
    public Double in3;
    public Integer in3_vol;
    public Double in2;
    public Integer in2_vol;
    public Double in1;
    public Integer in1_vol;

    public StockCurrent(String price, String devia_val, String devia_per, String open, String volume,
                        String high, String up_stop, String inner_count, String amount, String committee,
                        String avail_amount, String pe, String profit_per, String total_volume, String close,
                        String turnover, String low, String down_stop, String outer_count, String amplitude,
                        String quantity_ratio, String total_amount, String pb, String value_per_stock, String available_stock,
                        Integer open_attri, Integer high_attri, Integer low_attri, Integer up_attri, Integer down_attri,
                        Double out5, Double out4, Double out3, Double out2, Double out1,
                        Integer out5_vol, Integer out4_vol, Integer out3_vol, Integer out2_vol, Integer out1_vol,
                        Double in5, Double in4, Double in3, Double in2, Double in1,
                        Integer in5_vol, Integer in4_vol, Integer in3_vol, Integer in2_vol, Integer in1_vol
                        ) {

        this.price = price;
        this.devia_val = devia_val;
        this.devia_per = devia_per;
        this.open = open;
        this.volume = volume;
        this.high = high;
        this.up_stop = up_stop;
        this.inner_count = inner_count;
        this.amount = amount;
        this.committee = committee;
        this.avail_amount = avail_amount;
        this.pe = pe;
        this.profit_per = profit_per;
        this.total_volume = total_volume;
        this.close = close;
        this.turnover = turnover;
        this.low = low;
        this.down_stop = down_stop;
        this.outer_count = outer_count;
        this.amplitude = amplitude;
        this.quantity_ratio = quantity_ratio;
        this.total_amount = total_amount;
        this.pb = pb;
        this.value_per_stock = value_per_stock;
        this.available_stock = available_stock;

        this.open_attri = open_attri;
        this.high_attri = high_attri;
        this.low_attri = low_attri;
        this.up_attri = up_attri;
        this.down_attri = down_attri;

        this.out5 = out5;
        this.out4 = out4;
        this.out3 = out3;
        this.out2 = out2;
        this.out1 = out1;
        this.out5_vol = out5_vol;
        this.out4_vol = out4_vol;
        this.out3_vol = out3_vol;
        this.out2_vol = out2_vol;
        this.out1_vol = out1_vol;

        this.in5 = in5;
        this.in4 = in4;
        this.in3 = in3;
        this.in2 = in2;
        this.in1 = in1;
        this.in5_vol = in5_vol;
        this.in4_vol = in4_vol;
        this.in3_vol = in3_vol;
        this.in2_vol = in2_vol;
        this.in1_vol = in1_vol;

    }

    public StockCurrent() {}

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

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getUp_stop() {
        return up_stop;
    }

    public void setUp_stop(String up_stop) {
        this.up_stop = up_stop;
    }

    public String getInner_count() {
        return inner_count;
    }

    public void setInner_count(String inner_count) {
        this.inner_count = inner_count;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }

    public String getAvail_amount() {
        return avail_amount;
    }

    public void setAvail_amount(String avail_amount) {
        this.avail_amount = avail_amount;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getProfit_per() {
        return profit_per;
    }

    public void setProfit_per(String profit_per) {
        this.profit_per = profit_per;
    }

    public String getTotal_volume() {
        return total_volume;
    }

    public void setTotal_volume(String total_volume) {
        this.total_volume = total_volume;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getDown_stop() {
        return down_stop;
    }

    public void setDown_stop(String down_stop) {
        this.down_stop = down_stop;
    }

    public String getOuter_count() {
        return outer_count;
    }

    public void setOuter_count(String outer_count) {
        this.outer_count = outer_count;
    }

    public String getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(String amplitude) {
        this.amplitude = amplitude;
    }

    public String getQuantity_ratio() {
        return quantity_ratio;
    }

    public void setQuantity_ratio(String quantity_ratio) {
        this.quantity_ratio = quantity_ratio;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getPb() {
        return pb;
    }

    public void setPb(String pb) {
        this.pb = pb;
    }

    public String getValue_per_stock() {
        return value_per_stock;
    }

    public void setValue_per_stock(String value_per_stock) {
        this.value_per_stock = value_per_stock;
    }

    public String getAvailable_stock() {
        return available_stock;
    }

    public void setAvailable_stock(String available_stock) {
        this.available_stock = available_stock;
    }

    public Integer getOpen_attri() {
        return open_attri;
    }

    public void setOpen_attri(Integer open_attri) {
        this.open_attri = open_attri;
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

    public Integer getUp_attri() {
        return up_attri;
    }

    public void setUp_attri(Integer up_attri) {
        this.up_attri = up_attri;
    }

    public Integer getDown_attri() {
        return down_attri;
    }

    public void setDown_attri(Integer down_attri) {
        this.down_attri = down_attri;
    }

    public Double getOut5() {
        return out5;
    }

    public void setOut5(Double out5) {
        this.out5 = out5;
    }

    public Integer getOut5_vol() {
        return out5_vol;
    }

    public void setOut5_vol(Integer out5_vol) {
        this.out5_vol = out5_vol;
    }

    public Double getOut4() {
        return out4;
    }

    public void setOut4(Double out4) {
        this.out4 = out4;
    }

    public Integer getOut4_vol() {
        return out4_vol;
    }

    public void setOut4_vol(Integer out4_vol) {
        this.out4_vol = out4_vol;
    }

    public Double getOut3() {
        return out3;
    }

    public void setOut3(Double out3) {
        this.out3 = out3;
    }

    public Integer getOut3_vol() {
        return out3_vol;
    }

    public void setOut3_vol(Integer out3_vol) {
        this.out3_vol = out3_vol;
    }

    public Double getOut2() {
        return out2;
    }

    public void setOut2(Double out2) {
        this.out2 = out2;
    }

    public Integer getOut2_vol() {
        return out2_vol;
    }

    public void setOut2_vol(Integer out2_vol) {
        this.out2_vol = out2_vol;
    }

    public Double getOut1() {
        return out1;
    }

    public void setOut1(Double out1) {
        this.out1 = out1;
    }

    public Integer getOut1_vol() {
        return out1_vol;
    }

    public void setOut1_vol(Integer out1_vol) {
        this.out1_vol = out1_vol;
    }

    public Double getIn5() {
        return in5;
    }

    public void setIn5(Double in5) {
        this.in5 = in5;
    }

    public Integer getIn5_vol() {
        return in5_vol;
    }

    public void setIn5_vol(Integer in5_vol) {
        this.in5_vol = in5_vol;
    }

    public Double getIn4() {
        return in4;
    }

    public void setIn4(Double in4) {
        this.in4 = in4;
    }

    public Integer getIn4_vol() {
        return in4_vol;
    }

    public void setIn4_vol(Integer in4_vol) {
        this.in4_vol = in4_vol;
    }

    public Double getIn3() {
        return in3;
    }

    public void setIn3(Double in3) {
        this.in3 = in3;
    }

    public Integer getIn3_vol() {
        return in3_vol;
    }

    public void setIn3_vol(Integer in3_vol) {
        this.in3_vol = in3_vol;
    }

    public Double getIn2() {
        return in2;
    }

    public void setIn2(Double in2) {
        this.in2 = in2;
    }

    public Integer getIn2_vol() {
        return in2_vol;
    }

    public void setIn2_vol(Integer in2_vol) {
        this.in2_vol = in2_vol;
    }

    public Double getIn1() {
        return in1;
    }

    public void setIn1(Double in1) {
        this.in1 = in1;
    }

    public Integer getIn1_vol() {
        return in1_vol;
    }

    public void setIn1_vol(Integer in1_vol) {
        this.in1_vol = in1_vol;
    }
}
