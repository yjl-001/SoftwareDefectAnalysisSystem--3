package com.example.demo.Utils;

import org.junit.jupiter.api.Test;


import java.io.IOException;
//做预测的

import static org.junit.jupiter.api.Assertions.*;

class PredictTest {
    double[] cvs_test = {4,0.004517,8.73596,0,8.1114,1,0,0.134499,4,0.198199};
    double[] LDHH_test = {0.002984,0,0.003137,0.002748,0,0,0,0,0.003155,0.002785,0.003064,0,0.020279,0.005149,0.002921,0.015089,0.003155};
    double[] WCHU_test = {0,1.01,0,0,1.02,1.01,0,1.06,0,0,1.01,1.41,1.04,1.01,1.01,1.01,1.16};
    @Test
    void predict_lr_Cvs() throws IOException { //对逻辑回归做Csv做预测
        assertEquals("clean",Predict.predict(0,0,cvs_test));
    }
    @Test
    void predict_lr_LDHH() throws IOException {
        assertEquals("clean",Predict.predict(0,1,LDHH_test));
    }
    @Test
    void predict_lr_WCHU() throws IOException {
        assertEquals("clean",Predict.predict(0,2,WCHU_test));
    }
    @Test
    void predict_svm_Cvs() throws IOException { //对svm做
        assertEquals("clean",Predict.predict(1,0,cvs_test));
    }
    @Test
    void predict_svm_LDHH() throws IOException {
        assertEquals("clean",Predict.predict(1,1,cvs_test));
    }
    @Test
    void predict_svm_WCHU() throws IOException {
        assertEquals("clean",Predict.predict(1,2,cvs_test));
    }
    @Test
    void predict_by_Cvs() throws IOException { //对贝叶斯
        assertEquals("clean",Predict.predict(2,0,cvs_test));
    }
    @Test
    void predict_by_LDHH() throws IOException {
        assertEquals("clean",Predict.predict(2,1,cvs_test));
    }
    @Test
    void predict_by_WCHU() throws IOException {
        assertEquals("clean",Predict.predict(2,2,cvs_test));
    }

}
