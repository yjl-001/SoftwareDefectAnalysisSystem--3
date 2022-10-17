package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.web.bind.annotation.RequestParam;

@TableName("data")
@lombok.Data
public class Data {
    private int userid;
    private int dataid;
    private int datasetid;
    private String model;
    private double numberOfNonTrivialBugsFoundUntil;
    private double cvsWEntropy;
    private double cvsEntropy;
    private double numberOfCriticalBugsFoundUntil;
    private double cvsLogEntropy;
    private double numberOfHighPriorityBugsFoundUntil;
    private double numberOfMajorBugsFoundUntil;
    private double cvsLinEntropy;
    private double numberOfBugsFoundUntil;
    private double cvsExpEntropy;
    private String predictresult;
}
