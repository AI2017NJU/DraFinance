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
import service.*;
import service.impl.GubaCommentImpl;
import service.impl.XueqiuCommentImpl;
import tools.StockHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hermit on 2017/6/7.
 */
@Controller
public class StockDataController {

    @Resource
    private StockDataService stockDataService;
    @Resource
    private InstructionTextService instructionTextService;
    @Resource
    private IndustryService industryService;
    @Resource
    private MashEventService mashEventService;

    private CommentService xueqiuCommentService = new XueqiuCommentImpl();
    private CommentService gubaCommentSerivce = new GubaCommentImpl();

    @RequestMapping(value = "/stock/{ID}", method = RequestMethod.GET)
    public String toStock(@PathVariable("ID") String ID, Model model) throws Exception {

        if (StockHelper.isBench(ID)) {
            return "redirect:/bench/" + ID;
        }

        List<StockInfo> stockList = stockDataService.getAllStocks();
        model.addAttribute("stockList", JSON.toJSON(stockList));

        StockInfo stockInfo = stockDataService.getStockInfo(ID);
        model.addAttribute("stockInfo", stockInfo);

        List<DayK> dayKList = stockDataService.getDayKData(ID);
        model.addAttribute("dayKList", JSON.toJSON(dayKList));

        List<News> newsList = Realtime.getRealNews(ID.toLowerCase());
        model.addAttribute("newsList", newsList);

        List<Report> reportList = Realtime.getRealReport(ID.toLowerCase());
        model.addAttribute("reportList", reportList);

        model.addAttribute("intraday", Realtime.getRealTicks(ID.toLowerCase()));

        List<Comment> xueqiuCommentList = xueqiuCommentService.getCurrentComments(ID);
        model.addAttribute("xueqiuCommentList", xueqiuCommentList);

//        List<Comment> gubaCommentList = gubaCommentSerivce.getCurrentComments(ID);
//        model.addAttribute("gubaCommentList", xueqiuCommentList);

        Industry industry = industryService.getIndustry(ID);
        model.addAttribute("industry", industry);

        List<Mash> mashList = stockDataService.getQuotaData(ID);
        model.addAttribute("mashList", JSON.toJSON(mashList));

        List<MashEvent> mashEventList = mashEventService.getMashEvents(dayKList, mashList);
        model.addAttribute("mashEventList", JSON.toJSON(mashEventList));

        StockInIndustry stockInIndustry = Realtime.getStockInIndustry(ID.toLowerCase());
        model.addAttribute("stockInIndustry", stockInIndustry);

        List<BpPrediction> bpPredictionList = stockDataService.getBpPredictionData(ID);
        model.addAttribute("bpPredictionList", JSON.toJSON(bpPredictionList));

        return "stock";
    }

    @RequestMapping(value = "/stock/{ID}/realtime", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRealTimeData(@PathVariable("ID") String ID, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();

        StockCurrent currentData = Realtime.getStockRealtime(ID.toLowerCase());
        map.put("currentData", currentData);

        StockInsText insText = instructionTextService.getStockAnalysis(currentData);
        map.put("instruction", insText);

        return map;
    }
}
