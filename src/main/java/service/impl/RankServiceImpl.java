package service.impl;

import bot.core.RankDataJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.StockRank;
import org.springframework.stereotype.Service;
import service.RankService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hermit on 2017/6/9.
 */
@Service("RankService")
public class RankServiceImpl implements RankService {

    private RankDataJson rankDataJson = new RankDataJson();

    @Override
    public List<StockRank> getDeviaUpRank() {
        String data = rankDataJson.getDeviaUpJsonData();
        return parse(data);
    }

    @Override
    public List<StockRank> getDeviaUp5MinutesRank() {
        String data = rankDataJson.getDeviaUp5MinutesJsonData();
        return parse(data);
    }

    @Override
    public List<StockRank> getTurnoverRank() {
        String data = rankDataJson.getTurnoverJsonData();
        return parse(data);
    }

    private List<StockRank> parse(String data) {
        List<StockRank> rankList = new ArrayList<>();

        JSONObject object = JSON.parseObject(data);
        JSONObject column = object.getJSONObject("Column");

        int idIndex = column.getInteger("id");
        int nameIndex = column.getInteger("name");
        int priceIndex = column.getInteger("np");
        int deviaIndex;
        if (column.containsKey("min5pl")) {
            deviaIndex = column.getInteger("min5pl");
        } else {
            deviaIndex = column.getInteger("pl");
        }

        JSONArray array = object.getJSONArray("HqData");

        for (int i=0;i<array.size();i++) {
            JSONArray array1 = array.getJSONArray(i);

            String symbol = array1.getString(idIndex).toUpperCase();
            String name = array1.getString(nameIndex);
            double price = array1.getDouble(priceIndex);
            double devia = array1.getDouble(deviaIndex);

            StockRank rank = new StockRank();
            rank.setSymbol(symbol);
            rank.setName(name);
            rank.setLastest_price(price);
            rank.setDevia_per(devia);

            rankList.add(rank);
        }

        return rankList;
    }
}
