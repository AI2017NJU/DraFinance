package controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.BackTestService;
import model.BackTest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.BackTestService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hermit on 2017/7/7.
 */
@Controller
public class BackTestController {

    @Resource
    private BackTestService backTestService;

    @RequestMapping(value = "/backtest", method = RequestMethod.GET)
    public String toBackTest(HttpServletRequest request, Model model) {
        return "backtest";
    }

    @RequestMapping(value = "/backtest/data", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBacktestData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();

        String balance = request.getParameter("balance");
        String start = request.getParameter("start");
        String end = request.getParameter("end");

        List<BackTest> backTestList = backTestService.getBacktestResult(Double.parseDouble(balance),
                start, end);

        map.put("data", backTestList);

        return map;
    }
}
