package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Utils.Predict;
import com.example.demo.common.Result;
import com.example.demo.entity.Data;
import com.example.demo.entity.Dataset;
import com.example.demo.entity.UserDataset;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.DataMapper;
import com.example.demo.mapper.DatasetMapper;
import com.example.demo.mapper.UserDatasetMapper;
import com.example.demo.mapper.UserInfoMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/predict")
public class DataController {

    @Resource
    DataMapper dataMapper;

    @Resource
    UserDatasetMapper userDatasetMapper;

    @Resource
    DatasetMapper datasetMapper;

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
            Dataset dataset = new Dataset();
            dataset.setDatasetKind("single");
            dataset.setModel("svm");
            dataset.setDatasetname("single");
            dataset.setIsdataset(0);
            Date date = new Date();
            java.sql.Date date1 = new java.sql.Date(date.getYear(),date.getMonth(),date.getDay()+16);
            dataset.setUploadtime(date1);
            datasetMapper.insert(dataset);
            QueryWrapper<Dataset> wrapper = new QueryWrapper<>();
            wrapper.select("max(datasetid) as datasetid");
            Dataset dataset1 = datasetMapper.selectOne(wrapper);


            int datasetid = dataset1.getDatasetid();
            UserDataset userDataset = new UserDataset();
            userDataset.setUserid(data.getUserid());
            userDataset.setDatasetid(datasetid);
            userDatasetMapper.insert(userDataset);
            dataMapper.insertOne(datasetid,"svm",data.getUserid(),pre[0],pre[1],pre[2],pre[3],pre[4],pre[5],pre[6],pre[7],pre[8],pre[9],Predict.predict(1,0,pre));
            return Result.success(Predict.predict(1,0,pre));
        }else if(data.getModel().equals("logistic")){
            Dataset dataset = new Dataset();
            dataset.setDatasetKind("single");
            dataset.setModel("logistic");
            dataset.setDatasetname("single");
            dataset.setIsdataset(0);
            Date date = new Date();
            java.sql.Date date1 = new java.sql.Date(date.getYear(),date.getMonth(),date.getDay());
            dataset.setUploadtime(date1);
            datasetMapper.insert(dataset);
            QueryWrapper<Dataset> wrapper = new QueryWrapper<>();
            wrapper.select("max(datasetid) as datasetid");
            Dataset dataset1 = datasetMapper.selectOne(wrapper);


            int datasetid = dataset1.getDatasetid();
            UserDataset userDataset = new UserDataset();
            userDataset.setUserid(data.getUserid());
            userDataset.setDatasetid(datasetid);
            userDatasetMapper.insert(userDataset);
            System.out.println(pre[0]);
            dataMapper.insertOne(datasetid,"logistic",data.getUserid(),pre[0],pre[1],pre[2],pre[3],pre[4],pre[5],pre[6],pre[7],pre[8],pre[9],Predict.predict(0,0,pre));
            return Result.success(Predict.predict(0,0,pre));
        }else {
            Dataset dataset = new Dataset();
            dataset.setDatasetKind("single");
            dataset.setModel("beysi");
            dataset.setDatasetname("single");
            dataset.setIsdataset(0);
            Date date = new Date();
            java.sql.Date date1 = new java.sql.Date(date.getYear(),date.getMonth(),date.getDay());
            dataset.setUploadtime(date1);
            datasetMapper.insert(dataset);
            QueryWrapper<Dataset> wrapper = new QueryWrapper<>();
            wrapper.select("max(datasetid) as datasetid");
            Dataset dataset1 = datasetMapper.selectOne(wrapper);


            int datasetid = dataset1.getDatasetid();
            UserDataset userDataset = new UserDataset();
            userDataset.setUserid(data.getUserid());
            userDataset.setDatasetid(datasetid);
            userDatasetMapper.insert(userDataset);
            System.out.println(pre[0]);
            dataMapper.insertOne(datasetid,"beysi",data.getUserid(),pre[0],pre[1],pre[2],pre[3],pre[4],pre[5],pre[6],pre[7],pre[8],pre[9],Predict.predict(2,0,pre));
            return Result.success(Predict.predict(2,0,pre));
        }

    }

    @PostMapping ("/dataset")
    public Result<?> dataset(@RequestParam MultipartFile file,
                             @RequestParam Integer userid,
                             @RequestParam String model) throws IOException {
        Dataset dataset = new Dataset();
        UserDataset userDataset = new UserDataset();

        String filename = file.getOriginalFilename();
        int index = filename.lastIndexOf(".");
        String type= filename.substring(index+1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String names = bufferedReader.readLine();

        dataset.setDatasetKind(type);
        dataset.setDatasetname(filename.substring(0,index));
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getYear(),date.getMonth(),date.getDay()+16);
        dataset.setUploadtime(date1);
        dataset.setModel(model);
        dataset.setIsdataset(1);

        int i1 = datasetMapper.insert(dataset);

        QueryWrapper<Dataset> wrapper = new QueryWrapper<>();
        wrapper.select("max(datasetid) as datasetid");
        Dataset dataset1 = datasetMapper.selectOne(wrapper);
        userDataset.setDatasetid(dataset1.getDatasetid());
        userDataset.setUserid(userid);

        int i2 = userDatasetMapper.insert(userDataset);

        int modelid;
        if (model.equals("svm")){
            modelid = 1;
        }else if (model.equals("logistic")){
            modelid = 0;
        }else {
            modelid = 2;
        }

        List<Data> data = new ArrayList<>();

        String lineTxt;
        while ((lineTxt=bufferedReader.readLine())!=null){
            String[] strarr =lineTxt.split(",");
            double[] doubles = new double[strarr.length-1];
            for (int i = 0;i<strarr.length-1;i++){
                doubles[i] = Double.valueOf(strarr[i]);
            }
            dataMapper.insertOne(dataset1.getDatasetid(),model,userid,doubles[0],doubles[1],doubles[2],doubles[3],doubles[4],
                    doubles[5],doubles[6],doubles[7],doubles[8],doubles[9],Predict.predict(modelid,0,doubles));
            QueryWrapper<Data> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("max(dataid) as dataid");
            Data data1 = dataMapper.selectOne(queryWrapper);
            data.add(data1);
        }

        if (i1 != 0 && i2 !=0 ){
            return Result.success(data);
        }else
            return Result.error("0","错啦");
    }
}
