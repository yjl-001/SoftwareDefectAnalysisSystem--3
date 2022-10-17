package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("userdataset")
@Data
public class UserDataset {
    private int userid;
    private int datasetid;
}
