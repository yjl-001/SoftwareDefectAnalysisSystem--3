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
                   @Param("wchunumberofpublicattributes") double ldhhlcom,
                   @Param("wchunumberofattributes") double ldhhfanin,
                   @Param("wchufanin") double ldhhnumberofpublicmethods,
                   @Param("wchunumberofprivatemethods") double ldhhnumberofprivateattributes,
                   @Param("wchunumberofmethods") double ldhhnumberofpublicattributes,
                   @Param("wchunumberofprivateattributes") double ldhhnumberofprivatemethods,
                   @Param("wchunoc") double ldhhnumberofattributesinherited,
                   @Param("wchuwmc") double ldhhnoc,
                   @Param("wchudit") double ldhhwmc,
                   @Param("wchunumberofattributesinherited") double ldhhnumberofattributes,
                   @Param("wchufanout") double ldhhnumberoflinesofcode,
                   @Param("wchulcom") double ldhhdit,
                   @Param("wchurfc") double ldhhfanout,
                   @Param("wchunumberofpublicmethods") double ldhhnumberofmethodsinherited,
                   @Param("wchucbo") double ldhhrfc,
                   @Param("wchunumberofmethodsinherited") double ldhhcbo,
                   @Param("wchunumberoflinesofcode") double ldhhnumberofmethods,
                   @Param("predictresult") String predictresult);
}
