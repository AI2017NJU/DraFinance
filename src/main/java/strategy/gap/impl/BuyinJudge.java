package strategy.gap.impl;

import model.BackTestRaw;
import org.springframework.stereotype.Service;
import strategy.gap.BuyInJudgeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stonezhang on 2017/7/7.
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
