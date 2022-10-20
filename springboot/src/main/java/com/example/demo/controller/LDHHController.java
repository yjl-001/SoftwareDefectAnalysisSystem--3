package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.Utils.Predict;
import com.example.demo.common.Result;
import com.example.demo.entity.Data;
import com.example.demo.entity.Dataset;
import com.example.demo.entity.LDHH;
import com.example.demo.entity.UserDataset;
import com.example.demo.mapper.DataMapper;
import com.example.demo.mapper.DatasetMapper;
import com.example.demo.mapper.LDHHMapper;
import com.example.demo.mapper.UserDatasetMapper;
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
@RequestMapping("/ldhh")
public class LDHHController {

    @Resource
    LDHHMapper ldhhMapper;

    @Resource
    UserDatasetMapper userDatasetMapper;

    @Resource
    DatasetMapper datasetMapper;

    @RequestMapping("/single")
    public Result<?> single(@RequestBody LDHH ldhh) throws IOException {
        double[] pre = {ldhh.getLdhhlcom(),ldhh.getLdhhfanin(),ldhh.getLdhhnumberofpublicmethods(),ldhh.getLdhhnumberofprivateattributes(),
            ldhh.getLdhhnumberofpublicattributes(),ldhh.getLdhhnumberofprivatemethods(),ldhh.getLdhhnumberofattributesinherited(),ldhh.getLdhhnoc(),
            ldhh.getLdhhwmc(),ldhh.getLdhhnumberofattributes(),ldhh.getLdhhnumberoflinesofcode(),ldhh.getLdhhdit(),ldhh.getLdhhfanout(),
            ldhh.getLdhhnumberofmethodsinherited(),ldhh.getLdhhrfc(),ldhh.getLdhhcbo(),ldhh.getLdhhnumberofmethods()};
        System.out.println(ldhh.getModel());
        if (ldhh.getModel().equals("svm")){
            Dataset dataset = new Dataset();
            dataset.setDatasetKind("single");
            dataset.setModel("svm");
            dataset.setDatasetname("single");
            dataset.setIsdataset(1);
            Date date = new Date();
            java.sql.Date date1 = new java.sql.Date(date.getYear(),date.getMonth(),date.getDay()+16);
            dataset.setUploadtime(date1);
            datasetMapper.insert(dataset);
            QueryWrapper<Dataset> wrapper = new QueryWrapper<>();
            wrapper.select("max(datasetid) as datasetid");
            Dataset dataset1 = datasetMapper.selectOne(wrapper);


            int datasetid = dataset1.getDatasetid();
            UserDataset userDataset = new UserDataset();
            userDataset.setUserid(ldhh.getUserid());
            userDataset.setDatasetid(datasetid);
            userDatasetMapper.insert(userDataset);
            ldhhMapper.insertOne(datasetid,"svm",ldhh.getUserid(),pre[0],pre[1],pre[2],pre[3],pre[4],pre[5],pre[6],pre[7],pre[8],pre[9],pre[10],
                    pre[11],pre[12],pre[13],pre[14],pre[15],pre[16], Predict.predict(1,1,pre));
            return Result.success(Predict.predict(1,1,pre));
        }else if(ldhh.getModel().equals("logistic")){
            Dataset dataset = new Dataset();
            dataset.setDatasetKind("single");
            dataset.setModel("logistic");
            dataset.setDatasetname("single");
            dataset.setIsdataset(1);
            Date date = new Date();
            java.sql.Date date1 = new java.sql.Date(date.getYear(),date.getMonth(),date.getDay());
            dataset.setUploadtime(date1);
            datasetMapper.insert(dataset);
            QueryWrapper<Dataset> wrapper = new QueryWrapper<>();
            wrapper.select("max(datasetid) as datasetid");
            Dataset dataset1 = datasetMapper.selectOne(wrapper);


            int datasetid = dataset1.getDatasetid();
            UserDataset userDataset = new UserDataset();
            userDataset.setUserid(ldhh.getUserid());
            userDataset.setDatasetid(datasetid);
            userDatasetMapper.insert(userDataset);
            System.out.println(pre[0]);
            ldhhMapper.insertOne(datasetid,"logistic",ldhh.getUserid(),pre[0],pre[1],pre[2],pre[3],pre[4],pre[5],pre[6],pre[7],pre[8],pre[9],pre[10],
                    pre[11],pre[12],pre[13],pre[14],pre[15],pre[16],Predict.predict(0,1,pre));
            return Result.success(Predict.predict(0,1,pre));
        }else {
            Dataset dataset = new Dataset();
            dataset.setDatasetKind("single");
            dataset.setModel("beysi");
            dataset.setDatasetname("single");
            dataset.setIsdataset(1);
            Date date = new Date();
            java.sql.Date date1 = new java.sql.Date(date.getYear(),date.getMonth(),date.getDay());
            dataset.setUploadtime(date1);
            datasetMapper.insert(dataset);
            QueryWrapper<Dataset> wrapper = new QueryWrapper<>();
            wrapper.select("max(datasetid) as datasetid");
            Dataset dataset1 = datasetMapper.selectOne(wrapper);


            int datasetid = dataset1.getDatasetid();
            UserDataset userDataset = new UserDataset();
            userDataset.setUserid(ldhh.getUserid());
            userDataset.setDatasetid(datasetid);
            userDatasetMapper.insert(userDataset);
            System.out.println(pre[0]);
            ldhhMapper.insertOne(datasetid,"beysi",ldhh.getUserid(),pre[0],pre[1],pre[2],pre[3],pre[4],pre[5],pre[6],pre[7],pre[8],pre[9],pre[10],
                    pre[11],pre[12],pre[13],pre[14],pre[15],pre[16],Predict.predict(2,1,pre));
            return Result.success(Predict.predict(2,1,pre));
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

        List<LDHH> ldhhs = new ArrayList<>();

        String lineTxt;
        while ((lineTxt=bufferedReader.readLine())!=null){
            String[] strarr =lineTxt.split(",");
            double[] doubles = new double[strarr.length-1];
            for (int i = 0;i<strarr.length-1;i++){
                doubles[i] = Double.valueOf(strarr[i]);
            }
            ldhhMapper.insertOne(dataset1.getDatasetid(),model,userid,doubles[0],doubles[1],doubles[2],doubles[3],doubles[4],
                    doubles[5],doubles[6],doubles[7],doubles[8],doubles[9],doubles[10],doubles[11],doubles[12],doubles[13],
                    doubles[14],doubles[15],doubles[16], Predict.predict(modelid,1,doubles));
            QueryWrapper<LDHH> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("max(dataid) as dataid");
            LDHH ldhh = ldhhMapper.selectOne(queryWrapper);
            LambdaQueryWrapper<LDHH> wrapper3 = Wrappers.<LDHH>lambdaQuery().eq(LDHH::getDataid,ldhh.getDataid());
            LDHH ldhh1 = ldhhMapper.selectOne(wrapper3);
            ldhhs.add(ldhh1);
        }

        if (i1 != 0 && i2 !=0 ){
            return Result.success(ldhhs);
        }else
            return Result.error("1","错啦");
    }

    @RequestMapping("/hello")
    public Result<?> r(){
        LambdaQueryWrapper<LDHH> wrapper = Wrappers.<LDHH>lambdaQuery().eq(LDHH::getDatasetid,4);
        LDHH ldhh = ldhhMapper.selectOne(wrapper);

        return Result.success(ldhh);
    }
}
