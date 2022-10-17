package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface DataMapper extends BaseMapper<Data> {

    @Insert({"insert into data(datasetid,model,userid,number_of_non_trivial_bugs_found_until,cvs_w_entropy, cvs_entropy," +
            "number_of_critical_bugs_found_until,cvs_log_entropy,number_of_high_priority_bugs_found_until,number_of_major_bugs_found_until," +
            "cvs_lin_entropy,number_of_bugs_found_until,cvs_exp_entropy,predictresult) " +
            "values(#{datasetid},#{model},#{userid},#{numberOfNonTrivialBugsFoundUntil},#{cvsWEntropy},#{cvsEntropy}," +
            "#{numberOfCriticalBugsFoundUntil},#{cvsLogEntropy},#{numberOfHighPriorityBugsFoundUntil},#{numberOfMajorBugsFoundUntil}," +
            "#{cvsLinEntropy},#{numberOfBugsFoundUntil},#{cvsExpEntropy},#{predictresult})"})
    void insertOne(@Param("datasetid") int datasetid,
                   @Param("model") String model,
                   @Param("userid") int userid,
                   @Param("numberOfNonTrivialBugsFoundUntil") double numberOfNonTrivialBugsFoundUntil,
                   @Param("cvsWEntropy") double cvsWEntropy,
                   @Param("cvsEntropy") double cvsEntropy,
                   @Param("numberOfCriticalBugsFoundUntil") double numberOfCriticalBugsFoundUntil,
                   @Param("cvsLogEntropy") double cvsLogEntropy,
                   @Param("numberOfHighPriorityBugsFoundUntil") double numberOfHighPriorityBugsFoundUntil,
                   @Param("numberOfMajorBugsFoundUntil") double numberOfMajorBugsFoundUntil,
                   @Param("cvsLinEntropy") double cvsLinEntropy,
                   @Param("numberOfBugsFoundUntil") double numberOfBugsFoundUntil,
                   @Param("cvsExpEntropy") double cvsExpEntropy,
                   @Param("predictresult") String predictresult);
}
