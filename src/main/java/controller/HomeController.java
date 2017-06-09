package controller;

import bot.core.Realtime;
import bot.core.TotalNews;
import com.alibaba.fastjson.JSON;
import model.Hotspot;
import model.News;
import model.StockInfo;
import model.StockRank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.RankService;
import service.StockDataService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Hermit on 2017/6/7.
 */
@Controller
public class HomeController {

    @Resource
    private StockDataService stockDataService;
    @Resource
    private RankService rankService;

    public static final String BENCH_CODE = "sh000001";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request, Model model) {

        List<StockInfo> stockList = stockDataService.getAllStocks();
        model.addAttribute("stockList", JSON.toJSON(stockList));

        model.addAttribute("intraday", Realtime.getRealTicks(BENCH_CODE));

        List<News> stockNews = TotalNews.getStockNews();
        model.addAttribute("stockNews", stockNews);

        List<News> financeNews = TotalNews.getFinanceNews();
        model.addAttribute("financeNews", financeNews);

        List<News> companyNews = TotalNews.getCompanyNews();
        model.addAttribute("companyNews", companyNews);

        List<Hotspot> hotspots = TotalNews.getHotspot();
        model.addAttribute("hotspots", hotspots);

        List<StockRank> deviaRankList = rankService.getDeviaUpRank();
        model.addAttribute("deviaRankList", deviaRankList);

        List<StockRank> devia5RankList = rankService.getDeviaUp5MinutesRank();
        model.addAttribute("devia5RankList", devia5RankList);

        List<StockRank> turnoverList = rankService.getTurnoverRank();
        model.addAttribute("turnoverList", turnoverList);

        return "index";
    }
}
