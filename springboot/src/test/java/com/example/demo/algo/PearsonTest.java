package com.example.demo.algo;

import org.junit.jupiter.api.Test;

import static com.example.demo.algo.Pearson.getPearsonCorrelationScore;
import static org.junit.jupiter.api.Assertions.*;

class PearsonTest {

    @Test
    void test(){
        double[] x = new double[] { 38 ,13 ,27, 25, 18, 29, 30, 20, 23, 32, 38, 28, 34, 19, 20, 25, 16, 36, 25, 17, 24, 18, 30, 35, 22, 34, 12, 26, 29, 21};
        //double[] y = new double[] { 49 ,42, 45, 39, 30, 25, 39, 36, 39 ,45, 42, 50, 36, 48, 35, 42, 45, 29, 33, 27, 42, 43, 27, 39, 37, 36, 47, 37, 44, 34};
        double[] y = new double[] {114, 49, 84, 79, 87, 74, 77, 82, 80, 88, 123, 82, 98, 65, 61, 78, 51, 121, 78, 50, 75, 65, 113, 122, 78, 119, 45, 89, 102, 75};
        double score = getPearsonCorrelationScore(x, y);
        System.out.println(score);
    }
}