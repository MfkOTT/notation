package com.ski.notation.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ski.notation.config.annotation.WebLog;
import com.ski.notation.config.vo.Constants;
import com.ski.notation.config.vo.ErrorInfo;
import com.ski.notation.service.NotationService;
import com.ski.notation.util.SimpleDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by ski.zhou on 2018/5/24.
 */
@RestController
@RequestMapping("/page")
public class NotationController {
    private static Logger logger = LoggerFactory.getLogger(NotationController.class);
    @Autowired
    private NotationService notationService;
    /**
     * 查询notation列表
     *
     * @param reqJson
     * @return
     */
    @WebLog(desc = "首页展示列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ErrorInfo<JSONObject> getNotationList(@RequestBody JSONObject reqJson) throws Exception {
        Assert.notNull(reqJson, Constants.Msg.NULL_PARAM);
//        JSONObject reqJson = JSONObject.parseObject(param);

        try {
            int pageSize = StringUtils.isEmpty(reqJson.getString("pageSize"))
                    ? Constants.Notation.DEFAULT_PAGESIZE : Integer.parseInt(reqJson.getString("pageSize"));
            int pageNum = StringUtils.isEmpty(reqJson.getString("pageNum"))
                    ? Constants.Notation.DEFAULT_PAGENUM : Integer.parseInt(reqJson.getString("pageNum"));
            String type = reqJson.getString("type");
            ErrorInfo<JSONObject> rspInfo = notationService.getNotationList(pageNum, pageSize,type);
            return rspInfo;
        } catch (Exception e) {
            logger.error("==getNotationList occur error!!==param:{}", reqJson, e);
            throw new Exception();
        }
    }
    /**
     * 模糊搜索
     *
     * @param reqJson
     * @return
     */
    @WebLog(desc = "模糊查询")
    @RequestMapping(value = "/fuzzy", method = RequestMethod.POST)
    @ResponseBody
    public ErrorInfo<JSONObject> fuzzyByTitle(@RequestBody JSONObject reqJson) throws Exception {
        Assert.notNull(reqJson, Constants.Msg.NULL_PARAM);
//        JSONObject reqJson = JSONObject.parseObject(param);
        try {
            int pageSize = StringUtils.isEmpty(reqJson.getString("pageSize"))
                    ? Constants.Notation.DEFAULT_PAGESIZE : Integer.parseInt(reqJson.getString("pageSize"));
            int pageNum = StringUtils.isEmpty(reqJson.getString("pageNum"))
                    ? Constants.Notation.DEFAULT_PAGENUM : Integer.parseInt(reqJson.getString("pageNum"));
            String title = reqJson.getString("title");
            ErrorInfo<JSONObject> rspInfo = notationService.fuzzyByTitle(title,pageSize,pageNum);
            return rspInfo;
        } catch (Exception e) {
            logger.error("==fuzzyByTitle occur error!!==param:{}", reqJson, e);
            throw new Exception();
        }
    }
    /**
     * 模糊搜索
     *
     * @param reqJson
     * @return
     */
    @WebLog(desc = "添加数据")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ErrorInfo<JSONObject> addNotation(@RequestBody JSONObject reqJson) throws Exception {
        Assert.notNull(reqJson, Constants.Msg.NULL_PARAM);
//        JSONObject reqJson = JSONObject.parseObject(param);
        try {
            String date = SimpleDateUtil.format(new Date(),"yyyy/MM/dd");
            reqJson.put("createTime",date);
            String title = reqJson.getString("title");
            ErrorInfo<JSONObject> rspInfo= notationService.addNotation(reqJson);
            return rspInfo;
        } catch (Exception e) {
            logger.error("==addNotation occur error!!==param:{}", reqJson, e);
            throw new Exception();
        }
    }
}
