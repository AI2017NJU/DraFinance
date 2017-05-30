package bot;

import bot.wrapper.DayKWrapper;

/**
 * Created by stonezhang on 2017/4/26.
 */
public class DayKEntry {
    public static void main(String[] args) {
//        ListWrapper listWrapper = new ListWrapper("", "");
//        for(Enum category: Category.values()) {
//            for(Enum sellingType: SellingType.values()) {
//                listWrapper.setCategory(category.name());
//                listWrapper.setSellingType(sellingType.name());
//                listWrapper.scrapeToSql("net", "software2016", "nla");
//            }
//        }

        DayKWrapper dayKWrapper = new DayKWrapper();
        dayKWrapper.scrapeToMysql("root", "root", "finance");
    }
}
