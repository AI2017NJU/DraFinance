package service.impl;

import bot.core.IndustryJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Industry;
import org.springframework.stereotype.Service;
import service.IndustryService;
import tools.StockHelper;

/**
 * Created by Hermit on 2017/6/9.
 */
@Service("IndustryService")
public class IndustryServiceImpl implements IndustryService {

    private IndustryJson industryJson = new IndustryJson();

    @Override
    public Industry getIndustry(String ID) {
        ID = StockHelper.baiduCodeReflect(ID);
        String jsonData = industryJson.getJsonData(ID);

        JSONObject jsonObject = JSON.parseObject(jsonData);

        Industry industry = new Industry();
        industry.setName(jsonObject.getString("industry"));
        industry.setDescription(jsonObject.getString("mainBusiness"));

        return industry;
    }
}
