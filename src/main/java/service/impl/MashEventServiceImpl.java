package service.impl;

import model.DayK;
import model.Mash;
import model.MashEvent;
import org.springframework.stereotype.Service;
import service.MashEventService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hermit on 2017/6/9.
 */
@Service("MashEventService")
public class MashEventServiceImpl implements MashEventService {


    @Override
    public List<MashEvent> getMashEvents(String ID) {
        return null;
    }

    @Override
    public List<MashEvent> getMashEvents(List<DayK> dayKList, List<Mash> mashList) {
        if (dayKList.size() < 30 || mashList.size() < 30) return null;

        List<MashEvent> result = new ArrayList<>();

        int dayKIndex = dayKList.size() - 30;
        int mashIndex = mashList.size() - 30;

        MashEvent first = new MashEvent();
        first.setMash(mashList.get(mashIndex));
        result.add(first);

        for (int i=dayKIndex+1, j=mashIndex+1;i<dayKList.size()&&j<mashList.size();i++,j++) {
            MashEvent tabTableInsVO = new MashEvent();

            tabTableInsVO.setMash(mashList.get(j));

//            System.out.println(JSON.toJSON(list.get(i-1)));
            //kdj

            if (mashList.get(j-1).getK() == 0 && mashList.get(j-1).getD() == 0 && mashList.get(j-1).getJ() == 0) {
                continue;
            }

            if(mashList.get(j-1).getD()>=80){
                tabTableInsVO.kdjIns = "D线的值大于80,受到很多人的追捧,行情出现超买现象。注意随时可能出现的转折或者反弹。";
            }
            if(mashList.get(j).getD()<=20){
                tabTableInsVO.kdjIns = "D线小于20,大多数股民会采取做空策略,行情出现超卖现象。注意随时可能出现的转折或者反弹。";
            }
            if(mashList.get(j-1).getK() < mashList.get(j-1).getD() &&
                    mashList.get(j).getK() > mashList.get(j).getD() ){
                tabTableInsVO.kdjgrade = 3;
                tabTableInsVO.kdjIns = "K线向上突破D线,出现黄金交叉(上升中的短期移动平均线由下而上穿过上升的长期移动平均线),股票在后市有上涨趋势。压力线被向上突破，行情看好。";
            }
            if(mashList.get(j-1).getK() > mashList.get(j-1).getD() &&
                    mashList.get(j).getK() < mashList.get(j).getD() ){
                tabTableInsVO.kdjgrade = 2;
                tabTableInsVO.kdjIns = "K线向下突破D线,出现死亡交叉(下降中的短期移动平均线由上而下穿过下降的长期移动平均线),股票在后市有下跌趋势。支撑线被向下突破，表示股价将继续下落，行情看跌。";
            }


            //boll
//            if(dayKList.get(i).getClose()>mashList.get(j).get) {
//                tabTableInsVO.bollgrade = 1;
//                tabTableInsVO.bollIns += "股价向上击穿阻力线,股价可能到达了涨势的末期,出现卖点。";
//            }
//            if(list.get(j).close<list.get(j).boll_low) {
//                tabTableInsVO.bollgrade = 2;
//                tabTableInsVO.bollIns += "股价向下击破支撑线,股价可能到达了跌势的末期,出现买点。";
//            }
//            double before = (list.get(j-1).boll_upper-list.get(j-1).boll_low)/list.get(j-1).boll_middle;
//            double now = (list.get(j).boll_upper-list.get(j).boll_low)/list.get(j).boll_middle;
//            if(before<now&&now<0.1) {
//                tabTableInsVO.bollIns += "Boll线上小的两条线的距离逐渐变大,boll线开口变宽。";
//                if (list.get(j).close > list.get(j).boll_middle) {
//                    tabTableInsVO.bollgrade = 3; //上涨
//                    tabTableInsVO.bollIns += "此时股票价格处于boll线中线之上,行情看好,后市可能会出现上涨趋势。";
//                }
//                else {
//                    tabTableInsVO.bollgrade = 2; //下跌
//                    tabTableInsVO.bollIns += "此时股票价格处于boll线中线之下,行情看淡,后市可能会出现下跌趋势。请股民朋友谨慎对待";
//                }
//            }else if(before>now&&now<0.1){
//                tabTableInsVO.bollIns += "Boll线上下的两条线的距离逐渐变小,Boll线开口变窄。";
//                if (list.get(i).close > list.get(i).boll_middle) {
//                    tabTableInsVO.bollgrade = 3; //上涨
//                    tabTableInsVO.bollIns += "此时股票价格处于boll线中线之上,行情看好,后市可能会出现上涨趋势。";
//                }
//                else {
//                    tabTableInsVO.bollgrade = 2; //下跌
//                    tabTableInsVO.bollIns += "此时股票价格处于boll线中线之下,行情看淡,后市可能会出现下跌趋势。";
//                }
//            }

            //rsi
            if(mashList.get(j).getRsi1() >=85){
                tabTableInsVO.rsigrade = 2 ;
                tabTableInsVO.rsiIns += "rsi值过大,超过上阻力位置,数值过大,股票后期可能会下跌,建议做空";
            }
            if(mashList.get(j).getRsi1()<85&&mashList.get(j).getRsi1()>=60)
            {
                tabTableInsVO.rsiIns += "rsi值较大,行情被很多人所看好,处于涨势中,强买入";
                tabTableInsVO.rsigrade = 3;
            }

            if(mashList.get(j).getRsi1() <= 15){
                tabTableInsVO.rsigrade = 2 ;
                tabTableInsVO.rsiIns += "rsi值过小,行情不被大多数人看好,可能会出现下跌趋势,建议做空";

            }

            //end
            result.add(tabTableInsVO);
        }

        return result;
    }
}
