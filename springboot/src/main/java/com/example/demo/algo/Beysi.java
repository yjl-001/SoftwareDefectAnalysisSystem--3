package com.example.demo.algo;

import com.example.demo.Utils.LoadSVMData;
import java.io.IOException;
import java.util.HashMap;

public class Beysi {
    private final double PI = 3.14159;
    private double[] Ck = new double[2];
    private int t_num;// 正例的数量
    private int f_num;// 负例的数量
    private HashMap<Integer,HashMap<Integer, double[]>> p = new HashMap<>();// 模型
    private double[][] data;
    private double[] label;

    Beysi(double[][] x_train, double[] y_train){
        this.data = x_train;
        this.label = y_train;
        Ck = CalCk(this.label);
        p = CalP(this.data);
    }

    public int predict(double[] x){
        double pre_T = 1;
        double pre_F = 1;
        for(int i=0;i<x.length;i++){
            pre_T = pre_T * Gass(x[i],p.get(1).get(i)[0], p.get(1).get(i)[1] );
            pre_F = pre_F *  Gass(x[i],p.get(0).get(i)[0], p.get(0).get(i)[1] );
        }
        pre_T = pre_T*Ck[1];
        pre_F = pre_F*Ck[0];
        if(pre_T>=pre_F)
            return 1;
        else
            return -1;
    }

    public void test(double[][] x_test, double[] y_test){
        int err = 0;
        for(int i=0;i< x_test.length;i++){
            double[] x = x_test[i];
            if(predict(x)!=y_test[i])
                err++;
        }
        double acc = 1.0*err/ x_test.length;
        System.out.println("acc is:" + acc);
    }

    private HashMap<Integer,HashMap<Integer, double[]>> CalP(double[][] x){
        HashMap<Integer,HashMap<Integer, double[]>> result = new HashMap<>();
        int futureNum = x[0].length;
        // 划分正例与负例的data[][]
        double[][] t_data = new double[t_num][futureNum];
        double[][] f_data = new double[f_num][futureNum];
        int t = 0;
        int f = 0;
        for(int i=0;i<x.length;i++){
            if(label[i]==1){
                for(int j=0; j<futureNum;j++){
                    t_data[t][j] = x[i][j];
                }
                t++;
            }else{
                for(int j=0; j<futureNum;j++){
                    f_data[f][j] = x[i][j];
                }
                f++;
            }
        }
        // 计算正例的分布
        HashMap<Integer, double[]> T_temp = new HashMap<>();
        for(int i=0; i<futureNum; i++){//某个特征
            double[] a = new double[2];//均值与方差
            double sum=0;
            double square = 0;
            double mean = 0;
            double stdev = 0;
            for(int j=0;j<t_data.length;j++){
                //计算均值
                sum+=t_data[j][i];
            }
            mean = sum/t_data.length;
            for(int j=0;j<t_data.length;j++){
                square+=Math.pow((t_data[j][i]-mean),2);
            }
            stdev = square/ t_data.length;
            a[0] = mean;
            a[1] = stdev;
            T_temp.put(i, a);
        }
        result.put(1, T_temp);
        //计算负例的分布
        HashMap<Integer, double[]> F_temp = new HashMap<>();
        for(int i=0; i<futureNum; i++){//某个特征
            double[] a = new double[2];//均值与方差
            double sum=0;
            double square = 0;
            double mean = 0;
            double stdev = 0;
            for(int j=0;j<f_data.length;j++){
                //计算均值
                sum+=f_data[j][i];
            }
            mean = sum/f_data.length;
            for(int j=0;j<f_data.length;j++){
                square+=Math.pow((f_data[j][i]-mean),2);
            }
            stdev = square/ f_data.length;
            a[0] = mean;
            a[1] = stdev;
            F_temp.put(i, a);
        }
        result.put(0, F_temp);
        return result;

    }

    private double[] CalCk(double[] y){
        double[] temp = new double[2];
        int t = 0;
        int f = 0;
        for(int i=0; i<y.length;i++){
            if(y[i]==1){
                t++;
            }else{
                f++;
            }
        }
        t_num = t;
        f_num = f;
        temp[0] = 1.0*f/y.length;
        temp[1] = 1.0*t/ y.length;
        return temp;
    }


    private double Gass(double x, double mean, double stdev){// 计算高斯函数值
        double exponent = Math.exp(-(Math.pow(x-mean,2))/(2*Math.pow(stdev,2)));
        double GaussProb = (1/(Math.sqrt(2*PI)*stdev))*exponent;
        return GaussProb;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "data\\JDT_Cvs.csv";
        double[] y = LoadSVMData.LoadLabel(filePath);
        double[][] X = LoadSVMData.LoadTrainFeature(filePath);

        Beysi beysi = new Beysi(X, y);
        String testFilePath = "data\\Lucene_Cvs.csv";
        double[] test_y = LoadSVMData.LoadLabel(testFilePath);
        double[][] test_X = LoadSVMData.LoadTrainFeature(testFilePath);
        beysi.test(test_X, test_y);
    }
}
