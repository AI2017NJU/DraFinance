package strategy.runner;

import model.BackTest;
import model.BackTestRaw;

import java.util.List;
import java.util.Map;

/**
 * Created by stonezhang on 2017/7/8.
 */
public interface GapRunnerService {
    public List<BackTest> runBacktest(double balance, String startDate, String endDate,
                                      Map<String, List<BackTestRaw>> data);
}
