package controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import service.StockDataService;

import javax.annotation.Resource;

/**
 * Created by Hermit on 2017/6/7.
 */
@Controller
@RequestMapping(value = "/stock")
public class StockDataController {

    @Resource
    private StockDataService stockDataService;


}
