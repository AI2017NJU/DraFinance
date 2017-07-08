package strategy.gap.impl;

import model.BackTest;
import model.BackTestRaw;
import model.DayK;
import org.apache.ibatis.javassist.bytecode.CodeIterator;
import org.springframework.stereotype.Service;
import strategy.gap.GapJudgeService;

/**
 * Created by stonezhang on 2017/7/7.
 */
@Service("GapJudgeService")
public class GapJudge implements GapJudgeService {

    public boolean isPositiveGap(BackTestRaw start, BackTestRaw end) {
        return (start.getHigh() < end.getLow());
    }

    public boolean isNegativeGap(BackTestRaw start, BackTestRaw end) {
        return (start.getLow() > end.getHigh());
    }
}
