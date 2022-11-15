package com.example.demo.Utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;

// 读取SVM数据

import static org.junit.jupiter.api.Assertions.*;

class LoadSVMDataTest {
    String fileName = "/Users/wangjine/Desktop/SoftwareDefectAnalysisSystem--3-master/springboot/src/test/java/test.csv";
    @Test
    void loadTrainFeature() throws IOException { //读取特征
        double[][] expected = {{1,1,1,1,1},
                {2,2,2,2,2},
                {3,3,3,3,3},
                {4,4,4,4,4},
                {5,5,5,5,5}};
        assertArrayEquals(expected,LoadSVMData.LoadTrainFeature(fileName));
    }

    @Test
    void loadLabel() throws IOException { //读取标签
        double[] expected = {1,1,-1,1,-1};
        assertArrayEquals(expected,LoadSVMData.LoadLabel(fileName));
    }
}
