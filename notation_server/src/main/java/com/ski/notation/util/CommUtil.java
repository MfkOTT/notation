package com.ski.notation.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @version V1.0
 * @ClassName: CommUtil
 * @Description: 工具类
 */
public class CommUtil {
    private static Logger log = LoggerFactory.getLogger(CommUtil.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Random random = new Random();

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentDate() {
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间戳(秒)
     *
     * @return
     */
    public static String getCurrentTimestamp() {
        return new Date().getTime() / 1000 + "";
    }

    /**
     * 获取指定长度随机正整数
     *
     * @param length
     * @return
     */
    public static String getRandomStr(int length) {
        String num = String.valueOf(random.nextInt(9000) + 1000);
        int len = num.length();
        if (len < length) {
            num += getRandomStr(length - len);
        } else if (len > length) {
            num = num.substring(0, length);
        }
        return num;
    }

    /**
     * 生成指定长度的 随机编码
     *
     * @param length 指定的长度
     * @return
     */
    public static String generateId(int length) {
        String str = UUID.randomUUID().toString().replace("-", "");
        String s = str.substring(str.length() - length);
        return s;
    }


    /**
     * 获取指定格式的当前时间
     *
     * @param format 传进来的日期格式
     * @return
     */
    public static String getDate(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String currentTime = df.format(new Date());
        return currentTime;
    }

    /**
     * 格式化时间
     *
     * @param date
     * @return
     */
    public static String formatDate(String date) throws Exception {
        return sdf2.format(sdf.parse(date));
    }

    /**
     * 获取该月有多少天
     *
     * @param date
     * @return
     */
    public static int getMonthTotalDays(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(sdf.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int num = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return num;
    }

    /**
     * 由开始时间，结束时间，时间段，获得当天所有的分隔时间段
     *
     * @param timeStart
     * @param timeEnd
     * @param timeSlice
     * @return
     * @throws Exception
     */
    public static List<String> getTimeSlot(String timeStart, String timeEnd, String timeSlice) throws Exception {
        SimpleDateFormat sdfHM = new SimpleDateFormat("HH:mm");
        Calendar calendarHM = Calendar.getInstance();
        List<String> listHM = new ArrayList<>();//当天的时间分段
        String beginHM = timeStart;
        String endHM = "";
        for (int i = 0; i < 20; i++) {
            calendarHM.setTime(sdfHM.parse(beginHM));
            calendarHM.add(Calendar.MINUTE, Integer.parseInt(timeSlice));
            endHM = sdfHM.format(calendarHM.getTime());
            if (sdfHM.parse(endHM).after(sdfHM.parse(timeEnd))) {
                break;
            } else {
                listHM.add(beginHM + "-" + endHM);
                beginHM = endHM;
            }
        }
        return listHM;
    }

    /**
     * 获取当天后的一定天数的日期
     *
     * @param num
     * @return
     * @throws Exception
     */
    public static String getDateAfterDate(Integer num) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(today));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + num);
        return sdf.format(calendar.getTime());
    }


    /**
     * 获取当前时间后一定分钟后的时间
     *
     * @param minute
     * @return
     * @throws Exception
     */
    public static String getDateAfterMinute(int minute) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String today = sdf.format(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(today));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
        return sdf.format(calendar.getTime());
    }

    /**
     * 从big串中删除little
     * @param big
     * @param little
     * @return
     */
    public static String removeStringFromString(String big,String little){
        if(big.length()==1){
            big=big.replace(little,"");
        }else if((big.length()-1)  == big.indexOf(little)){
            big=big.replace("#" + little,"");
        }else{
            big = big.replace(little + "#","");
        }
        return big;
    }

    /**
     * 根据某个字段，给jsonArray排序
     * @param jsonArray
     * @return
     */
    public static JSONArray compareJsonArray(JSONArray jsonArray,String field){
        List<JSONObject> list = new ArrayList<>();
        JSONObject json = null;
        for (int i =0;i<jsonArray.size();i++){
            json = jsonArray.getJSONObject(i);
            list.add(json);
        }

        Collections.sort(list, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                String key1 = o1.getString(field);
                String key2 = o2.getString(field);
                return key1.compareTo(key2);
            }
        });

        jsonArray.clear();
        for(int i = 0;i<list.size();i++){
            jsonArray.add(list.get(i));
        }
        return jsonArray;
    }

}
