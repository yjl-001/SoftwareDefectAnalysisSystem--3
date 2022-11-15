package com.example.demo.algo;

import com.example.demo.Utils.DataOperation;
import com.example.demo.Utils.LoadData;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LogisticRegressionTest {
    String filename = "C:\\Users\\yjl\\Desktop\\SoftwareDefectAnalysisSystem--3\\data\\JDT_Cvs.csv";
    double[][] a = com.example.demo.Utils.LoadData.LoadTrainFeature(filename);
    double[][] x_train = DataOperation.normalize4Scale(a);
    //double[][] x_train = a;
    double[] l = com.example.demo.Utils.LoadData.LoadLabel(filename);
    LogisticRegression lr = new LogisticRegression(0.5, 1000, 0.00001,x_train[0].length,x_train.length,x_train, l);

    LogisticRegressionTest() throws IOException {
    }

    @Test
    void sigmoid() {
        assertEquals(0.5,lr.sigmoid(0));
    }

    @Test
    void Update(){
        assertNotNull(lr.Updata());
    }

    @Test
    void test() throws IOException {
        lr.Updata();
        String testFileName = "C:\\Users\\yjl\\Desktop\\SoftwareDefectAnalysisSystem--3\\data\\Lucene_Cvs.csv";
        double[][] b = LoadData.LoadTrainFeature(testFileName);
        double[][] x_test = DataOperation.normalize4Scale(b);
        //double[][] x_test = b;
        double[] y_test = LoadData.LoadLabel(testFileName);
        lr.test(x_test, y_test);
    }

}