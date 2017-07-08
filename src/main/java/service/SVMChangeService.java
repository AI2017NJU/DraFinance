package service;

import model.DayK;

import java.util.List;

/**
 * Created by christine on 2017/7/8.
 */
public interface SVMChangeService {

    /**根据Ma5来预测*/
    public void predictByMa5Price();

    /**根据Ma10来预测*/
    public void predictByMa10Price();

    /**根据Ma20来预测*/
    public void predictByMa20Price();

    /**根据Ma5、Ma10、Ma20来预测*/
    public void predictByMa51020Price();

    /**根据Macd、Diff、Dea来预测*/
    public void predictByMacd();

    /**根据K、D、J来预测*/
    public void predictByKDJ();

    /**根据Rsi1、Rsi2、Rsi3来预测*/
    public void predictByRsi();

}
