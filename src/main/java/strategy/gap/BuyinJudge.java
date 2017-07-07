package strategy.gap;

import model.BackTestRaw;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stonezhang on 2017/7/7.
 */
@Service("BuyinJudge")
public class BuyinJudge {

    public List<BackTestRaw> positiveWithVolume(List<BackTestRaw> data) {
        System.out.println(data);
        return null;
    }

    public List<BackTestRaw> postiveWithMa(List<BackTestRaw> data) {
        return null;
    }

    public List<BackTestRaw> continuousNegative(List<BackTestRaw> data) {
        return null;
    }
}
