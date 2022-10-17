package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("SELECT * FROM userinfo")
    List<UserInfo> selectAll();
}
