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
import java.text.DecimalFormat;
import java.util.ArrayList;
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
    @Resource
    private SVMChangeService svmChangeService;
    @Resource
    private SVMPriceService svmPriceService;

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



        List<SVM> svmPredictionList = new ArrayList<>();
        SVM svm1 = new SVM();
        svm1.setDate("2017-06-30");
        svm1.setPrice_predict(32);
        svm1.setPrice_true(30);
        SVM svm2 = new SVM();
        svm2.setDate("2017-07-01");
        svm2.setPrice_predict(31);
        svm2.setPrice_true(28);
        svmPredictionList.add(svm1);
        svmPredictionList.add(svm2);
        //TODO
        //SVM预测的数据List，PO三个属性price_high, price_low, price_middle
        model.addAttribute("forecastData", JSON.toJSON(svmPredictionList));
//        model.addAttribute()

        SVMChange svmChange = new SVMChange();
        svmChange.setMa5(1);
        svmChange.setMa10(1);
        svmChange.setMa20(0);
        svmChange.setMa51020(1);
        svmChange.setMacd(0);
        svmChange.setKdj(0);
        svmChange.setRsi(1);

        model.addAttribute("svmChange",JSON.toJSON(svmChange));

        model.addAttribute("predictPrice",22);

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

    @RequestMapping(value = "/stock/{ID}/SVMChange", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSVMChange(@PathVariable("ID") String ID, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();

        SVMChange svmChange = new SVMChange();
        svmChange.setMa5(svmChangeService.predictByMa5Price(ID));
        svmChange.setMa10(svmChangeService.predictByMa10Price(ID));
        svmChange.setMa20(svmChangeService.predictByMa20Price(ID));
        svmChange.setMa51020(svmChangeService.predictByMa51020Price(ID));
        svmChange.setMacd(svmChangeService.predictByMacd(ID));
        svmChange.setKdj(svmChangeService.predictByKDJ(ID));
        svmChange.setRsi(svmChangeService.predictByRsi(ID));

        map.put("svmChange", svmChange);

        return map;
    }

    @RequestMapping(value = "/stock/{ID}/SVMPrice", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSVMPrice(@PathVariable("ID") String ID, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();

        double price = svmPriceService.predictByOCHL_Tomorrow(ID);
        String format_price = new DecimalFormat("#.00").format(price);
        map.put("svmPrice", format_price);

        return map;
    }

    @RequestMapping(value = "/stock/{ID}/SVMPriceList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSVMPriceList(@PathVariable("ID") String ID, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();

        List<SVM> svmList = svmPriceService.predictByOCHL_List(ID);

        map.put("svmPriceList", svmList);

        return map;
    }
}
