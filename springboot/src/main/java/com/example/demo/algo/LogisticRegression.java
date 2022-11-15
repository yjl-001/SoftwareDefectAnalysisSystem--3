package com.example.demo.algo;

import com.example.demo.Utils.DataOperation;
import com.example.demo.Utils.LoadData;

import java.io.*;
import java.util.ArrayList;

public class LogisticRegression {
    private double learning_rate; // 学习率
    private int epochs;//最大的迭代次数
    private  double th;// 阈值
    private int paramNum; // 权重参数的数量
    private double[][] features; //特征矩阵
    private double[] labels; // 标签
    private double[] weights; //权重
    private int samNum;// 样本的数量
    private ArrayList<Double> acc = new ArrayList<Double>();
    private ArrayList<Double> loss = new ArrayList<Double>();

    LogisticRegression(double learning_rate, int epochs, double th, int paramNum, int samNUM, double[][] features, double[] labels){
        this.learning_rate = learning_rate;  //学习率
        this.epochs = epochs; //最大的迭代次数
        this.th = th; //阈值
        this.paramNum = paramNum;
        this.samNum = samNUM;
        this.features = features;
        this.labels = labels;
        acc.add(0.0);loss.add(1.0);

    }
    public double sigmoid(double x) {
        double i = 1.0;
        double y = i / (i + Math.exp(-x));
        return y;
    }

    private int preY(double x){
        if (sigmoid(x)>=0.5)
            return 1;
        else
            return 0;

    }

    private void ParaInitialize(){
        weights = new double[paramNum];//最后一个参数位偏置
        for(int i=0; i<paramNum; i++){
            weights[i] = 1;
        }
    }

    //计算每次迭代后的预测误差
    private double [] PreVal() {
        double [] Preval = new double[samNum];
        for (int i = 0; i< samNum; i ++) {
            double tmp = 0;
            for(int j = 0; j < paramNum; j ++) {
                tmp += features[i][j] * weights[j];
            }
            Preval[i] = sigmoid(tmp);
        }
        return Preval;
    }

    public void test(double[][] x_test, double[] y_test){
        double[] pres = new double[y_test.length];
        int E = 0;
        if(x_test.length == y_test.length){
            for(int i=0; i< x_test.length;i++){
                double pre = 0;
                for(int j=0; j<x_test[0].length;j++){
                    pre += weights[j]*x_test[i][j];
                }
                pres[i] = preY(pre);
                if(preY(pre) != y_test[i])
                    E = E+1;
            }
        }
        System.out.println("Acc is :"+(1 - E*1.0 /y_test.length));
    }

    //计算误差率
    private double error_rate( double [] Preval) {
        double sum_err = 0.0;
        for(int i = 0; i < samNum; i ++) {
            sum_err += Math.pow(labels[i] - Preval[i], 2);
        }
        return sum_err;
    }

    public static void saveModel(String filename, double [] W) throws IOException{
        File f = new File(filename);
        // 构建FileOutputStream对象
        FileOutputStream fip = new FileOutputStream(f);
        // 构建OutputStreamWriter对象
        OutputStreamWriter writer = new OutputStreamWriter(fip,"UTF-8");
        //计算模型矩阵的元素个数
        int n = W.length;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n-1; i ++) {
            sb.append(String.valueOf(W[i]));
            sb.append(",");
        }
        sb.append(String.valueOf(W[n-1]));
        String sb1 = sb.toString();
        writer.write(sb1);
        writer.close();
        fip.close();
    }

    public static void saveAccLoss(String filename, ArrayList<Double> W) throws IOException{
        File f = new File(filename);
        // 构建FileOutputStream对象
        FileOutputStream fip = new FileOutputStream(f);
        // 构建OutputStreamWriter对象
        OutputStreamWriter writer = new OutputStreamWriter(fip,"UTF-8");
        //计算模型矩阵的元素个数
        int n = W.size();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n-1; i ++) {
            sb.append(String.valueOf(W.get(i)));
            sb.append(",");
        }
        sb.append(String.valueOf(W.get(n-1)));
        String sb1 = sb.toString();
        writer.write(sb1);
        writer.close();
        fip.close();
    }

    //LR模型训练
    public double[] Updata() {
        //初始化权重矩阵
        ParaInitialize();
        double sum_err = -999999;
        // 循环迭代优化权重矩阵
        for (int i = 0; i < epochs; i ++) {
            // 每次迭代后，样本预测值
            double [] Preval = PreVal();
            if(Math.abs(error_rate(Preval)-sum_err)<th)//小于阈值退出
                break;
            sum_err = error_rate(Preval);
            if (i<epochs) {
                System.out.println("第" + i + "次迭代的预测准确率为:" +(1-sum_err/samNum));
                acc.add((1-sum_err/samNum));
                loss.add(sum_err/samNum);
            }
            //预测值与标签的误差
            double [] err = new double[samNum];
            for(int j = 0; j < samNum; j ++) {
                err[j] = labels[j] - Preval[j];
            }
            // 计算权重矩阵的梯度方向
            double [] Delt_W = new double[paramNum];
            for (int n = 0 ; n < paramNum; n ++) {
                double tmp = 0;
                for(int m = 0; m < samNum; m ++) {
                    tmp += features[m][n] * err[m];
                }
                Delt_W[n] = tmp / samNum;
            }

            for(int m = 0; m < paramNum; m ++) {
                weights[m] = weights[m] + learning_rate * Delt_W[m];
            }
        }
        try {//保存权重文件
            saveModel("weights\\lr.txt",weights);
            saveAccLoss("weights\\lr_acc.txt", acc);
            saveAccLoss("weights\\lr_loss.txt",loss);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return weights;
    }



//    public static void main(String[] args) throws IOException {
//        String filename = "data\\JDT_Cvs.csv";
//        double[][] a = LoadData.LoadTrainFeature(filename);
//        double[][] x_train = DataOperation.normalize4Scale(a);
//        //double[][] x_train = a;
//        double[] l = LoadData.LoadLabel(filename);
//        LogisticRegression lr = new LogisticRegression(0.5, 1000, 0.00001,x_train[0].length,x_train.length,x_train, l);
//        double[] w = lr.Updata();
//        String testFileName = "data\\Lucene_Cvs.csv";
//        double[][] b = LoadData.LoadTrainFeature(testFileName);
//        double[][] x_test = DataOperation.normalize4Scale(b);
//        //double[][] x_test = b;
//        double[] y_test = LoadData.LoadLabel(testFileName);
//        lr.test(x_test, y_test);
//        for(int i=0; i<w.length;i++){
//            System.out.println(w[i]);
//        }
//    }
}
