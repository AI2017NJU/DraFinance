package dao;

import model.DayK;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by stonezhang on 2017/6/8.
 */
public interface DayKDAO {
    List<DayK> findDayKList(String symbol);
    List<DayK> findDayKListWithTimeRange(@Param("symbol") String symbol,
                                         @Param("startDate") String startDate,
                                         @Param("endDate") String endDate);
    List<DayK> findDayKListYear(String symbol);
}
