package service.impl;

import dao.DayKDAO;
import libsvm.*;

import model.DayK;
import model.SVM;
import org.springframework.stereotype.Service;
import service.SVMPriceService;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by christine on 2017/7/8.
 */
@Service("SVMPrice")
public class SVMPriceImpl implements SVMPriceService {
    /**取“一段时间”来预测，这里为7天*/
    public static final int days=7;

//    @Resource
//    private DayKDAO dayKDAO;

    @Override
    public List<SVM> predictByOCHL_List(String stockId) {
        List<SVM> svmList=new ArrayList<SVM>();
        for(int i=0;i<15;i++)
        {
            SVM svm=new SVM();
            svm.setDate("2017-7-"+i);
            double price=new Random().nextDouble()*20;
            svm.setPrice_predit(price+new Random().nextDouble()*3);
            svm.setPrice_true(price+new Random().nextDouble()*3);
            svmList.add(svm);
        }
//        List<String> time_series=timeseries();
//        List<DayK> dayKs=train("2016");
//        svm_node[][] datas =new svm_node[dayKs.size()-days][4*days];//训练集的向量表，4表示一天中取4个数据：开、收、高、低，一组数据共4*days个作为一个向量
//        double[] labels = new double[dayKs.size()-days];//对应的label，用作分类，标签值相同则为同一类，这里取每个标签值都不同，所以最后结果是看与训练集哪个时间段的情况最相近
//        for(int i=days-1;i<dayKs.size()-1;i++)//日期从小到大的数据，从第days天开始往前取days个日期，以days+1为标签，后面以此类推，每次用days*4个数据来构造一个向量
//        {
//            svm_node[] svm_nodes=new svm_node[4*days];//一个向量
//            for(int j=0;j<days;j++)
//            {
//                svm_node pa0 = new svm_node();
//                pa0.index = 0;
//                pa0.value = dayKs.get(i-j).getOpen()-dayKs.get(i-j).getOpen();
//                svm_node pa1 = new svm_node();
//                pa1.index = 1;
//                pa1.value = dayKs.get(i-j).getClose()-dayKs.get(i-j).getOpen();
//                svm_node pa2 = new svm_node();
//                pa2.index = 2;
//                pa2.value = dayKs.get(i-j).getHigh()-dayKs.get(i-j).getOpen();
//                svm_node pa3 = new svm_node();
//                pa3.index = 3;
//                pa3.value = dayKs.get(i-j).getLow()-dayKs.get(i-j).getOpen();
//
//                svm_nodes[0+4*j]=pa0;
//                svm_nodes[1+4*j]=pa1;
//                svm_nodes[2+4*j]=pa2;
//                svm_nodes[3+4*j]=pa3;
//            }
//
//            datas[i-days+1]=svm_nodes;//训练集
//            labels[i-days+1]=(i+1);//训练集中每个向量的标签，在这里每个都不同，以后一日的下标为标签
//        }
//
//
//        //定义svm_problem对象
//        svm_problem problem = new svm_problem();
//        problem.l = dayKs.size()-days; //向量个数
//        problem.x = datas; //训练集向量表
//        problem.y = labels; //对应的label数组
//
//        //定义svm_parameter对象
//        svm_parameter param = new svm_parameter();
//        param.svm_type = svm_parameter.C_SVC;
//        param.kernel_type = svm_parameter.LINEAR;
//        param.cache_size = 100;
//        param.eps = 0.00001;
//        param.C = 1;
//
//        //训练SVM分类模型
//        System.out.println(svm.svm_check_parameter(problem, param)); //如果参数没有问题，则svm.svm_check_parameter()函数返回null,否则返回error描述。
//        svm_model model = svm.svm_train(problem, param); //svm.svm_train()训练出SVM分类模型
//
//        //定义测试数据点,duration取15天
//        int duration=15;
//        List<Double> priceList=new ArrayList<Double>();
//        for(int i=0;i<duration;i++) {
//            List<DayK> dayKs2 = predict(time_series.get(i));//i=0表示最近的一天开始往前推days天
//            svm_node[] svm_nodes = new svm_node[4 * days];//用于预测的数据是一个模为4*days的向量
//            for (int j = 0; j < days; j++) {
//                svm_node pa0 = new svm_node();
//                pa0.index = 0;
//                pa0.value = dayKs2.get(j).getOpen() - dayKs2.get(j).getOpen();
//                svm_node pa1 = new svm_node();
//                pa1.index = 1;
//                pa1.value = dayKs2.get(j).getClose() - dayKs2.get(j).getOpen();
//                svm_node pa2 = new svm_node();
//                pa2.index = 2;
//                pa2.value = dayKs2.get(j).getHigh() - dayKs2.get(j).getOpen();
//                svm_node pa3 = new svm_node();
//                pa3.index = 3;
//                pa3.value = dayKs2.get(j).getLow() - dayKs2.get(j).getOpen();
//
//                svm_nodes[0 + 4 * j] = pa0;
//                svm_nodes[1 + 4 * j] = pa1;
//                svm_nodes[2 + 4 * j] = pa2;
//                svm_nodes[3 + 4 * j] = pa3;
//            }
//
//            //预测测试数据的label
//            int index = (int) svm.svm_predict(model, svm_nodes);
//            double price=dayKs.get(index).getOpen()-dayKs.get(index-1).getOpen()+dayKs2.get(0).getOpen();
//            priceList.add(price);//时间最晚的在最前面
//            System.out.println(dayKs2.get(0).getDate()+" predict next day "+price);
//        }
//        System.out.println(priceList);
        return svmList;
    }

