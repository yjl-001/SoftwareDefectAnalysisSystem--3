package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Utils.Predict;
import com.example.demo.common.Result;
import com.example.demo.entity.Dataset;
import com.example.demo.entity.LDHH;
import com.example.demo.entity.UserDataset;
import com.example.demo.entity.WCHU;
import com.example.demo.mapper.DatasetMapper;
import com.example.demo.mapper.UserDatasetMapper;
import com.example.demo.mapper.WCHUMapper;
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
@RequestMapping("/wchu")
public class WCHUController {

    @Resource
    WCHUMapper wchuMapper;
    @Resource
    UserDatasetMapper userDatasetMapper;

    @Resource
    DatasetMapper datasetMapper;

    @RequestMapping("/single")
    public Result<?> single(@RequestBody WCHU wchu) throws IOException {
        double[] pre = {wchu.getWchunumberofpublicattributes(),wchu.getWchunumberofattributes(),wchu.getWchufanin(),
                wchu.getWchunumberofprivatemethods(),wchu.getWchunumberofmethods(),wchu.getWchunumberofprivateattributes(),
                wchu.getWchunoc(),wchu.getWchuwmc(),wchu.getWchudit(),wchu.getWchunumberofattributesinherited(),wchu.getWchufanout(),
                wchu.getWchulcom(),wchu.getWchurfc(),wchu.getWchunumberofpublicmethods(),wchu.getWchucbo(),
                wchu.getWchunumberofmethodsinherited(),wchu.getWchunumberoflinesofcode()};
        System.out.println(wchu.getModel());
        if (wchu.getModel().equals("svm")){
            Dataset dataset = new Dataset();
            dataset.setDatasetKind("single");
            dataset.setModel("svm");
            dataset.setDatasetname("single");
            dataset.setIsdataset(2);
            Date date = new Date();
            java.sql.Date date1 = new java.sql.Date(date.getYear(),date.getMonth(),date.getDay()+16);
            dataset.setUploadtime(date1);
            datasetMapper.insert(dataset);
            QueryWrapper<Dataset> wrapper = new QueryWrapper<>();
            wrapper.select("max(datasetid) as datasetid");
            Dataset dataset1 = datasetMapper.selectOne(wrapper);


            int datasetid = dataset1.getDatasetid();
            UserDataset userDataset = new UserDataset();
            userDataset.setUserid(wchu.getUserid());
            userDataset.setDatasetid(datasetid);
            userDatasetMapper.insert(userDataset);
            wchuMapper.insertOne(datasetid,"svm",wchu.getUserid(),pre[0],pre[1],pre[2],pre[3],pre[4],pre[5],pre[6],pre[7],pre[8],pre[9],pre[10],
                    pre[11],pre[12],pre[13],pre[14],pre[15],pre[16], Predict.predict(1,2,pre));
            return Result.success(Predict.predict(1,2,pre));
        }else if(wchu.getModel().equals("logistic")){
            Dataset dataset = new Dataset();
            dataset.setDatasetKind("single");
            dataset.setModel("logistic");
            dataset.setDatasetname("single");
            dataset.setIsdataset(2);
            Date date = new Date();
            java.sql.Date date1 = new java.sql.Date(date.getYear(),date.getMonth(),date.getDay());
            dataset.setUploadtime(date1);
            datasetMapper.insert(dataset);
            QueryWrapper<Dataset> wrapper = new QueryWrapper<>();
            wrapper.select("max(datasetid) as datasetid");
            Dataset dataset1 = datasetMapper.selectOne(wrapper);


            int datasetid = dataset1.getDatasetid();
            UserDataset userDataset = new UserDataset();
            userDataset.setUserid(wchu.getUserid());
            userDataset.setDatasetid(datasetid);
            userDatasetMapper.insert(userDataset);
            System.out.println(pre[0]);
            wchuMapper.insertOne(datasetid,"logistic",wchu.getUserid(),pre[0],pre[1],pre[2],pre[3],pre[4],pre[5],pre[6],pre[7],pre[8],pre[9],pre[10],
                    pre[11],pre[12],pre[13],pre[14],pre[15],pre[16],Predict.predict(0,2,pre));
            return Result.success(Predict.predict(0,2,pre));
        }else {
            Dataset dataset = new Dataset();
            dataset.setDatasetKind("single");
            dataset.setModel("beysi");
            dataset.setDatasetname("single");
            dataset.setIsdataset(2);
            Date date = new Date();
            java.sql.Date date1 = new java.sql.Date(date.getYear(),date.getMonth(),date.getDay());
            dataset.setUploadtime(date1);
            datasetMapper.insert(dataset);
            QueryWrapper<Dataset> wrapper = new QueryWrapper<>();
            wrapper.select("max(datasetid) as datasetid");
            Dataset dataset1 = datasetMapper.selectOne(wrapper);


            int datasetid = dataset1.getDatasetid();
            UserDataset userDataset = new UserDataset();
            userDataset.setUserid(wchu.getUserid());
            userDataset.setDatasetid(datasetid);
            userDatasetMapper.insert(userDataset);
            System.out.println(pre[0]);
            wchuMapper.insertOne(datasetid,"beysi",wchu.getUserid(),pre[0],pre[1],pre[2],pre[3],pre[4],pre[5],pre[6],pre[7],pre[8],pre[9],pre[10],
                    pre[11],pre[12],pre[13],pre[14],pre[15],pre[16],Predict.predict(2,2,pre));
            return Result.success(Predict.predict(2,2,pre));
        }

    }

    @PostMapping("/dataset")
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
        dataset.setIsdataset(2);

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

        List<WCHU> wchus = new ArrayList<>();

        String lineTxt;
        while ((lineTxt=bufferedReader.readLine())!=null){
            String[] strarr =lineTxt.split(",");
            double[] doubles = new double[strarr.length-1];
            for (int i = 0;i<strarr.length-1;i++){
                doubles[i] = Double.valueOf(strarr[i]);
            }
            wchuMapper.insertOne(dataset1.getDatasetid(),model,userid,doubles[0],doubles[1],doubles[2],doubles[3],doubles[4],
                    doubles[5],doubles[6],doubles[7],doubles[8],doubles[9],doubles[10],doubles[11],doubles[12],doubles[13],
                    doubles[14],doubles[15],doubles[16], Predict.predict(modelid,2,doubles));
            QueryWrapper<WCHU> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("max(dataid) as dataid");
            WCHU wchu = wchuMapper.selectOne(queryWrapper);
            wchus.add(wchu);
        }

        if (i1 != 0 && i2 !=0 ){
            return Result.success(wchus);
        }else
            return Result.error("1","错啦");
    }
}
