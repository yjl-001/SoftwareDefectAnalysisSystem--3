package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Dataset;
import com.example.demo.entity.UserDataset;
import com.example.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDatasetMapper extends BaseMapper<UserDataset> {

    @Select("SELECT * FROM userdataset where userid = #{userid}")
    List<UserDataset> selectAll(@Param("userid") int userid);
}
