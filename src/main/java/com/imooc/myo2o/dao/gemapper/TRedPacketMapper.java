package com.imooc.myo2o.dao.gemapper;

import com.imooc.myo2o.entity.TRedPacket;
import com.imooc.myo2o.entity.TRedPacketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRedPacketMapper {
    int countByExample(TRedPacketExample example);

    int deleteByExample(TRedPacketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRedPacket record);

    int insertSelective(TRedPacket record);

    List<TRedPacket> selectByExample(TRedPacketExample example);

    TRedPacket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRedPacket record, @Param("example") TRedPacketExample example);

    int updateByExample(@Param("record") TRedPacket record, @Param("example") TRedPacketExample example);

    int updateByPrimaryKeySelective(TRedPacket record);

    int updateByPrimaryKey(TRedPacket record);
}