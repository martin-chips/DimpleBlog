package com.dimple.dao;

import com.dimple.bean.OperatorLog;
import com.dimple.bean.OperatorLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorLogMapper {
    int countByExample(OperatorLogExample example);

    int deleteByExample(OperatorLogExample example);

    int deleteByPrimaryKey(Integer operid);

    int insert(OperatorLog record);

    int insertSelective(OperatorLog record);

    List<OperatorLog> selectByExample(OperatorLogExample example);

    OperatorLog selectByPrimaryKey(Integer operid);

    int updateByExampleSelective(@Param("record") OperatorLog record, @Param("example") OperatorLogExample example);

    int updateByExample(@Param("record") OperatorLog record, @Param("example") OperatorLogExample example);

    int updateByPrimaryKeySelective(OperatorLog record);

    int updateByPrimaryKey(OperatorLog record);
}