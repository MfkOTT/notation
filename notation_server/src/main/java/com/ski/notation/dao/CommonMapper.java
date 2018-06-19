package com.ski.notation.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ski.zhou on 04/06/2018.
 */
public interface CommonMapper {

    @Insert({"INSERT INTO TRACK (UUID,REMOTEADDR,DESCRIPTION,CREATETIME) VALUES (#{uuid},#{remoteAddr},#{desc},#{date})"})
    void saveTrackInfo(@Param("uuid")String uuid, @Param("remoteAddr")String remoteAddr, @Param("desc")String desc, @Param("date")String date);
}
