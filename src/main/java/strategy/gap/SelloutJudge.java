package strategy.gap;

import dao.DayKDAO;
import dao.MashDAO;
import model.BackTestRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stonezhang on 2017/7/7.
 */
@Service("SelloutJudge")
public class SelloutJudge {

    @Autowired
    private DayKDAO dayKDAO;

    @Autowired
    private MashDAO mashDAO;

    public List<BackTestRaw> NegativeWithMa(List<BackTestRaw> data) {
        return null;
    }

    public List<BackTestRaw> continousPositve(List<BackTestRaw> data) {
        return null;
    }
}
