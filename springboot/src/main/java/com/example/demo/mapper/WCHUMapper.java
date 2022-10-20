package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.WCHU;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface WCHUMapper extends BaseMapper<WCHU> {

    @Insert({"insert into ldhh(datasetid,model,userid,wchunumberofpublicattributes,wchunumberofattributes, wchufanin," +
            "wchunumberofprivatemethods,wchunumberofmethods,wchunumberofprivateattributes,wchunoc," +
            "wchuwmc,wchudit,wchunumberofattributesinherited,wchufanout,wchulcom,wchurfc,wchunumberofpublicmethods," +
            "wchucbo,wchunumberofmethodsinherited,wchunumberoflinesofcode,predictresult) " +
            "values(#{datasetid},#{model},#{userid},#{wchunumberofpublicattributes},#{wchunumberofattributes},#{wchufanin}," +
            "#{wchunumberofprivatemethods},#{wchunumberofmethods},#{wchunumberofprivateattributes},#{wchunoc}," +
            "#{wchuwmc},#{wchudit},#{wchunumberofattributesinherited},#{wchufanout},#{wchulcom},#{wchurfc},#{wchunumberofpublicmethods}," +
            "#{wchucbo},#{wchunumberofmethodsinherited},#{wchunumberoflinesofcode},#{predictresult})"})
    void insertOne(@Param("datasetid") int datasetid,
                   @Param("model") String svm,
                   @Param("userid") int userid,
                   @Param("wchunumberofpublicattributes") double wchunumberofpublicattributes,
                   @Param("wchunumberofattributes") double wchunumberofattributes,
                   @Param("wchufanin") double wchufanin,
                   @Param("wchunumberofprivatemethods") double wchunumberofprivatemethods,
                   @Param("wchunumberofmethods") double wchunumberofmethods,
                   @Param("wchunumberofprivateattributes") double wchunumberofprivateattributes,
                   @Param("wchunoc") double wchunoc,
                   @Param("wchuwmc") double wchuwmc,
                   @Param("wchudit") double wchudit,
                   @Param("wchunumberofattributesinherited") double wchunumberofattributesinherited,
                   @Param("wchufanout") double wchufanout,
                   @Param("wchulcom") double wchulcom,
                   @Param("wchurfc") double wchurfc,
                   @Param("wchunumberofpublicmethods") double wchunumberofpublicmethods,
                   @Param("wchucbo") double wchucbo,
                   @Param("wchunumberofmethodsinherited") double wchunumberofmethodsinherited,
                   @Param("wchunumberoflinesofcode") double wchunumberoflinesofcode,
                   @Param("predictresult") String predictresult);
}
