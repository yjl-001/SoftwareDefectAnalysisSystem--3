package com.example.demo.algo;

import com.example.demo.Utils.LoadSVMData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SVM {
    private int exampleNum;//X数组行数
    private int exampleDim;//X数组列数
    private double[] w;//权值
    private double lambda;//损失函数的参数
    private double lr = 0.001;//0.00001 学习率
    private double threshold = 0.001; //迭代停止 权值变换小于threshold
    private double cost;//HingeLoss损失函数  cost = HingeLoss^2 + lambda*||w||^2   cost = err'*err + lambda*w'*w; grad = 2*X(idx,:)'*err + 2*lambda*w;
    private double error;
    private double[] grad;//存放需要更新的权值w
    private double[] yp;//存放每一行x和权值w的点积 yp【0】表示第一行x和w的点积
    SVM(double paramLambda)
    {

        lambda = paramLambda;

    }

    private void CostAndGrad(double[][] X,double[] y)
    {
        cost =0;
        error = 0;
        for(int m=0;m<exampleNum;m++)//从第一行开始进行循环
        {
            yp[m]=0;
            for(int d=0;d<exampleDim;d++)
            {
                yp[m]+=X[m][d]*w[d];//第一行x和权值w的点积
            }

            if(y[m]*yp[m]-1<0)
            {
                cost += Math.max(0.0,1-y[m]*yp[m]);//将y label（-1 or 1）和点积相乘 和1的差 相加
            }

        }

        for(int d=0;d<exampleDim;d++)
        {
            cost += 0.5*lambda*w[d]*w[d];
        }


        for(int d=0;d<exampleDim;d++)
        {
            grad[d] = Math.abs(lambda*w[d]);
            for(int m=0;m<exampleNum;m++)
            {
                if(y[m]*yp[m]-1<0)
                {
                    grad[d]-= y[m]*X[m][d];
                }
            }
        }
    }

    private void update()
    {
        for(int d=0;d<exampleDim;d++)
        {
            w[d] -= lr*grad[d];
        }
    }

    public void Train(double[][] X,double[] y,int maxIters)
    {
        exampleNum = X.length;
        if(exampleNum <=0)
        {
            System.out.println("num of example <=0!");
            return;
        }
        exampleDim = X[0].length;
        w = new double[exampleDim];
        grad = new double[exampleDim];
        yp = new double[exampleNum];

        for(int iter=0;iter<maxIters;iter++)
        {

            CostAndGrad(X,y);
            System.out.println(iter+ " cost:"+cost+" "+"acc is "+(1-1.0*error/exampleNum));
            if(cost< threshold)
            {
                break;
            }
            update();

        }
        try {//保存权重文件
            saveModel("weights\\svm.txt",w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    private int predict(double[] x)
    {
        double pre=0;
        for(int j=0;j<x.length;j++)
        {
            pre+=x[j]*w[j];
        }
        System.out.println("y_pre :"+pre);
        if(pre >-1)//这个阈值一般位于-1到1
            return 1;
        else return 0;
    }

    public void Test(double[][] testX,double[] testY)
    {
        int error=0;
        for(int i=0;i<testX.length;i++)
        {
            if(predict(testX[i]) != testY[i])
            {
                error++;
            }
        }
        System.out.println("total:"+testX.length);
        System.out.println("error:"+error);
        System.out.println("error rate:"+((double)error/testX.length));
        System.out.println("acc rate:"+((double)(testX.length-error)/testX.length));
    }

    public static void main(String[] args) throws IOException
    {
        String filePath = "data\\JDT_WCHU.csv";
        double[] y = LoadSVMData.LoadLabel(filePath);
        double[][] X = LoadSVMData.LoadTrainFeature(filePath);


        SVM svm = new SVM(0.00001);
        svm.Train(X,y,1000);
        String testFilePath = "data\\Lucene_WCHU.csv";
        double[] test_y = LoadSVMData.LoadLabel(testFilePath);
        double[][] test_X = LoadSVMData.LoadTrainFeature(testFilePath);
        svm.Test(test_X, test_y);

    }

}
