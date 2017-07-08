package strategy.gap.impl;

import dao.DayKDAO;
import dao.MashDAO;
import model.BackTestRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import strategy.gap.GapJudgeService;
import strategy.gap.SelloutJudgeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stonezhang on 2017/7/7.
 * 针对单只股票的所有历史数据，遍历并检查是否满足卖出要求，将所有满足要求的BackTestRaw返回
 */
@Service("SelloutJudgeService")
public class SelloutJudge implements SelloutJudgeService {
    GapJudgeService gapJudgeService = new GapJudge();

    public List<BackTestRaw> NegativeWithMa(List<BackTestRaw> data) {
        List<BackTestRaw> result = new ArrayList<>();
        int length = data.size();
        for(int i=1;i<length;i++){
            BackTestRaw today = data.get(i);
            BackTestRaw before = data.get(i-1);
            double lower = (today.getOpen()>today.getClose())?today.getClose():today.getOpen();
            double higher = (before.getOpen()>before.getClose())?before.getOpen():before.getClose();
            if(lower<today.getMa20()&&higher>before.getMa20()&&gapJudgeService.isPositiveGap(before,today)){
                today.setState(2);
                result.add(today);
            }
        }
        return result;
    }

    public List<BackTestRaw> continousPositve(List<BackTestRaw> data) {
        List<BackTestRaw> result = new ArrayList<>();
        int length = data.size();
        int[] temp = new int[length];
        int count = 0;
        for(int i=1;i<length;i++){
            BackTestRaw today = data.get(i);
            BackTestRaw before = data.get(i-1);
            if(gapJudgeService.isPositiveGap(before,today)){
                temp[count] = i;
                count++;
            }
        }
        for(int i=2;i<count;i++){
            if(temp[i]-temp[i-2]<=40){
                data.get(temp[i]).setState(2);
                result.add(data.get(temp[i]));
            }
        }
        return result;
    }
}
