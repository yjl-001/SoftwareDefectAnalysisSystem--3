package com.example.demo.Utils;

import java.io.*;
import java.util.ArrayList;

public class Predict {
    public static String predict(int type, double[] data) throws IOException {
        String result = "";
        ArrayList<Double> weights = new ArrayList<Double>();
        if(type==0){//逻辑回归
            File f = new File("springboot\\weights\\lr.txt");
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
        }else{//SVM
            File f = new File("springboot\\weights\\svm.txt");
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
    public static double sigmoid(double x) {
        double i = 1.0;
        double y = i / (i + Math.exp(-x));
        return y;
    }
}
