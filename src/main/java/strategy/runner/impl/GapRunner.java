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

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by stonezhang on 2017/7/7.
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

    /**
     * @param balance
     * @param trade
     * @return
     * 接受时间-可以买卖的股票数据list 的map，计算结果
     * 要求时间是以递增顺序的
     */
    private List<BackTest> calculate(double balance, Map<String, Set<BackTestRaw>> trade) {
        // trade key is date; value is all backtest data matches in the criteria

        // ware key is symbol

        Map<String, Integer> ware = new HashMap<>();
        List<BackTest> result = new ArrayList<>();
        for(String date: trade.keySet()) {
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
            result.add(new BackTest(balance, date));
        }

        return result;
    }

    /**
     *
     * @param balance
     * @return
     *  将股票id-股票数据list的map转化为时间-可以买卖的股票数据list 的map
     */
    public List<BackTest> runBacktest(double balance, Map<String, List<BackTestRaw>> data) {
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
        System.out.println("from strategy.GapRunner: " + trade);
        return calculate(balance, trade);
    }
}

