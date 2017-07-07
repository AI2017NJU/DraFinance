package service.impl;

import dao.DayKDAO;
import dao.MashDAO;
import model.BackTest;
import model.BackTestRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BackTestService;
import strategy.runner.GapRunner;
import strategy.stockList.StockPoolManager;

import java.time.LocalDate;
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

    @Override
    public List<BackTest> getBacktestResult(double balance, String startDate, String endDate) {
        GapRunner gapRunner = new GapRunner();
        return gapRunner.runBacktest(balance, startDate, endDate);
    }

}
