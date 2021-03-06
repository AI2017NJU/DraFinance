package service.impl;

import dao.DayKDAO;
import dao.MashDAO;
import libsvm.*;
import model.DayK;
import model.Mash;
import org.springframework.stereotype.Service;
import service.SVMChangeService;
import service.SVMPriceService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by christine on 2017/7/8.
 */
@Service("SVMChange")
public class SVMChangeImpl implements SVMChangeService{
    /**取“一段时间”来预测，这里为7天*/
    public static final int days=7;

    @Resource
    public DayKDAO dayKDAO;
    @Resource
    public MashDAO mashDAO;

    public List<Mash> train(String year,String stockId) {
        List<Mash> MashList = mashDAO.getPriceTrainData(year,stockId);
        return MashList;
    }

    /**用于预测的数据集，即时间长度为“days”的数据*/
    public List<Mash> predict(String stockId) {
        List<Mash> MashList = mashDAO.getPricePreditData(stockId);
        return MashList;
    }

    @Override
    public int predictByMa5Price(String stockId) {
        List<Mash> Mashs=train("2016",stockId);
        List<DayK> dayKs=dayKDAO.getPriceTrainData("2016",stockId);

        List<Integer> changeList=new ArrayList<>();
        for(int i=0;i<dayKs.size();i++)
        {
            double change=dayKs.get(i).getClose()-dayKs.get(i).getOpen();
            if(change>0)//涨
            {
                changeList.add(1);
            }
            else if(change<0)//跌
            {
                changeList.add(0);
            }
            else//平
            {
                changeList.add(-1);
            }
        }

        svm_node[][] datas =new svm_node[Mashs.size()-days][days];//训练集的向量表//
        double[] labelss = new double[Mashs.size()-days];//对应的labels
        for(int i=days-1;i<Mashs.size()-1;i++)
        {
            svm_node[] svm_nodes=new svm_node[days];//
            for(int j=0;j<days;j++)
            {
                svm_node pa0 = new svm_node();
                pa0.index = 0;
                pa0.value = Mashs.get(i-j).getMa5Price();
                svm_nodes[0+j]=pa0;
            }

            datas[i-days+1]=svm_nodes;
            labelss[i - days + 1] = changeList.get(i+1);
        }


        //定义svm_problem对象
        svm_problem problem = new svm_problem();
        problem.l = Mashs.size()-days; //向量个数
        problem.x = datas; //训练集向量表
        problem.y = labelss; //对应的labels数组

        //定义svm_parameter对象
        svm_parameter param = new svm_parameter();
        param.svm_type = svm_parameter.C_SVC;
        param.kernel_type = svm_parameter.LINEAR;
        param.cache_size = 100;
        param.eps = 0.00001;
        param.C = 1;

        //训练SVM分类模型
        System.out.println(svm.svm_check_parameter(problem, param)); //如果参数没有问题，则svm.svm_check_parameter()函数返回null,否则返回error描述。
        svm_model model = svm.svm_train(problem, param); //svm.svm_train()训练出SVM分类模型

        //定义测试数据点c
        List<Mash> Mashs2=predict(stockId);
        svm_node[] svm_nodes=new svm_node[days];//
        for(int j=0;j<days;j++)
        {
            svm_node pa0 = new svm_node();
            pa0.index = 0;
            pa0.value = Mashs2.get(j).getMa5Price();

            svm_nodes[0+j]=pa0;
        }
        //预测测试数据的labels
        int index=(int)svm.svm_predict(model, svm_nodes);

        return index;
    }

