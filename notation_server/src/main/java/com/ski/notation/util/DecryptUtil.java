package com.ski.notation.util;

import com.ski.notation.util.OkHttp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;

/**
 * Created by itw_hongww on 2018/1/16.
 */
public class DecryptUtil {
    private static Logger logger = LoggerFactory.getLogger(DecryptUtil.class);
    public static String decrypt(String securityUrl,String resStr){
        String target = "nil";
        logger.debug("收到的加密信息:{}",resStr);

        if(StringUtils.isEmpty(resStr)){
            return "收到的数据为空";
        }
        try{
            String aesKey = resStr.split("##")[0];
            String dataStr = resStr.split("##")[1];
            logger.debug("收到的加密信息:{}",dataStr);
            logger.debug("收到的AES key:{}",aesKey);
            String response = OkHttp.getInstance().doPost(securityUrl,aesKey);
            logger.debug("收到的response:{}",response);
            String decodeStr = response;
//            String decodeStr = "79E25B80FC95BC996123C429D7E71FFC";
            logger.debug("解密后的AES key:{}",decodeStr);
//            target = com.taikanglife.epm.util.AES.decrypt(dataStr, decodeStr);
            target = URLDecoder.decode(target,"UTF-8");
            logger.debug("解密后的信息:{}",target);
        }catch (Exception e){
            logger.error("init error!!",e);
        }
        return target;
    }
}
