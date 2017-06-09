package service;


import model.DayK;
import model.Mash;
import model.MashEvent;

import java.util.List;

/**
 * Created by linyufan on 16/5/21.
 * 标签式图表,图示分析
 * kdj rsi boll
 */
public interface MashEventService {

    /**
     * 获取标签式图表的解析
     *
     */
    List<MashEvent> getMashEvents(String ID);

    List<MashEvent> getMashEvents(List<DayK> dayKList, List<Mash> mashList);
}
