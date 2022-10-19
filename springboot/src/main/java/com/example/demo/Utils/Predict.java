package com.example.demo.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Predict {
    private final static double[] Ck = {0.16639935846030474,0.8336006415396953};
    private final static double PI = 3.14159;
    public static String predict(int type, int m_type, double[] data) throws IOException {
        String result = "";
        ArrayList<Double> weights = new ArrayList<Double>();
        if(type==0){//逻辑回归
            File f;
            if(m_type==0){
                f = new File("springboot\\weights\\lr_Cvs.txt");
            }
            else if(m_type==1){
                f = new File("springboot\\weights\\lr_LDHH.txt");
            }
            else{
                f = new File("springboot\\weights\\lr_WCHU.txt");
            }
            FileInputStream fip = new FileInputStream(f);
            // 构建FileInputStream对象
            InputStreamReader reader = new InputStreamReader(fip,"UTF-8");
            // 构建InputStreamReader对象,编码与写入相同
            StringBuffer sb = new StringBuffer();
            while(reader.ready()) {
                sb.append((char) reader.read());
            }
            reader.close();
            fip.close();
            //将读入的数据流转换为字符串
            String sb1 = sb.toString();
            String [] a0 = sb1.split(",");
            for(int i=0; i< a0.length;i++){
                weights.add(Double.parseDouble(a0[i]));
            }
            double pre = 0;
            if(data.length==weights.size()-1){
                for(int i=0;i<weights.size()-1;i++) {
                    pre += weights.get(i) * data[i];
                }
                pre += weights.get(weights.size()-1);
                if(sigmoid(pre)>0.5){
                    result = "clean";
                }else{
                    result = "buggy";
                }
            }
        }if(type==2){
            HashMap<Integer, HashMap<Integer, double[]>> p = new HashMap<>();
            if(m_type==0){
                p.put(0, getwi("springboot\\weights\\fbeysi_Cvs.txt"));
                p.put(1,getwi("springboot\\weights\\tbeysi_Cvs.txt"));
            }else if(m_type==1){
                p.put(0, getwi("springboot\\weights\\fbeysi_LDHH.txt"));
                p.put(1,getwi("springboot\\weights\\tbeysi_LDHH.txt"));
            }else{
                p.put(0, getwi("springboot\\weights\\fbeysi_WCHU.txt"));
                p.put(1,getwi("springboot\\weights\\tbeysi_WCHU.txt"));
            }


            double pre_T = 1;
            double pre_F = 1;
            for(int i=0;i<data.length;i++){
                pre_T = pre_T * Gass(data[i],p.get(1).get(i)[0], p.get(1).get(i)[1] );
                pre_F = pre_F *  Gass(data[i],p.get(0).get(i)[0], p.get(0).get(i)[1] );
            }
            pre_T = pre_T*Ck[1];
            pre_F = pre_F*Ck[0];
            if(pre_T>=pre_F)
                result = "clean";
            else
                result = "buggy";
        }else{//SVM
            File f;
            if(m_type==0){
                f = new File("springboot\\weights\\svm_Cvs.txt");
            }
            else if(m_type==1){
                f = new File("springboot\\weights\\svm_LDHH.txt");
            }
            else {
                f = new File("springboot\\weights\\svm_WCHU.txt");
            }
            FileInputStream fip = new FileInputStream(f);
            // 构建FileInputStream对象
            InputStreamReader reader = new InputStreamReader(fip,"UTF-8");
            // 构建InputStreamReader对象,编码与写入相同
            StringBuffer sb = new StringBuffer();
            while(reader.ready()) {
                sb.append((char) reader.read());
            }
            reader.close();
            fip.close();
            //将读入的数据流转换为字符串
            String sb1 = sb.toString();
            String [] a0 = sb1.split(",");
            for(int i=0; i< a0.length;i++){
                weights.add(Double.parseDouble(a0[i]));
            }
            double pre = 0;
            if(data.length==weights.size()-1){
                for(int i=0;i<weights.size()-1;i++){
                    pre+=weights.get(i)*data[i];
                }
                pre+=weights.get(weights.size()-1);
                if(pre >=0.5)//这个阈值一般位于-1到1
                    result = "clean";
                else result = "buggy";
            }
        }
        return result;
    }
    private static HashMap<Integer, double[]> getwi(String filename) throws IOException {
        File f = new File(filename);
        FileInputStream fip = new FileInputStream(f);
        // 构建FileInputStream对象
        InputStreamReader reader = new InputStreamReader(fip,"UTF-8");
        // 构建InputStreamReader对象
        StringBuffer sb = new StringBuffer();
        while(reader.ready()) {
            sb.append((char) reader.read());
        }
        reader.close();
        fip.close();
        //将读入的数据流转换为字符串
        String sb1 = sb.toString();
        //按行将字符串分割,计算二维数组行数
        String [] a = sb1.split("\n");
        int n = a.length;
        //计算二维数组列数
        String [] a0 = a[0].split(",");
        int m = a0.length;
        HashMap<Integer, double[]> w = new HashMap<>();
        for(int i=0; i<n;i++){
            String[] s_temp = a[i].split(",");
            double[] temp = new double[2];
            for(int j=0; j<m;j++){
                temp[0] = Double.parseDouble(s_temp[1]);
                temp[1] = Double.parseDouble(s_temp[2]);
            }
            w.put(i, temp);
        }
        return w;
    }

    private static double Gass(double x, double mean, double stdev){// 计算高斯函数值
        double exponent = Math.exp(-(Math.pow(x-mean,2))/(2*Math.pow(stdev,2)));
        double GaussProb = (1/(Math.sqrt(2*PI)*stdev))*exponent;
        return GaussProb;
    }
    public static double sigmoid(double x) {
        double i = 1.0;
        double y = i / (i + Math.exp(-x));
        return y;
    }
}
