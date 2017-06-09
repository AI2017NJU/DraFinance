package service;

import model.DayK;
import model.Mash;
import model.StockInfo;

import java.util.List;

/**
 * Created by Hermit on 2017/6/7.
 */
public interface StockDataService {

    List<DayK> getDayKData(String ID);

    StockInfo getStockInfo(String ID);

    List<StockInfo> getAllStocks();

    List<Mash> getQuotaData(String ID);
}
