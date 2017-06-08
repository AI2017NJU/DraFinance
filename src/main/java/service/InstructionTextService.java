package service;


import model.StockCurrent;
import model.StockInsText;

/**
 * Created by zcj on 16/5/7.
 * 股票投资指导接口,提供文字以及建模分析
 *
 */
public interface InstructionTextService {

    /**
     * 股票文字分析
     * @return 文字分析+
     */
    StockInsText getStockAnalysis(String id) throws Exception;

    StockInsText getStockAnalysis(StockCurrent currentInfo) throws Exception;

}
