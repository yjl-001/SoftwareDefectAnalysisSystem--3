package com.example.demo.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoadSVMData {
    //导入样本特征
    public static double[][] LoadTrainFeature(String filename) throws IOException {
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
        System.out.println("二维数组行数为:" + n);
        //计算二维数组列数
        String [] a0 = a[0].split(",");
        int m = a0.length;
        System.out.println("二维数组列数为:" + String.valueOf(m-1));

        double [][] feature = new double[n][m-1];
        for (int i = 0; i < n; i ++) {
            String [] tmp = a[i].split(",");
            for(int j = 0; j < m-1; j ++) {//最后一列全为1，当作偏置b
                if (j == m-1) {
                    feature[i][j] = (double) 1;
                }
                else {
                    feature[i][j] = Double.parseDouble(tmp[j]);
                }
            }
        }
        return feature;
    }

    public static double[] LoadLabel(String filename) throws IOException{
        File f = new File(filename);
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
        //按行将字符串分割,计算二维数组行数
        String [] a = sb1.split("\n");
        int n = a.length;
        //计算二维数组列数
        String [] a0 = a[0].split(",");
        int m = a0.length;

        double [] Label = new double[n];
        for (int i = 0; i < n; i ++) {
            a[i] = a[i].replace("\r","");
            String [] tmp = a[i].split(",");
            if(tmp[m-1].equals("clean"))
                tmp[m-1] = "1";
            else
                tmp[m-1] = "-1";
            Label[i] = Double.parseDouble(tmp[m-1]);
        }
        return Label;
    }
}
