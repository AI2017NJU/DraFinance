package strategy.runner.impl;

import dao.BackTestRawDAO;
import model.BackTest;
import model.BackTestRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import strategy.gap.BuyInJudgeService;
import strategy.gap.SelloutJudgeService;
import strategy.gap.impl.BuyinJudge;
import strategy.gap.impl.SelloutJudge;
import strategy.runner.GapRunnerService;
import strategy.stockList.StockPoolManagerService;
import strategy.stockList.impl.StockPoolManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by stonezhang on 2017/7/7.
 * 可能的bug位置
 * calculate函数的ware的维护
 * runBacktest函数的trade的维护，特别是取出map里已经有的set然后向里面加元素的可行性，以及set和list之间的转换
 */
@Service("GapRunnerService")
public class GapRunner implements GapRunnerService {
//    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Autowired
    private StockPoolManagerService stockPoolManagerService;

    @Autowired
    private BuyInJudgeService buyInJudgeService;

    @Autowired
    private SelloutJudgeService selloutJudgeService;

    private DateTimeFormatter common = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * @param balance
     * @param trade
     * @return
     * 接受时间-可以买卖的股票数据list 的map，计算结果
     * 要求时间是以递增顺序的
     */
    private List<BackTest> calculate(double balance, String startDate, String endDate,
                                     Map<String, Set<BackTestRaw>> trade,
                                     Map<String, List<BackTestRaw>> priceList) {
        // trade key is date; value is all backtest data matches in the criteria

        // ware key is symbol

        Map<String, Integer> ware = new HashMap<>();
        List<BackTest> result = new ArrayList<>();
        LocalDate start = LocalDate.parse(startDate, common);
        LocalDate end = LocalDate.parse(endDate, common);

        double value = 0;

        while (start.isBefore(end)) {
            value = balance;
            String date = start.format(common);
            if(trade.containsKey(date)) {
                for(BackTestRaw backTestRaw: trade.get(date)) {
                    if(backTestRaw.getState() == 1 && balance > 100 * backTestRaw.getOpen()) {
                        // buy in
                        balance -= 100 * backTestRaw.getOpen();
                        if(ware.containsKey(backTestRaw.getSymbol())) {
                            ware.put(backTestRaw.getSymbol(), ware.get(backTestRaw.getSymbol()) + 1);
                        }
                        else {
                            ware.put(backTestRaw.getSymbol(), 1);
                        }

                    }
                    else if(backTestRaw.getState() == 2 && ware.get(backTestRaw.getSymbol()) != null
                            && ware.get(backTestRaw.getSymbol()) > 1) {
                        balance += 100 * backTestRaw.getOpen();
                        ware.put(backTestRaw.getSymbol(), ware.get(backTestRaw.getSymbol()) - 1);
                    }
                }
            }

//            System.out.println("from calculate loop: " + date);
            for(String symbol: priceList.keySet()) {
                if(ware.keySet().contains(symbol)) {
                    for(BackTestRaw backTestRaw: priceList.get(symbol)) {
                        if(backTestRaw.getDate().equals(date)) {
                            value += backTestRaw.getOpen() * ware.get(symbol);
                            break;
                        }
                    }
                }

            }

            result.add(new BackTest(balance, value, date));
            start = start.plusDays(1);
        }
        System.out.println("from GapRunner.calculate: " + result);
        return result;
    }

    /**
     *
     * @param balance
     * @return
     *  将股票id-股票数据list的map转化为时间-可以买卖的股票数据list 的map
     */
    public List<BackTest> runBacktest(double balance, String startDate, String endDate,
                                      Map<String, List<BackTestRaw>> data) {
        BuyinJudge buyinJudge = new BuyinJudge();
        SelloutJudge selloutJudge = new SelloutJudge();
        System.out.println("from strategy.GapRunner: " + data);

        Map<String, Set<BackTestRaw>> trade = new HashMap<>();

        for (List<BackTestRaw> symbolData : data.values()) {
            Set<BackTestRaw> dayTrade = new HashSet<>();
//            dayTrade.addAll(buyInJudgeService.continuousNegative(symbolData));
//            dayTrade.addAll(buyInJudgeService.positiveWithVolume(symbolData));
//            dayTrade.addAll(buyInJudgeService.postiveWithMa(symbolData));
//            dayTrade.addAll(selloutJudgeService.NegativeWithMa(symbolData));
//            dayTrade.addAll(selloutJudgeService.continousPositve(symbolData));

            dayTrade.addAll(buyinJudge.continuousNegative(symbolData));
            dayTrade.addAll(buyinJudge.positiveWithVolume(symbolData));
            dayTrade.addAll(buyinJudge.postiveWithMa(symbolData));
            dayTrade.addAll(selloutJudge.NegativeWithMa(symbolData));
            dayTrade.addAll(selloutJudge.continousPositve(symbolData));

            for (BackTestRaw backTestRaw : dayTrade) {
                if (trade.containsKey(backTestRaw.getDate())) {
                    trade.get(backTestRaw.getDate()).add(backTestRaw);
                } else {
                    trade.put(backTestRaw.getDate(), new HashSet() {{
                        add(backTestRaw);
                    }});
                }
            }
        }
        System.out.println("from strategy.GapRunner trade result: " + trade);
        return calculate(balance, startDate, endDate, trade, data);
    }
}

