package controller;

import com.alibaba.fastjson.JSON;
import model.StockInfo;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import service.StockDataService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Hermit on 2017/6/11.
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    @Resource
    private StockDataService stockDataService;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        List<StockInfo> stockList = stockDataService.getAllStocks();

        ModelAndView view = new ModelAndView("exception");
        view.addObject("stockList", JSON.toJSON(stockList));

        return view;
    }
}
