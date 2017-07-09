package service;

import model.DayK;

import java.util.List;

/**
 * Created by christine on 2017/7/8.
 */
public interface SVMChangeService {

    /**根据Ma5来预测*/
    public int predictByMa5Price(String stock_id);

    /**根据Ma10来预测*/
    public int predictByMa10Price(String stock_id);

    /**根据Ma20来预测*/
    public int predictByMa20Price(String stock_id);

    /**根据Ma5、Ma10、Ma20来预测*/
    public int predictByMa51020Price(String stock_id);

    /**根据Macd、Diff、Dea来预测*/
    public int predictByMacd(String stock_id);

    /**根据K、D、J来预测*/
    public int predictByKDJ(String stock_id);

    /**根据Rsi1、Rsi2、Rsi3来预测*/
    public int predictByRsi(String stock_id);

}
