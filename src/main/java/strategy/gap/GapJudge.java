package strategy.gap;

import model.BackTest;
import model.BackTestRaw;
import model.DayK;
import org.springframework.stereotype.Service;

/**
 * Created by stonezhang on 2017/7/7.
 */
@Service("GapJudge")
public class GapJudge {

    public boolean isPositiveGap(BackTestRaw start, BackTestRaw end) {
        return (start.getHigh() < end.getLow());
    }

    public boolean isNegativeGap(BackTestRaw start, BackTestRaw end) {
        return (start.getLow() > end.getHigh());
    }
}
