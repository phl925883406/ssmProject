package com.imooc.myo2o.dao.gemapper;

import com.imooc.myo2o.entity.TUserRedPacket;
import com.imooc.myo2o.entity.TUserRedPacketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserRedPacketMapper {
    int countByExample(TUserRedPacketExample example);

    int deleteByExample(TUserRedPacketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUserRedPacket record);

    int insertSelective(TUserRedPacket record);

    List<TUserRedPacket> selectByExample(TUserRedPacketExample example);

    TUserRedPacket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserRedPacket record, @Param("example") TUserRedPacketExample example);

    int updateByExample(@Param("record") TUserRedPacket record, @Param("example") TUserRedPacketExample example);

    int updateByPrimaryKeySelective(TUserRedPacket record);

    int updateByPrimaryKey(TUserRedPacket record);
}