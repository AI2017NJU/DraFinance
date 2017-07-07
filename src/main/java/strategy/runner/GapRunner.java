package strategy.runner;

import dao.BackTestRawDAO;
import dao.DayKDAO;
import dao.MashDAO;
import model.BackTest;
import model.BackTestRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import strategy.gap.BuyinJudge;
import strategy.gap.GapJudge;
import strategy.gap.SelloutJudge;
import strategy.stockList.StockPoolManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by stonezhang on 2017/7/7.
 */
@Service("GapRunner")
public class GapRunner {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private BackTestRawDAO backTestRawDAO;

    @Autowired
    private StockPoolManager stockPoolManager;

    @Autowired
    private GapJudge gapJudge;

    @Autowired
    private BuyinJudge buyinJudge;

    @Autowired
    private SelloutJudge selloutJudge;

    private Map<String, List<BackTestRaw>> pack(String startDate, String endDate) {
//        StockPoolManager stockPoolManager = new StockPoolManager();

        Map<String, List<BackTestRaw>> result = new HashMap<>();
        List<String> idPool = stockPoolManager.getStockPool();
        for(String symbol: idPool) {
            result.put(symbol, backTestRawDAO.findTimeRange(symbol, startDate, endDate));
        }
        return result;
    }

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

    public List<BackTest> runBacktest(double balance, String startDate, String endDate) {
        Map<String, List<BackTestRaw>> data = pack(startDate, endDate);

        Map<String, Set<BackTestRaw>> trade = new HashMap<>();

        for (List<BackTestRaw> symbolData : data.values()) {
            Set<BackTestRaw> dayTrade = new HashSet<>();
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
        return calculate(balance, trade);
    }
}

