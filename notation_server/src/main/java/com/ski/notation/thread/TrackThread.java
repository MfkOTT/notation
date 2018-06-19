package com.ski.notation.thread;

import com.ski.notation.service.CommonService;
import com.ski.notation.util.SimpleDateUtil;

import java.util.Date;

/**
 * Created by ski.zhou on 04/06/2018.
 */
public class TrackThread implements Runnable{
    private CommonService commonService;
    private String remoteAddr;
    private String uuid;
    private String desc;
    public TrackThread(CommonService commonService, String remoteAddr, String uuid, String desc) {
        this.commonService = commonService;
        this.remoteAddr = remoteAddr;
        this.uuid = uuid;
        this.desc = desc;
    }

    @Override
    public void run() {
        String date = SimpleDateUtil.format(new Date(),"yyyyMMddHHmmss");
        commonService.saveTrackInfo(uuid,remoteAddr,desc,date);
    }
}
