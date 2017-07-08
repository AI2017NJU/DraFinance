package service.impl;

import dao.DayKDAO;
import dao.MashDAO;
import model.BackTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BackTestService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stonezhang on 2017/7/7.
 */
@Service("BackTestService")
public class BackTestServiceImpl implements BackTestService {
    @Autowired
    private DayKDAO dayKDAO;

    @Autowired
    private MashDAO mashDAO;

    private DateTimeFormatter common = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    @Override
    public List<BackTest> getBacktestResult(double balance, String startDate, String endDate) {

        LocalDate start = LocalDate.parse(startDate, common);
        LocalDate end = LocalDate.parse(endDate, common);

        List<BackTest> result = new ArrayList<>();
        double revenue = balance;
        while (start.isBefore(end)) {
            revenue += ((Math.pow(-1, (int)(10 * Math.random()))) * Math.random() * 1000);
            result.add(new BackTest(revenue, start.format(common)));
            start = start.plusDays(1);
        }

        return result;

//        GapRunner gapRunner = new GapRunner();
//        return gapRunner.runBacktest(balance, startDate, endDate);
    }

}
