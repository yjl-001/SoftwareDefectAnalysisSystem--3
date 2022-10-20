package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("wchu")
@Data
public class WCHU {
    private int userid;
    private int datasetid;
    private int dataid;
    private String model;
    private double wchunumberofpublicattributes;
    private double wchunumberofattributes;
    private double wchufanin;
    private double wchunumberofprivatemethods;
    private double wchunumberofmethods;
    private double wchunumberofprivateattributes;
    private double wchunoc;
    private double wchuwmc;
    private double wchudit;
    private double wchunumberofattributesinherited;
    private double wchufanout;
    private double wchulcom;
    private double wchurfc;
    private double wchunumberofpublicmethods;
    private double wchucbo;
    private double wchunumberofmethodsinherited;
    private double wchunumberoflinesofcode;
    private String predictresult;
}
