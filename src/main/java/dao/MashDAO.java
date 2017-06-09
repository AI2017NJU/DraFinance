package dao;

import model.Mash;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by stonezhang on 2017/6/9.
 */
public interface MashDAO {
    List<Mash> findBySymbol(String symbol);
    List<Mash> findBySymbolAndRange(@Param("symbol") String symbol,
                                    @Param("startDate") String startDate,
                                    @Param("endDate") String endDate);
    Mash findBySymbolAndDate(@Param("symbol") String symbol,
                             @Param("date") String date);
}
