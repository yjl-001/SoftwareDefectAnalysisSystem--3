package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("ldhh")
@Data
public class LDHH {
    private int datasetid;
    private int dataid;
    private String model;
    private double ldhhlcom;
    private double ldhhfanin;
    private double ldhhnumberofpublicmethods;
    private double ldhhnumberofprivateattributes;
    private double ldhhnumberofpublicattributes;
    private double ldhhnumberofprivatemethods;
    private double ldhhnumberofattributesinherited;
    private double ldhhnoc;
    private double ldhhwmc;
    private double ldhhnumberofattributes;
    private double ldhhnumberoflinesofcode;
    private double ldhhdit;
    private double ldhhfanout;
    private double ldhhnumberofmethodsinherited;
    private double ldhhrfc;
    private double ldhhcbo;
    private double ldhhnumberofmethods;
    private String predictresult;

}
