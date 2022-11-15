package com.example.demo.algo;

import com.example.demo.Utils.LoadSVMData;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BeysiTest {
    @Test
    void test() throws IOException {
        String filePath = "C:\\Users\\yjl\\Desktop\\SoftwareDefectAnalysisSystem--3\\data\\JDT_Cvs.csv";
        double[] y = LoadSVMData.LoadLabel(filePath);
        double[][] X = LoadSVMData.LoadTrainFeature(filePath);

        Beysi beysi = new Beysi(X, y);
        String testFilePath = "C:\\Users\\yjl\\Desktop\\SoftwareDefectAnalysisSystem--3\\data\\Lucene_Cvs.csv";
        double[] test_y = LoadSVMData.LoadLabel(testFilePath);
        double[][] test_X = LoadSVMData.LoadTrainFeature(testFilePath);
        beysi.test(test_X, test_y);
    }

}