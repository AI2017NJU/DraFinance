package controller;

import bot.core.Realtime;
import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CommentService;
import service.StockDataService;
import service.impl.XueqiuCommentImpl;
import tools.StockHelper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hermit on 2017/6/8.
 */
@Controller
public class BenchDataController {

    @Resource
    private StockDataService stockDataService;

    private CommentService xueqiuCommentService = new XueqiuCommentImpl();

    @RequestMapping(value = "/bench/{ID}", method = RequestMethod.GET)
    public String toBench(@PathVariable("ID") String ID, Model model) {

        if (!StockHelper.isBench(ID)) {
            return "redirect:/stock/" + ID;
        }

        List<StockInfo> stockList = stockDataService.getAllStocks();
        model.addAttribute("stockList", JSON.toJSON(stockList));

        StockInfo stockInfo = stockDataService.getStockInfo(ID);
        model.addAttribute("stockInfo", stockInfo);

        List<DayK> dayKList = stockDataService.getDayKData(ID);
        model.addAttribute("dayKList", JSON.toJSON(dayKList));

        model.addAttribute("intraday", Realtime.getRealTicks(ID.toLowerCase()));

        return "bench";
    }

    @RequestMapping(value = "/bench/{ID}/realtime", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRealTimeData(@PathVariable("ID") String ID, Model model) throws Exception {
        Map<String, Object> map = new HashMap<>();

        BenchCurrent benchCurrent = Realtime.getBenchCurrent(ID.toLowerCase());
        map.put("currentData", benchCurrent);

        return map;
    }
}