    @Override
    public int predictByMa10Price(String stockId) {
        List<Mash> Mashs=train("2016",stockId);
        List<DayK> dayKs=dayKDAO.getPriceTrainData("2016",stockId);

        List<Integer> changeList=new ArrayList<>();
        for(int i=0;i<dayKs.size();i++)
        {
            double change=dayKs.get(i).getClose()-dayKs.get(i).getOpen();
            if(change>0)//涨
            {
                changeList.add(1);
            }
            else if(change<0)//跌
            {
                changeList.add(0);
            }
            else//平
            {
                changeList.add(-1);
            }
        }

        svm_node[][] datas =new svm_node[Mashs.size()-days][days];//训练集的向量表//
        double[] labelss = new double[Mashs.size()-days];//对应的labels
        for(int i=days-1;i<Mashs.size()-1;i++)
        {
            svm_node[] svm_nodes=new svm_node[days];//
            for(int j=0;j<days;j++)
            {
                svm_node pa0 = new svm_node();
                pa0.index = 0;
                pa0.value = Mashs.get(i-j).getMa10Price();
                svm_nodes[0+j]=pa0;
            }

            datas[i-days+1]=svm_nodes;
            labelss[i - days + 1] = changeList.get(i+1);
        }


        //定义svm_problem对象
        svm_problem problem = new svm_problem();
        problem.l = Mashs.size()-days; //向量个数
        problem.x = datas; //训练集向量表
        problem.y = labelss; //对应的labels数组

        //定义svm_parameter对象
        svm_parameter param = new svm_parameter();
        param.svm_type = svm_parameter.C_SVC;
        param.kernel_type = svm_parameter.LINEAR;
        param.cache_size = 100;
        param.eps = 0.00001;
        param.C = 1;

        //训练SVM分类模型
        System.out.println(svm.svm_check_parameter(problem, param)); //如果参数没有问题，则svm.svm_check_parameter()函数返回null,否则返回error描述。
        svm_model model = svm.svm_train(problem, param); //svm.svm_train()训练出SVM分类模型

        //定义测试数据点c
        List<Mash> Mashs2=predict(stockId);
        svm_node[] svm_nodes=new svm_node[days];//
        for(int j=0;j<days;j++)
        {
            svm_node pa0 = new svm_node();
            pa0.index = 0;
            pa0.value = Mashs2.get(j).getMa10Price();

            svm_nodes[0+j]=pa0;
        }
        //预测测试数据的labels
        int index=(int)svm.svm_predict(model, svm_nodes);

        return index;
    }

    @Override
    public int predictByMa20Price(String stockId) {
        List<Mash> Mashs=train("2016",stockId);
        List<DayK> dayKs=dayKDAO.getPriceTrainData("2016",stockId);

        List<Integer> changeList=new ArrayList<>();
        for(int i=0;i<dayKs.size();i++)
        {
            double change=dayKs.get(i).getClose()-dayKs.get(i).getOpen();
            if(change>0)//涨
            {
                changeList.add(1);
            }
            else if(change<0)//跌
            {
                changeList.add(0);
            }
            else//平
            {
                changeList.add(-1);
            }
        }

        svm_node[][] datas =new svm_node[Mashs.size()-days][days];//训练集的向量表//
        double[] labelss = new double[Mashs.size()-days];//对应的labels
        for(int i=days-1;i<Mashs.size()-1;i++)
        {
            svm_node[] svm_nodes=new svm_node[days];//
            for(int j=0;j<days;j++)
            {
                svm_node pa0 = new svm_node();
                pa0.index = 0;
                pa0.value = Mashs.get(i-j).getMa20Price();
                svm_nodes[0+j]=pa0;
            }

            datas[i-days+1]=svm_nodes;
            labelss[i - days + 1] = changeList.get(i+1);
        }


        //定义svm_problem对象
        svm_problem problem = new svm_problem();
        problem.l = Mashs.size()-days; //向量个数
        problem.x = datas; //训练集向量表
        problem.y = labelss; //对应的labels数组

        //定义svm_parameter对象
        svm_parameter param = new svm_parameter();
        param.svm_type = svm_parameter.C_SVC;
        param.kernel_type = svm_parameter.LINEAR;
        param.cache_size = 100;
        param.eps = 0.00001;
        param.C = 1;

        //训练SVM分类模型
        System.out.println(svm.svm_check_parameter(problem, param)); //如果参数没有问题，则svm.svm_check_parameter()函数返回null,否则返回error描述。
        svm_model model = svm.svm_train(problem, param); //svm.svm_train()训练出SVM分类模型

        //定义测试数据点c
        List<Mash> Mashs2=predict(stockId);
        svm_node[] svm_nodes=new svm_node[days];//
        for(int j=0;j<days;j++)
        {
            svm_node pa0 = new svm_node();
            pa0.index = 0;
            pa0.value = Mashs2.get(j).getMa20Price();

            svm_nodes[0+j]=pa0;
        }
        //预测测试数据的labels
        int index=(int)svm.svm_predict(model, svm_nodes);

        return index;
    }

