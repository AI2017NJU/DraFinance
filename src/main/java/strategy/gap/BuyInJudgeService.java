package strategy.gap;

import model.BackTestRaw;

import java.util.List;

/**
 * Created by stonezhang on 2017/7/8.
 */
public interface BuyInJudgeService {
    List<BackTestRaw> positiveWithVolume(List<BackTestRaw> data);

    List<BackTestRaw> postiveWithMa(List<BackTestRaw> data);

    List<BackTestRaw> continuousNegative(List<BackTestRaw> data);
}
