package strategy.gap;

import dao.DayKDAO;
import dao.MashDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by stonezhang on 2017/7/7.
 */
public class SelloutJudge {

    @Autowired
    private DayKDAO dayKDAO;

    @Autowired
    private MashDAO mashDAO;

    public boolean NegativeWithMa(String date) {
        return false;
    }

    public boolean continousPositve(String date) {
        return false;
    }
}
