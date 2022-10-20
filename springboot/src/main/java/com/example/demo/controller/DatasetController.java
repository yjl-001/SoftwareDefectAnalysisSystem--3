package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
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
    LDHHMapper ldhhMapper;

    @Resource
    WCHUMapper wchuMapper;
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    UserDatasetMapper userDatasetMapper;

    @Resource
    DataMapper dataMapper;

    @RequestMapping(value = "/datasetResult")
    public Result<?> result(@RequestParam Integer datasetid){
        LambdaQueryWrapper<Dataset> wrapper1 = Wrappers.<Dataset>lambdaQuery().eq(Dataset::getDatasetid,datasetid);
        Dataset dataset = datasetMapper.selectOne(wrapper1);
        switch (dataset.getIsdataset()){
            case 0:
                LambdaQueryWrapper<Data> wrapper = Wrappers.<Data>lambdaQuery().eq(Data::getDatasetid,datasetid);
                List<Data> datas = dataMapper.selectList(wrapper);
                return Result.success(datas);
            case 1:
                LambdaQueryWrapper<LDHH> wrapper2 = Wrappers.<LDHH>lambdaQuery().eq(LDHH::getDatasetid,datasetid);
                List<LDHH> ldhhs = ldhhMapper.selectList(wrapper2);
                return Result.success(ldhhs);
            case 2:
                LambdaQueryWrapper<WCHU> wrapper3 = Wrappers.<WCHU>lambdaQuery().eq(WCHU::getDatasetid,datasetid);
                List<WCHU> wchus = wchuMapper.selectList(wrapper3);
                return Result.success(wchus);
            default:
                return Result.error("1","什么鬼");
        }

    }

    @RequestMapping(value = "/deleteHistory")
    public Result<?> del(@RequestParam Integer datasetid){

        QueryWrapper<Dataset> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("datasetid",datasetid);
        Dataset dataset = datasetMapper.selectOne(queryWrapper2);
        int i1;

        switch (dataset.getIsdataset()){
            case 0:
                QueryWrapper<Data> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("datasetid",datasetid);
                i1 = dataMapper.delete(queryWrapper);
                break;
            case 1:
                QueryWrapper<LDHH> queryWrapper5 = new QueryWrapper<>();
                queryWrapper5.eq("datasetid",datasetid);
                i1 = ldhhMapper.delete(queryWrapper5);
                break;
            case 2:
                QueryWrapper<WCHU> queryWrapper6 = new QueryWrapper<>();
                queryWrapper6.eq("datasetid",datasetid);
                i1 = wchuMapper.delete(queryWrapper6);
                break;
            default:
                i1 = 0;
                break;
        }


        QueryWrapper<UserDataset> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("datasetid",datasetid);
        int i3 = userDatasetMapper.delete(queryWrapper1);

        int i2 = datasetMapper.delete(queryWrapper2);

        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);


        if (i1!=0 && i2!=0 && i3!=0){
            return Result.success();
        }else{
            return Result.error("0","删除失败");
        }

    }

    @RequestMapping(value = "/search")
    public Result<?> queryDataset(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam String username,
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
        System.out.println(search);
        if (!StrUtil.isNotBlank(search.getDatasetname()) && !StrUtil.isNotBlank(search.getDatasetKind()) && !StrUtil.isNotBlank(search.getModel()) && !StrUtil.isNotBlank(String.valueOf(search.getIsdataset()))){
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
            if (StrUtil.isNotBlank(String.valueOf(search.getIsdataset()))){
                wrapper1.eq(Dataset::getIsdataset,search.getIsdataset());
            }
            Page<Dataset> datasetPage = datasetMapper.selectPage(new Page<>(pageNum,pageSize),wrapper1);

            return Result.success(datasetPage);
        }

    }

    @RequestMapping("none")
    public Result<?> queryNone(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam String username){

        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUsername,username);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);

        List<UserDataset> datasets = userDatasetMapper.selectAll(userInfo.getUserid());
        List<Integer> datasetIDs = new ArrayList<>();
        for (int i =0;i<datasets.size();i++){
            datasetIDs.add(datasets.get(i).getDatasetid());
        }
            LambdaQueryWrapper<Dataset> wrapper3 = Wrappers.<Dataset>lambdaQuery().orderByAsc(Dataset::getDatasetname);
            wrapper3.in(Dataset::getDatasetid,datasetIDs);
            Page<Dataset> datasetPage = datasetMapper.selectPage(new Page<>(pageNum,pageSize),wrapper3);


            return Result.success(datasetPage);

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
