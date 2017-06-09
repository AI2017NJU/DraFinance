package service;

import bot.core.RankDataJson;
import model.StockRank;

import java.util.List;

/**
 * Created by Hermit on 2017/6/9.
 */
public interface RankService {

    List<StockRank> getDeviaUpRank();

    List<StockRank> getDeviaUp5MinutesRank();

    List<StockRank> getTurnoverRank();
}
