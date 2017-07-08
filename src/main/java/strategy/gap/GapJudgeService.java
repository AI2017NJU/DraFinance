package strategy.gap;

import model.BackTestRaw;

/**
 * Created by stonezhang on 2017/7/8.
 */
public interface GapJudgeService {
    boolean isPositiveGap(BackTestRaw start, BackTestRaw end);

    boolean isNegativeGap(BackTestRaw start, BackTestRaw end);
}
