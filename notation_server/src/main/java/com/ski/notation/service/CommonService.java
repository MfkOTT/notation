package com.ski.notation.service;

import com.ski.notation.dao.CommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ski.zhou on 04/06/2018.
 */
@Service
public class CommonService {
    private static Logger logger = LoggerFactory.getLogger(CommonService.class);
    @Autowired
    private CommonMapper commonMapper;
    public void saveTrackInfo(String uuid, String remoteAddr, String desc, String date){
        try {
            commonMapper.saveTrackInfo(uuid,remoteAddr,desc,date);
        }catch (Exception e){
            logger.error("saveTrackInfo occur error!!!",e);
        }


    }
}
