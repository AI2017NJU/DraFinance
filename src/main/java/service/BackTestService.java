package service;

import model.BackTest;

import java.util.List;

/**
 * Created by stonezhang on 2017/7/7.
 */
public interface BackTestService {
    public List<BackTest> getBacktestResult(double balance, String startDate, String endDate);
}
