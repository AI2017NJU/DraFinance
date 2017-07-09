package service.impl;

import libsvm.*;
import model.DayK;
import model.Mash;
import org.springframework.stereotype.Service;
import service.SVMChangeService;
import service.SVMPriceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christine on 2017/7/8.
 */
@Service("SVMChange")
public class SVMChangeImpl implements SVMChangeService{

    public List<Mash> train(String year) {
        List<Mash> MashList = new ArrayList<Mash>();
        return MashList;
    }

    /**用于预测的数据集，即时间长度为“days”的数据*/
    public List<Mash> predict() {
        List<Mash> MashList = new ArrayList<Mash>();
        return MashList;
    }

    @Override
    public void predictByMa5Price() {
//        SVMPriceService pricetest=new SVMPriceImpl();
//
//        List<Mash> Mashs=test.train("2016");
//        List<DayK> dayKs=pricetest.train("2016");
//
//        List<Integer> changeList=new ArrayList<>();
//        for(int i=0;i<dayKs.size();i++)
//        {
//            double change=dayKs.get(i).getClose()-dayKs.get(i).getOpen();
//            if(change>0)//涨
//            {
//                changeList.add(1);
//            }
//            else if(change<0)//跌
//            {
//                changeList.add(-1);
//            }
//            else//平
//            {
//                changeList.add(0);
//            }
//        }
//
//        svm_node[][] datas =new svm_node[Mashs.size()-days][days];//训练集的向量表//
//        double[] labelss = new double[Mashs.size()-days];//对应的labels
//        for(int i=days-1;i<Mashs.size()-1;i++)
//        {
//            svm_node[] svm_nodes=new svm_node[days];//
//            for(int j=0;j<days;j++)
//            {
//                svm_node pa0 = new svm_node();
//                pa0.index = 0;
//                pa0.value = Mashs.get(i-j).getMa5Price();
//                svm_nodes[0+j]=pa0;
//            }
//
//            datas[i-days+1]=svm_nodes;
//            labelss[i - days + 1] = changeList.get(i+1);
//        }
//
//
//        //定义svm_problem对象
//        svm_problem problem = new svm_problem();
//        problem.l = Mashs.size()-days; //向量个数
//        problem.x = datas; //训练集向量表
//        problem.y = labelss; //对应的labels数组
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
//        //定义测试数据点c
//        List<Mash> Mashs2=test.predict();
//        svm_node[] svm_nodes=new svm_node[days];//
//        for(int j=0;j<days;j++)
//        {
//            svm_node pa0 = new svm_node();
//            pa0.index = 0;
//            pa0.value = Mashs2.get(j).getMa5Price();
//
//            svm_nodes[0+j]=pa0;
//        }
//        //预测测试数据的labels
//        int index=(int)svm.svm_predict(model, svm_nodes);
//
//        System.out.println(index);
    }

    @Override
    public void predictByMa10Price() {

    }

    @Override
    public void predictByMa20Price() {

    }

    @Override
    public void predictByMa51020Price() {

    }

    @Override
    public void predictByMacd() {

    }

    @Override
    public void predictByKDJ() {

    }

    @Override
    public void predictByRsi() {

    }
}
