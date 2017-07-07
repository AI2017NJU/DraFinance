package strategy.gap;

import model.DayK;

/**
 * Created by stonezhang on 2017/7/7.
 */
public class GapJudge {

    public boolean isPositiveGap(DayK start, DayK end) {
        return (start.getHigh() < end.getLow());
    }

    public boolean isNegativeGap(DayK start, DayK end) {
        return (start.getLow() > end.getHigh());
    }
}
