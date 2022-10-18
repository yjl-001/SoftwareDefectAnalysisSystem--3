package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Utils.Predict;
import com.example.demo.common.Result;
import com.example.demo.entity.Data;
import com.example.demo.mapper.DataMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/predict")
public class DataController {

    @Resource
    DataMapper dataMapper;

//    @RequestParam(defaultValue = "svm") String model,
//    @RequestParam(defaultValue = "1") double numberOfNonTrivialBugsFoundUntil,
//    @RequestParam(defaultValue = "1") double CvsWEntropy,
//    @RequestParam(defaultValue = "1") double CvsEntropy,
//    @RequestParam(defaultValue = "1") double numberOfCriticalBugsFoundUntil,
//    @RequestParam(defaultValue = "1") double CvsLogEntropy,
//    @RequestParam(defaultValue = "1") double numberOfHighPriorityBugsFoundUntil,
//    @RequestParam(defaultValue = "1") double numberOfMajorBugsFoundUntil,
//    @RequestParam(defaultValue = "1")  double CvsLinEntropy,
//    @RequestParam(defaultValue = "1") double numberOfBugsFoundUntil,
//    @RequestParam(defaultValue = "1")  double CvsExpEntropy
    @RequestMapping("/single")
    public Result<?> single(@RequestBody Data data) throws IOException {
        double[] pre = {data.getNumberOfNonTrivialBugsFoundUntil(),data.getCvsWEntropy(),data.getCvsEntropy(),data.getNumberOfCriticalBugsFoundUntil(),
                        data.getCvsLogEntropy(),data.getNumberOfHighPriorityBugsFoundUntil(),data.getNumberOfMajorBugsFoundUntil(),
                        data.getCvsLinEntropy(),data.getNumberOfBugsFoundUntil(),data.getCvsExpEntropy()};
        System.out.println(data.getModel());
        System.out.println(data.getNumberOfNonTrivialBugsFoundUntil());
        if (data.getModel().equals("svm")){
            dataMapper.insertOne(-1,"svm",1,pre[0],pre[1],pre[2],pre[3],pre[4],pre[5],pre[6],pre[7],pre[8],pre[9],Predict.predict(1,pre));
            return Result.success(Predict.predict(1,pre));
        }else {
            System.out.println(pre[0]);
            dataMapper.insertOne(-1,"logistic",1,pre[0],pre[1],pre[2],pre[3],pre[4],pre[5],pre[6],pre[7],pre[8],pre[9],Predict.predict(0,pre));
            return Result.success(Predict.predict(0,pre));
        }

    }

    @RequestMapping("dataset")
    public Result<?> dataset(@RequestParam MultipartFile file){
        String filename = file.getOriginalFilename();
        String type= filename.substring(filename.lastIndexOf(".")+1);
        return Result.success();
    }
}
