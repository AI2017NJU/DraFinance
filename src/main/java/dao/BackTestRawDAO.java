package dao;

import model.BackTestRaw;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by stonezhang on 2017/7/7.
 */
public interface BackTestRawDAO {
    BackTestRaw find(@Param("symbol") String symbol,
                     @Param("date") String date);
    List<BackTestRaw> findNameRange(@Param("startSymbol") String startSymbol,
                           @Param("endSymbol") String endSymbol,
                           @Param("date") String date);
    List<BackTestRaw> findTimeRange(@Param("symbol") String symbol,
                                    @Param("startDate") String startDate,
                                    @Param("endDate") String endDate);
}