    @Override
    public int predictByMa51020Price(String stockId) {
        List<Mash> Mashs = train("2016",stockId);
        List<DayK> dayKs=dayKDAO.getPriceTrainData("2016",stockId);
        List<Integer> changeList = new ArrayList<>();
        for (int i = 0; i < dayKs.size(); i++) {
            double change = dayKs.get(i).getClose() - dayKs.get(i).getOpen();
            if (change > 0)//涨
            {
                changeList.add(1);
            } else if (change < 0)//跌
            {
                changeList.add(0);
            } else//平
            {
                changeList.add(-1);
            }
        }

        svm_node[][] datas = new svm_node[Mashs.size() - days][3 * days];//训练集的向量表//
        double[] labelss = new double[Mashs.size() - days];//对应的labels
        for (int i = days - 1; i < Mashs.size() - 1; i++) {
            svm_node[] svm_nodes = new svm_node[3 * days];//
            for (int j = 0; j < days; j++) {
                svm_node pa0 = new svm_node();
                pa0.index = 0;
                pa0.value = Mashs.get(i - j).getMa5Price();
                svm_node pa1 = new svm_node();
                pa1.index = 1;
                pa1.value = Mashs.get(i - j).getMa10Price();
                svm_node pa2 = new svm_node();
                pa2.index = 2;
                pa2.value = Mashs.get(i - j).getMa20Price();
                svm_nodes[0 + 3 * j] = pa0;
                svm_nodes[1 + 3 * j] = pa1;
                svm_nodes[2 + 3 * j] = pa2;
            }
            datas[i - days + 1] = svm_nodes;
            labelss[i - days + 1] = changeList.get(i + 1);
        }


        //定义svm_problem对象
        svm_problem problem = new svm_problem();
        problem.l = Mashs.size() - days; //向量个数
        problem.x = datas; //训练集向量表
        problem.y = labelss; //对应的labels数组

        //定义svm_parameter对象
        svm_parameter param = new svm_parameter();
        param.svm_type = svm_parameter.C_SVC;
        param.kernel_type = svm_parameter.LINEAR;
        param.cache_size = 100;
        param.eps = 0.00001;
        param.C = 1;

        //训练SVM分类模型
        System.out.println(svm.svm_check_parameter(problem, param)); //如果参数没有问题，则svm.svm_check_parameter()函数返回null,否则返回error描述。
        svm_model model = svm.svm_train(problem, param); //svm.svm_train()训练出SVM分类模型

        //定义测试数据点c
        List<Mash> Mashs2 = predict(stockId);
        svm_node[] svm_nodes = new svm_node[3 * days];//
        for (int j = 0; j < days; j++) {
            svm_node pa0 = new svm_node();
            pa0.index = 0;
            pa0.value = Mashs2.get(j).getMa5Price();
            svm_node pa1 = new svm_node();
            pa1.index = 1;
            pa1.value = Mashs2.get(j).getMa10Price();
            svm_node pa2 = new svm_node();
            pa2.index = 0;
            pa2.value = Mashs2.get(j).getMa20Price();
            svm_nodes[0 + 3*j] = pa0;
            svm_nodes[1 + 3*j] = pa1;
            svm_nodes[2 + 3*j] = pa2;
        }
        //预测测试数据的labels
        int index = (int) svm.svm_predict(model, svm_nodes);

        return index;
    }

