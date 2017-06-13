package tools;

/**
 * Created by Hermit on 2017/6/8.
 */
public class StockHelper {

    public static final String[] BENCH_CODES = {"SH000", "SZ399"};

    public static boolean isBench(String ID) {
        for (String BENCH_CODE : BENCH_CODES) {
            if (ID.contains(BENCH_CODE)) return true;
        }
        return false;
    }

    public static String xueqiuCodeReflect(String ID) {
        return ID;
    }

    public static String baiduCodeReflect(String ID) {
        return ID.toLowerCase();
    }

    public static String gubaCodeReflect(String ID) {
        return ID.substring(2, ID.length());
    }
}
