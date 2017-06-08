package controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CommentService;
import service.StockDataService;
import service.impl.XueqiuCommentImpl;

import javax.annotation.Resource;

/**
 * Created by Hermit on 2017/6/7.
 */
@Controller
@RequestMapping(value = "/stock")
public class StockDataController {

    @Resource
    private StockDataService stockDataService;

    private CommentService xueqiuCommentService = new XueqiuCommentImpl();

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    public String toStock(@PathVariable("ID") String ID, Model model) {
        model.addAttribute("ID", ID);
        return "stock";
    }
}
