package controller;

import bot.core.Realtime;
import model.News;
import model.Report;
import model.StockCurrent;
import model.StockInsText;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CommentService;
import service.InstructionTextService;
import service.StockDataService;
import service.impl.XueqiuCommentImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hermit on 2017/6/7.
 */
@Controller
@RequestMapping(value = "/stock")
public class StockDataController {

    @Resource
    private StockDataService stockDataService;
    @Resource
    private InstructionTextService instructionTextService;

    private CommentService xueqiuCommentService = new XueqiuCommentImpl();

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    public String toStock(@PathVariable("ID") String ID, Model model) throws Exception {
        model.addAttribute("ID", ID);

        List<News> newsList = Realtime.getRealNews(ID.toLowerCase());
        model.addAttribute("newsList", newsList);

        List<Report> reportList = Realtime.getRealReport(ID.toLowerCase());
        model.addAttribute("reportList", reportList);

        model.addAttribute("intraday", Realtime.getRealTicks(ID.toLowerCase()));

        return "stock";
    }

    @RequestMapping(value = "/{ID}/realtime", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRealtimeData(@PathVariable("ID") String ID, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();

        StockCurrent currentData = Realtime.getStockRealtime(ID.toLowerCase());
        map.put("currentData", currentData);

        StockInsText insText = instructionTextService.getStockAnalysis(currentData);
        map.put("instruction", insText);

        return map;
    }
}