    @Override
    public int predictByMacd(String stockId) {
        List<Mash> Mashs = train("2016",stockId);
        List<DayK> dayKs=dayKDAO.getPriceTrainData("2016",stockId);
        List<Integer> changeList = new ArrayList<>();
        for (int i = 0; i < dayKs.size(); i++) {
            double change = dayKs.get(i).getClose() - dayKs.get(i).getOpen();
            if (change > 0)//涨
            {
                changeList.add(1);
            } else if (change < 0)//跌
            {
                changeList.add(0);
            } else//平
            {
                changeList.add(-1);
            }
        }

        svm_node[][] datas = new svm_node[Mashs.size() - days][3 * days];//训练集的向量表//
        double[] labelss = new double[Mashs.size() - days];//对应的labels
        for (int i = days - 1; i < Mashs.size() - 1; i++) {
            svm_node[] svm_nodes = new svm_node[3 * days];//
            for (int j = 0; j < days; j++) {
                svm_node pa0 = new svm_node();
                pa0.index = 0;
                pa0.value = Mashs.get(i - j).getMacd();
                svm_node pa1 = new svm_node();
                pa1.index = 1;
                pa1.value = Mashs.get(i - j).getDiff();
                svm_node pa2 = new svm_node();
                pa2.index = 2;
                pa2.value = Mashs.get(i - j).getDea();
                svm_nodes[0 + 3 * j] = pa0;
                svm_nodes[1 + 3 * j] = pa1;
                svm_nodes[2 + 3 * j] = pa2;
            }
            datas[i - days + 1] = svm_nodes;
            labelss[i - days + 1] = changeList.get(i + 1);
        }


        //定义svm_problem对象
        svm_problem problem = new svm_problem();
        problem.l = Mashs.size() - days; //向量个数
        problem.x = datas; //训练集向量表
        problem.y = labelss; //对应的labels数组

        //定义svm_parameter对象
        svm_parameter param = new svm_parameter();
        param.svm_type = svm_parameter.C_SVC;
        param.kernel_type = svm_parameter.LINEAR;
        param.cache_size = 100;
        param.eps = 0.00001;
        param.C = 1;

        //训练SVM分类模型
        System.out.println(svm.svm_check_parameter(problem, param)); //如果参数没有问题，则svm.svm_check_parameter()函数返回null,否则返回error描述。
        svm_model model = svm.svm_train(problem, param); //svm.svm_train()训练出SVM分类模型

        //定义测试数据点c
        List<Mash> Mashs2 = predict(stockId);
        svm_node[] svm_nodes = new svm_node[3 * days];//
        for (int j = 0; j < days; j++) {
            svm_node pa0 = new svm_node();
            pa0.index = 0;
            pa0.value = Mashs2.get(j).getMacd();
            svm_node pa1 = new svm_node();
            pa1.index = 1;
            pa1.value = Mashs2.get(j).getDiff();
            svm_node pa2 = new svm_node();
            pa2.index = 0;
            pa2.value = Mashs2.get(j).getDea();
            svm_nodes[0 + 3*j] = pa0;
            svm_nodes[1 + 3*j] = pa1;
            svm_nodes[2 + 3*j] = pa2;
        }
        //预测测试数据的labels
        int index = (int) svm.svm_predict(model, svm_nodes);

        return index;
    }

    @Override
    public int predictByKDJ(String stockId) {
        List<Mash> Mashs = train("2016",stockId);
        List<DayK> dayKs=dayKDAO.getPriceTrainData("2016",stockId);
        List<Integer> changeList = new ArrayList<>();
        for (int i = 0; i < dayKs.size(); i++) {
            double change = dayKs.get(i).getClose() - dayKs.get(i).getOpen();
            if (change > 0)//涨
            {
                changeList.add(1);
            } else if (change < 0)//跌
            {
                changeList.add(0);
            } else//平
            {
                changeList.add(-1);
            }
        }

        svm_node[][] datas = new svm_node[Mashs.size() - days][3 * days];//训练集的向量表//
        double[] labelss = new double[Mashs.size() - days];//对应的labels
        for (int i = days - 1; i < Mashs.size() - 1; i++) {
            svm_node[] svm_nodes = new svm_node[3 * days];//
            for (int j = 0; j < days; j++) {
                svm_node pa0 = new svm_node();
                pa0.index = 0;
                pa0.value = Mashs.get(i - j).getK();
                svm_node pa1 = new svm_node();
                pa1.index = 1;
                pa1.value = Mashs.get(i - j).getD();
                svm_node pa2 = new svm_node();
                pa2.index = 2;
                pa2.value = Mashs.get(i - j).getJ();
                svm_nodes[0 + 3 * j] = pa0;
                svm_nodes[1 + 3 * j] = pa1;
                svm_nodes[2 + 3 * j] = pa2;
            }
            datas[i - days + 1] = svm_nodes;
            labelss[i - days + 1] = changeList.get(i + 1);
        }


        //定义svm_problem对象
        svm_problem problem = new svm_problem();
        problem.l = Mashs.size() - days; //向量个数
        problem.x = datas; //训练集向量表
        problem.y = labelss; //对应的labels数组

        //定义svm_parameter对象
        svm_parameter param = new svm_parameter();
        param.svm_type = svm_parameter.C_SVC;
        param.kernel_type = svm_parameter.LINEAR;
        param.cache_size = 100;
        param.eps = 0.00001;
        param.C = 1;

        //训练SVM分类模型
        System.out.println(svm.svm_check_parameter(problem, param)); //如果参数没有问题，则svm.svm_check_parameter()函数返回null,否则返回error描述。
        svm_model model = svm.svm_train(problem, param); //svm.svm_train()训练出SVM分类模型

        //定义测试数据点c
        List<Mash> Mashs2 = predict(stockId);
        svm_node[] svm_nodes = new svm_node[3 * days];//
        for (int j = 0; j < days; j++) {
            svm_node pa0 = new svm_node();
            pa0.index = 0;
            pa0.value = Mashs2.get(j).getK();
            svm_node pa1 = new svm_node();
            pa1.index = 1;
            pa1.value = Mashs2.get(j).getD();
            svm_node pa2 = new svm_node();
            pa2.index = 0;
            pa2.value = Mashs2.get(j).getJ();
            svm_nodes[0 + 3*j] = pa0;
            svm_nodes[1 + 3*j] = pa1;
            svm_nodes[2 + 3*j] = pa2;
        }
        //预测测试数据的labels
        int index = (int) svm.svm_predict(model, svm_nodes);

        return index;
    }

