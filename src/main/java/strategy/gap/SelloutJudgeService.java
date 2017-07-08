package strategy.gap;

import model.BackTestRaw;

import java.util.List;

/**
 * Created by stonezhang on 2017/7/8.
 */
public interface SelloutJudgeService {
    List<BackTestRaw> NegativeWithMa(List<BackTestRaw> data);

    List<BackTestRaw> continousPositve(List<BackTestRaw> data);
}
