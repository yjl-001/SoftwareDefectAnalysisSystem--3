package com.example.demo.entity;

import lombok.Data;

@Data
public class Search {
    private String datasetname;
    private String datasetKind;
    private String model;
    private int isdataset;//评价指标
}
