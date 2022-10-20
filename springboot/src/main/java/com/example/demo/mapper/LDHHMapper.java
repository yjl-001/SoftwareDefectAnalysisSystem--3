package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.LDHH;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface LDHHMapper extends BaseMapper<LDHH> {
    @Insert({"insert into ldhh(datasetid,model,userid,ldhhlcom,ldhhfanin, ldhhnumberofpublicmethods," +
            "ldhhnumberofprivateattributes,ldhhnumberofpublicattributes,ldhhnumberofprivatemethods,ldhhnumberofattributesinherited," +
            "ldhhnoc,ldhhwmc,ldhhnumberofattributes,ldhhnumberoflinesofcode,ldhhdit,ldhhfanout,ldhhnumberofmethodsinherited," +
            "ldhhrfc,ldhhcbo,ldhhnumberofmethods,predictresult) " +
            "values(#{datasetid},#{model},#{userid},#{ldhhlcom},#{ldhhfanin},#{ldhhnumberofpublicmethods}," +
            "#{ldhhnumberofprivateattributes},#{ldhhnumberofpublicattributes},#{ldhhnumberofprivatemethods},#{ldhhnumberofattributesinherited}," +
            "#{ldhhnoc},#{ldhhwmc},#{ldhhnumberofattributes},#{ldhhnumberoflinesofcode},#{ldhhdit},#{ldhhfanout},#{ldhhnumberofmethodsinherited}," +
            "#{ldhhrfc},#{ldhhcbo},#{ldhhnumberofmethods},#{predictresult})"})
    void insertOne(@Param("datasetid") int datasetid,
                   @Param("model") String svm,
                   @Param("userid") int userid,
                   @Param("ldhhlcom") double ldhhlcom,
                   @Param("ldhhfanin") double ldhhfanin,
                   @Param("ldhhnumberofpublicmethods") double ldhhnumberofpublicmethods,
                   @Param("ldhhnumberofprivateattributes") double ldhhnumberofprivateattributes,
                   @Param("ldhhnumberofpublicattributes") double ldhhnumberofpublicattributes,
                   @Param("ldhhnumberofprivatemethods") double ldhhnumberofprivatemethods,
                   @Param("ldhhnumberofattributesinherited") double ldhhnumberofattributesinherited,
                   @Param("ldhhnoc") double ldhhnoc,
                   @Param("ldhhwmc") double ldhhwmc,
                   @Param("ldhhnumberofattributes") double ldhhnumberofattributes,
                   @Param("ldhhnumberoflinesofcode") double ldhhnumberoflinesofcode,
                   @Param("ldhhdit") double ldhhdit,
                   @Param("ldhhfanout") double ldhhfanout,
                   @Param("ldhhnumberofmethodsinherited") double ldhhnumberofmethodsinherited,
                   @Param("ldhhrfc") double ldhhrfc,
                   @Param("ldhhcbo") double ldhhcbo,
                   @Param("ldhhnumberofmethods") double ldhhnumberofmethods,
                   @Param("predictresult") String predictresult);
}
