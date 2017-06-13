package dao;

import model.BpPrediction;

import java.util.List;

/**
 * Created by stonezhang on 2017/6/13.
 */
public interface BpPredictionDAO {
    List<BpPrediction> findBySymbol(String symbol);
}
