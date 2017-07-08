package strategy.gap.impl;

import dao.DayKDAO;
import dao.MashDAO;
import model.BackTestRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import strategy.gap.SelloutJudgeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stonezhang on 2017/7/7.
 * 针对单只股票的所有历史数据，遍历并检查是否满足卖出要求，将所有满足要求的BackTestRaw返回
 */
@Service("SelloutJudgeService")
public class SelloutJudge implements SelloutJudgeService {

    @Autowired
    private DayKDAO dayKDAO;

    @Autowired
    private MashDAO mashDAO;

    public List<BackTestRaw> NegativeWithMa(List<BackTestRaw> data) {
        return new ArrayList<>();
    }

    public List<BackTestRaw> continousPositve(List<BackTestRaw> data) {
        return new ArrayList<>();
    }
}
