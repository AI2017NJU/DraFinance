package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.BackTestService;

/**
 * Created by Hermit on 2017/7/7.
 */
@Controller
public class BackTestController {
    @Autowired
    private BackTestService backTestService;

    @RequestMapping(value = "backtesting", method = RequestMethod.GET)
    public String getPage() {
        backTestService.getBacktestResult(10000, "2017-01-01", "2017-07-01");
        return "index";
    }

}
