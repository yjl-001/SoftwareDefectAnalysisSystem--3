package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;


@TableName("dataset")
@Data
public class Dataset {
    private int datasetid;
    private String datasetname;
    private Date uploadtime;
    private String datasetKind;
    private String model;
}