    /**
     * 根据开盘价、收盘价、最高价、最低价来预测
     * 这里根据各价格与当天开盘价的相对关系（差）作为训练数据发现联系
     * 最后取最接近的情况下后一日与前一日开盘价的差与数据库中最新的一天的开盘价来计算预期后一日的开盘价
     * */
    @Override
    public double predictByOCHL_Tomorrow(String stockId) {
        return 0;
//        List<DayK> dayKs=train("2016");
//        svm_node[][] datas =new svm_node[dayKs.size()-days][4*days];//训练集的向量表，4表示一天中取4个数据：开、收、高、低，一组数据共4*days个作为一个向量
//        double[] labels = new double[dayKs.size()-days];//对应的label，用作分类，标签值相同则为同一类，这里取每个标签值都不同，所以最后结果是看与训练集哪个时间段的情况最相近
//        for(int i=days-1;i<dayKs.size()-1;i++)//日期从小到大的数据，从第days天开始往前取days个日期，以days+1为标签，后面以此类推，每次用days*4个数据来构造一个向量
//        {
//            svm_node[] svm_nodes=new svm_node[4*days];//一个向量
//            for(int j=0;j<days;j++)
//            {
//                svm_node pa0 = new svm_node();
//                pa0.index = 0;
//                pa0.value = dayKs.get(i-j).getOpen()-dayKs.get(i-j).getOpen();
//                svm_node pa1 = new svm_node();
//                pa1.index = 1;
//                pa1.value = dayKs.get(i-j).getClose()-dayKs.get(i-j).getOpen();
//                svm_node pa2 = new svm_node();
//                pa2.index = 2;
//                pa2.value = dayKs.get(i-j).getHigh()-dayKs.get(i-j).getOpen();
//                svm_node pa3 = new svm_node();
//                pa3.index = 3;
//                pa3.value = dayKs.get(i-j).getLow()-dayKs.get(i-j).getOpen();
//
//                svm_nodes[0+4*j]=pa0;
//                svm_nodes[1+4*j]=pa1;
//                svm_nodes[2+4*j]=pa2;
//                svm_nodes[3+4*j]=pa3;
//            }
//
//            datas[i-days+1]=svm_nodes;//训练集
//            labels[i-days+1]=(i+1);//训练集中每个向量的标签，在这里每个都不同，以后一日的下标为标签
//        }
//
//
//        //定义svm_problem对象
//        svm_problem problem = new svm_problem();
//        problem.l = dayKs.size()-days; //向量个数
//        problem.x = datas; //训练集向量表
//        problem.y = labels; //对应的label数组
//
//        //定义svm_parameter对象
//        svm_parameter param = new svm_parameter();
//        param.svm_type = svm_parameter.C_SVC;
//        param.kernel_type = svm_parameter.LINEAR;
//        param.cache_size = 100;
//        param.eps = 0.00001;
//        param.C = 1;
//
//        //训练SVM分类模型
//        System.out.println(svm.svm_check_parameter(problem, param)); //如果参数没有问题，则svm.svm_check_parameter()函数返回null,否则返回error描述。
//        svm_model model = svm.svm_train(problem, param); //svm.svm_train()训练出SVM分类模型
//
//        //定义测试数据点
//
//        List<DayK> dayKs2=predict(null);
//        svm_node[] svm_nodes=new svm_node[4*days];//用于预测的数据是一个模为4*days的向量
//        for(int j=0;j<days;j++)
//        {
//            svm_node pa0 = new svm_node();
//            pa0.index = 0;
//            pa0.value = dayKs2.get(j).getOpen()-dayKs2.get(j).getOpen();
//            svm_node pa1 = new svm_node();
//            pa1.index = 1;
//            pa1.value = dayKs2.get(j).getClose()-dayKs2.get(j).getOpen();
//            svm_node pa2 = new svm_node();
//            pa2.index = 2;
//            pa2.value = dayKs2.get(j).getHigh()-dayKs2.get(j).getOpen();
//            svm_node pa3 = new svm_node();
//            pa3.index = 3;
//            pa3.value = dayKs2.get(j).getLow()-dayKs2.get(j).getOpen();
//
//            svm_nodes[0+4*j]=pa0;
//            svm_nodes[1+4*j]=pa1;
//            svm_nodes[2+4*j]=pa2;
//            svm_nodes[3+4*j]=pa3;
//        }
//
//        //预测测试数据的label
//        int index=(int)svm.svm_predict(model, svm_nodes);
//
//        System.out.println(dayKs.get(index).getOpen()-dayKs.get(index-1).getOpen()+dayKs2.get(0).getOpen());//打印预测后一天开盘价
//    }
//
//
//    public List<String> timeseries(String stockId) {
//        List<String> time_series = new ArrayList<>();
//
////            resultSet = super.select(conn, "select dataTime from dayK " + "where symbol='SH600400' order by dataTime desc");
//        time_series = dayKDAO.getDates(stockId);
//        return time_series;
    }

    /**训练集，目前用2016年一年的数据来训练*/
    public List<DayK> train(String year,String stockId) {
        List<DayK> dayKList = new ArrayList<DayK>();

//            resultSet = super.select(conn, "select * from dayK " + "where dataTime Like '"+year+"%' and symbol='SH600400' order by dataTime");
//        dayKList = dayKDAO.getPriceTrainData(year,stockId);
        return dayKList;
    }

    public List<DayK> predict(String time,String stockId) {
        List<DayK> dayKList = new ArrayList<DayK>();
////        try {
//            if(time==null||time.isEmpty())
//            {
////                resultSet = super.select(conn, "select * from dayK " + "where symbol='SH600400' order by dataTime desc");
//                dayKList = dayKDAO.getPricePreditData(stockId);
//
//            }
//            else {
//                dayKList = dayKDAO.getPricePreditData(time,stockId);
////                resultSet = super.select(conn, "select * from dayK " + "where symbol='SH600400' and dataTime<='" + time + "' order by dataTime desc");
//            }
//
//        dayKList = dayKDAO.getPricePreditData(time,stockId);
        return dayKList;
    }

    public List<String> timeseries()
    {
        List<String> time_series = new ArrayList<>();
//        dayKDAO.
        return time_series;
    }
}
