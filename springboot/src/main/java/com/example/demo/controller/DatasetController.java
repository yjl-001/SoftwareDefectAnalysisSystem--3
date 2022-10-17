package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Dataset;
import com.example.demo.entity.Search;
import com.example.demo.entity.UserDataset;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.DatasetMapper;
import com.example.demo.mapper.UserDatasetMapper;
import com.example.demo.mapper.UserInfoMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/datasets_center")
public class DatasetController {

    @Resource
    DatasetMapper datasetMapper;
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    UserDatasetMapper userDatasetMapper;

    @RequestMapping(value = "/search")
    public Result<?> queryDataset(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "admin") String username,
                                  Search search
    ){

        System.out.println(username);
        System.out.println(pageSize);
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUsername,username);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);

        List<UserDataset> datasets = userDatasetMapper.selectAll(userInfo.getUserid());
        List<Integer> datasetIDs = new ArrayList<>();
        for (int i =0;i<datasets.size();i++){
            datasetIDs.add(datasets.get(i).getDatasetid());
        }
        datasetIDs.add(-1);
        System.out.println(search);
        if (search == null){
            System.out.println("aaaaaaaaaa");
            LambdaQueryWrapper<Dataset> wrapper3 = Wrappers.<Dataset>lambdaQuery().orderByAsc(Dataset::getDatasetname);
            wrapper3.in(Dataset::getDatasetid,datasetIDs);
            Page<Dataset> datasetPage = datasetMapper.selectPage(new Page<>(pageNum,pageSize),wrapper3);


            return Result.success(datasetPage);
        }else {
            System.out.println("bbbbbbbbbbbbbb");
            LambdaQueryWrapper<Dataset> wrapper1;
            if (StrUtil.isNotBlank(search.getDatasetname())){
                wrapper1 = Wrappers.<Dataset>lambdaQuery().like(Dataset::getDatasetname,search.getDatasetname());
            }else {
                wrapper1 = Wrappers.<Dataset>lambdaQuery().in(Dataset::getDatasetid,datasetIDs);
            }
            if (StrUtil.isNotBlank(search.getDatasetKind())){
                wrapper1 = wrapper1.like(Dataset::getDatasetKind,search.getDatasetKind());
            }
            if (StrUtil.isNotBlank(search.getModel())){
                wrapper1.eq(Dataset::getModel,search.getModel());
            }
            Page<Dataset> datasetPage = datasetMapper.selectPage(new Page<>(pageNum,pageSize),wrapper1);

            return Result.success(datasetPage);
        }

    }

    @RequestMapping("/none")
    public Result<?> queryNone(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestBody(required = false) Search search,
                                  @RequestParam(defaultValue = "admin") String username){

        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUsername,username);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);

        List<UserDataset> datasets = userDatasetMapper.selectAll(userInfo.getUserid());
        List<Integer> datasetIDs = new ArrayList<>();
        for (int i =0;i<datasets.size();i++){
            datasetIDs.add(datasets.get(i).getDatasetid());
        }
        datasetIDs.add(-1);
        System.out.println(search);
        if (search == null){
            LambdaQueryWrapper<Dataset> wrapper3 = Wrappers.<Dataset>lambdaQuery().orderByAsc(Dataset::getDatasetname);
            wrapper3.in(Dataset::getDatasetid,datasetIDs);
            Page<Dataset> datasetPage = datasetMapper.selectPage(new Page<>(pageNum,pageSize),wrapper3);


            return Result.success(datasetPage);
        }else {
            LambdaQueryWrapper<Dataset> wrapper1;
            if (StrUtil.isNotBlank(search.getDatasetname())){
                wrapper1 = Wrappers.<Dataset>lambdaQuery().like(Dataset::getDatasetname,search.getDatasetname());
            }else {
                wrapper1 = Wrappers.<Dataset>lambdaQuery().in(Dataset::getDatasetid,datasetIDs);
            }
            if (StrUtil.isNotBlank(search.getDatasetKind())){
                wrapper1 = wrapper1.like(Dataset::getDatasetKind,search.getDatasetKind());
            }
            if (StrUtil.isNotBlank(search.getModel())){
                wrapper1.eq(Dataset::getModel,search.getModel());
            }
            Page<Dataset> datasetPage = datasetMapper.selectPage(new Page<>(pageNum,pageSize),wrapper1);

            return Result.success(datasetPage);
        }

    }
//    @GetMapping
//    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
//                              @RequestParam(defaultValue = "10") Integer pageSize,
//                              @RequestParam(defaultValue = "") String search) {
//        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().orderByAsc(User::getId);
//        if (StrUtil.isNotBlank(search)) {
//            wrapper.like(User::getNickName, search);
//        }
//
//        Page<User> userPage = userMapper.findPage(new Page<>(pageNum, pageSize), search);
//        // 设置用户的角色id列表
//        for (User record : userPage.getRecords()) {
//            List<UserRole> roles = roleMapper.getUserRoleByUserId(record.getId());
//            List<Integer> roleIds = roles.stream().map(UserRole::getRoleId).distinct().collect(Collectors.toList());
//            record.setRoles(roleIds);
//        }
//        return Result.success(userPage);
//    }
}
