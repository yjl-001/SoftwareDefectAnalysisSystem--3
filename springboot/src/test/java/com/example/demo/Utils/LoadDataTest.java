package com.example.demo.Utils;

import org.junit.jupiter.api.Test;
import com.example.demo.Utils.LoadData;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
//读数据测试
class LoadDataTest {
    String fileName = "/Users/wangjine/Desktop/SoftwareDefectAnalysisSystem--3-master/springboot/src/test/java/test.csv";
    @Test
    void loadTrainFeature() throws IOException { //测试特征的读取
        double[][] expected = {{1,1,1,1,1,1},
                {2,2,2,2,2,1},
                {3,3,3,3,3,1},
                {4,4,4,4,4,1},
                {5,5,5,5,5,1}};
        assertArrayEquals(expected,LoadData.LoadTrainFeature(fileName));

    }

    @Test
    void loadLabel() throws IOException { //测试标签的读取
        double[] expected = {1,1,0,1,0};
        assertArrayEquals(expected,LoadData.LoadLabel(fileName));
    }
}