    @Override
    public int predictByRsi(String stockId) {
        List<Mash> Mashs = train("2016",stockId);
        List<DayK> dayKs=dayKDAO.getPriceTrainData("2016",stockId);
        List<Integer> changeList = new ArrayList<>();
        for (int i = 0; i < dayKs.size(); i++) {
            double change = dayKs.get(i).getClose() - dayKs.get(i).getOpen();
            if (change > 0)//涨
            {
                changeList.add(1);
            } else if (change < 0)//跌
            {
                changeList.add(0);
            } else//平
            {
                changeList.add(-1);
            }
        }

        svm_node[][] datas = new svm_node[Mashs.size() - days][3 * days];//训练集的向量表//
        double[] labelss = new double[Mashs.size() - days];//对应的labels
        for (int i = days - 1; i < Mashs.size() - 1; i++) {
            svm_node[] svm_nodes = new svm_node[3 * days];//
            for (int j = 0; j < days; j++) {
                svm_node pa0 = new svm_node();
                pa0.index = 0;
                pa0.value = Mashs.get(i - j).getRsi1();
                svm_node pa1 = new svm_node();
                pa1.index = 1;
                pa1.value = Mashs.get(i - j).getRsi2();
                svm_node pa2 = new svm_node();
                pa2.index = 2;
                pa2.value = Mashs.get(i - j).getRsi3();
                svm_nodes[0 + 3 * j] = pa0;
                svm_nodes[1 + 3 * j] = pa1;
                svm_nodes[2 + 3 * j] = pa2;
            }
            datas[i - days + 1] = svm_nodes;
            labelss[i - days + 1] = changeList.get(i + 1);
        }


        //定义svm_problem对象
        svm_problem problem = new svm_problem();
        problem.l = Mashs.size() - days; //向量个数
        problem.x = datas; //训练集向量表
        problem.y = labelss; //对应的labels数组

        //定义svm_parameter对象
        svm_parameter param = new svm_parameter();
        param.svm_type = svm_parameter.C_SVC;
        param.kernel_type = svm_parameter.LINEAR;
        param.cache_size = 100;
        param.eps = 0.00001;
        param.C = 1;

        //训练SVM分类模型
        System.out.println(svm.svm_check_parameter(problem, param)); //如果参数没有问题，则svm.svm_check_parameter()函数返回null,否则返回error描述。
        svm_model model = svm.svm_train(problem, param); //svm.svm_train()训练出SVM分类模型

        //定义测试数据点c
        List<Mash> Mashs2 = predict(stockId);
        svm_node[] svm_nodes = new svm_node[3 * days];//
        for (int j = 0; j < days; j++) {
            svm_node pa0 = new svm_node();
            pa0.index = 0;
            pa0.value = Mashs2.get(j).getRsi1();
            svm_node pa1 = new svm_node();
            pa1.index = 1;
            pa1.value = Mashs2.get(j).getRsi2();
            svm_node pa2 = new svm_node();
            pa2.index = 0;
            pa2.value = Mashs2.get(j).getRsi3();
            svm_nodes[0 + 3*j] = pa0;
            svm_nodes[1 + 3*j] = pa1;
            svm_nodes[2 + 3*j] = pa2;
        }
        //预测测试数据的labels
        int index = (int) svm.svm_predict(model, svm_nodes);

        return index;
    }
}
