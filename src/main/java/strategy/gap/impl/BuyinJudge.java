package strategy.gap.impl;

import model.BackTestRaw;
import org.springframework.stereotype.Service;
import strategy.gap.BuyInJudgeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stonezhang on 2017/7/7.
 * 针对单只股票的所有历史数据，遍历并检查是否满足买入要求，将所有满足要求的BackTestRaw返回
 */
@Service("BuyinJudgeService")
public class BuyinJudge implements BuyInJudgeService {

    public List<BackTestRaw> positiveWithVolume(List<BackTestRaw> data) {
//        System.out.println("from buyinJudge: " + data);
        return new ArrayList<>();
    }

    public List<BackTestRaw> postiveWithMa(List<BackTestRaw> data) {
        return new ArrayList<>();
    }

    public List<BackTestRaw> continuousNegative(List<BackTestRaw> data) {
        return new ArrayList<>();
    }
}
