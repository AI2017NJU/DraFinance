package bot.wrapper;

import bot.core.DayKJson;
import bot.core.DayKParser;
import model.DayK;

import java.util.List;
import java.util.Map;

/**
 * Created by stonezhang on 2017/5/27.
 */
public class DayKWrapper implements Wrapper {
    @Override
    public void scrapeToMysql(String user, String password, String schema) {
        String url = "https://xueqiu.com/stock/cata/stocklist.json?page=1&size=30&order=desc&orderby=percent&type=11%2C12&_=1496029395792";
        List<DayK> res = DayKJson.demo(url);
        System.out.println(res);
    }
}
