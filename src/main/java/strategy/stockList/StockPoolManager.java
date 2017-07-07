package strategy.stockList;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stonezhang on 2017/7/7.
 */
@Service("StockPoolManager")
public class StockPoolManager {
    public List<String> getStockPool() {
        return Arrays.asList("SH600000",
                "SH600004",
                "SH600006",
                "SH600007",
                "SH600008",
                "SH600009",
                "SH600010",
                "SH600011",
                "SH600012",
                "SH600015",
                "SH600016",
                "SH600017",
                "SH600018",
                "SH600019",
                "SH600020",
                "SH600021",
                "SH600022",
                "SH600023",
                "SH600026",
                "SH600027",
                "SH600028");
    }
}
