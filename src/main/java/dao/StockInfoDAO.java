package dao;

import model.StockInfo;

import java.util.List;

/**
 * Created by stonezhang on 2017/6/8.
 */
public interface StockInfoDAO {
    StockInfo findStockInfoBySymbol(String symbol);
    StockInfo findStockInfoByName(String name);
    List<StockInfo> findStockInfoList();
}
