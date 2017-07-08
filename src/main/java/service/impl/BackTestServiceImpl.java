package service.impl;

import dao.BackTestRawDAO;
import dao.DayKDAO;
import dao.MashDAO;
import model.BackTest;
import model.BackTestRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BackTestService;

import strategy.runner.impl.GapRunner;
import strategy.stockList.impl.StockPoolManager;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

/**
 * Created by stonezhang on 2017/7/7.
 */
@Service("BackTestService")
public class BackTestServiceImpl implements BackTestService {
    @Autowired
    private DayKDAO dayKDAO;

    @Autowired
    private MashDAO mashDAO;

    @Autowired
    private BackTestRawDAO backTestRawDAO;

    @Override
    public List<BackTest> getBacktestResult(double balance, String startDate, String endDate) {
        GapRunner gapRunner = new GapRunner();
        return gapRunner.runBacktest(balance, pack(startDate, endDate));
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     * 将起止时间内的基本数据以股票代码和数据list的映射形式返回
     */
    private Map<String, List<BackTestRaw>> pack(String startDate, String endDate) {
        StockPoolManager stockPoolManager = new StockPoolManager();

        Map<String, List<BackTestRaw>> result = new HashMap<>();
//        List<String> idPool = stockPoolManagerService.getStockPool();
        List<String> idPool = stockPoolManager.getStockPool();
        System.out.println("from backtestService: " + idPool);
        for (String symbol : idPool) {
            result.put(symbol, backTestRawDAO.findTimeRange(symbol, startDate, endDate));
        }
        return result;
    }
//    private DateTimeFormatter common = DateTimeFormatter.ofPattern("uuuu-MM-dd");
//
//    @Override
//    public List<BackTest> getBacktestResult(double balance, String startDate, String endDate) {
//
//        LocalDate start = LocalDate.parse(startDate, common);
//        LocalDate end = LocalDate.parse(endDate, common);
//
//        List<BackTest> result = new ArrayList<>();
//        double revenue = balance;
//        while (start.isBefore(end)) {
//            revenue += ((Math.pow(-1, (int)(10 * Math.random()))) * Math.random() * 1000);
//            result.add(new BackTest(revenue, start.format(common)));
//            start = start.plusDays(1);
//        }
//
//        return result;
//
////        GapRunner gapRunner = new GapRunner();
////        return gapRunner.runBacktest(balance, startDate, endDate);
//
//    }


}
