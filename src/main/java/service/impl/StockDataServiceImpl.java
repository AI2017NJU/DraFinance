package service.impl;

import dao.DayKDAO;
import dao.MashDAO;
import dao.StockInfoDAO;
import model.DayK;
import model.Mash;
import model.StockInfo;
import org.springframework.stereotype.Service;
import service.StockDataService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Hermit on 2017/6/7.
 */
@Service("StockDataService")
public class StockDataServiceImpl implements StockDataService {

    @Resource
    private DayKDAO dayKDAO;
    @Resource
    private StockInfoDAO stockInfoDAO;
    @Resource
    private MashDAO mashDAO;

    @Override
    public List<DayK> getDayKData(String ID) {
        return dayKDAO.findDayKList(ID);
    }

    @Override
    public StockInfo getStockInfo(String ID) {
        return stockInfoDAO.findStockInfoBySymbol(ID);
    }

    @Override
    public List<StockInfo> getAllStocks() {
        return stockInfoDAO.findStockInfoList();
    }

    @Override
    public List<Mash> getQuotaData(String ID) {
        return mashDAO.findBySymbol(ID);
    }
}
