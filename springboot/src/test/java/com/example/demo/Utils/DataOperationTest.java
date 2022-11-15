package com.example.demo.Utils;

import com.example.demo.Utils.DataOperation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

//线性归一化 公式：X(norm) = (X - min) / (max - min)

class DataOperationTest {

    @Test
    void normalize4Scale() { //测试接口对不对
        double[][] x = {{10,10,10},{4,5,6},{1,1,1}};
        double[][] y = {{1,1,1},{3.0/9, 4.0/9, 5.0/9},{0,0,0}};
        assertArrayEquals(y, DataOperation.normalize4Scale(x));
    }

    @Test
    void getMatrixCol() { //测试-获取矩阵的某一列，是否正确
        double[][] x = {{10,10,10},{4,5,6},{1,1,1}};
        double[] y = {10,5,1};
        assertArrayEquals(y, DataOperation.getMatrixCol(x,1));
    }

    @Test
    void minV() { //测试-获取最小值是否正确
        double[] x = {1,2,3,4,5,7};
        assertEquals(1, DataOperation.minV(x));
    }

    @Test
    void maxV() { //测试-获取最大值是否正确
        double[] x = {1, 2,3 ,5,6,7};
        assertEquals(7, DataOperation.maxV(x));
    }
}
