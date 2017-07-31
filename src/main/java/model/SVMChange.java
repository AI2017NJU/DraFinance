package model;

/**
 * Created by christine on 2017/7/9.
 */
public class SVMChange {
    private String ma5;
    private String ma10;
    private String ma20;
    private String ma51020;
    private String macd;
    private String kdj;
    private String rsi;


    public String getMa5() {
        return ma5;
    }

    public void setMa5(int ma5) {
        this.ma5 = getDescription(ma5);
    }

    public String getMa10() {
        return ma10;
    }

    public void setMa10(int ma10) {
        this.ma10 = getDescription(ma10);
    }

    public String getMa20() {
        return ma20;
    }

    public void setMa20(int ma20) {
        this.ma20 = getDescription(ma20);
    }

    public String getMa51020() {
        return ma51020;
    }

    public void setMa51020(int ma51020) {
        this.ma51020 = getDescription(ma51020);
    }

    public String getMacd() {
        return macd;
    }

    public void setMacd(int macd) {
        this.macd = getDescription(macd);
    }

    public String getKdj() {
        return kdj;
    }

    public void setKdj(int kdj) {
        this.kdj = getDescription(kdj);
    }

    public String getRsi() {
        return rsi;
    }

    public void setRsi(int rsi) {
        this.rsi = getDescription(rsi);
    }


    private String getDescription(int i){
        switch (i){
            case 1:
                return "涨";
            case 0:
                return "跌";
            case -1:
                return "平";
            default:
                return "无数据";
        }
    }
}
