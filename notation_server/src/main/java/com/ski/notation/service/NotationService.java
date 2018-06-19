package com.ski.notation.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ski.notation.config.vo.Constants;
import com.ski.notation.config.vo.ErrorInfo;
import com.ski.notation.dao.NotationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ski.zhou on 2018/5/29.
 */
@Service
public class NotationService {
    @Autowired
    private NotationMapper notationMapper;
    /**
     *
     * @param pageNum
     * @param pageSize
     * @param type
     * @return 列表查询
     */
    public ErrorInfo<JSONObject> getNotationList(int pageNum, int pageSize, String type) throws Exception{

        PageHelper.startPage(pageNum, pageSize);
        Page page = (Page)notationMapper.getNotationList(type);
        if (null == page){
            return new ErrorInfo<>(Constants.Code.FAIL,Constants.Msg.EMPTY_DATA);
        }

        long size = page.getTotal();//总条数
        long currentPage = page.getPageNum();//当前页
        JSONArray array = (JSONArray) JSON.toJSON(page);//分页后的数据


        JSONObject rspJson = new JSONObject();
        JSONObject dataJson = new JSONObject();
        dataJson.put("pageNum", currentPage);
        dataJson.put("total", size);
        dataJson.put("list", array);
        rspJson.put("info", dataJson);

        return new ErrorInfo<>(Constants.Code.OK,Constants.Msg.SUCCESS,rspJson);
    }

    /**
     * title 模糊查询
     * @param title
     * @param pageSize
     *@param pageNum @return
     */
    public ErrorInfo<JSONObject> fuzzyByTitle(String title, int pageSize, int pageNum) throws Exception{

        PageHelper.startPage(pageNum, pageSize);
        Page page = (Page)notationMapper.fuzzyByTitle(title);
        if (null == page){
            return new ErrorInfo<>(Constants.Code.FAIL,Constants.Msg.EMPTY_DATA);
        }
        long size = page.getTotal();//总条数
        long currentPage = page.getPageNum();//当前页
        JSONArray array = (JSONArray) JSON.toJSON(page);//分页后的数据
        JSONObject rspJson = new JSONObject();
        JSONObject dataJson = new JSONObject();
        dataJson.put("pageNum", currentPage);
        dataJson.put("total", size);
        dataJson.put("list", array);
        rspJson.put("info", dataJson);
        return new ErrorInfo<>(Constants.Code.OK,Constants.Msg.SUCCESS,rspJson);
    }

    /**
     * 添加资源
     * @param reqJson
     * @return
     */
    public ErrorInfo<JSONObject> addNotation(JSONObject reqJson) {
        notationMapper.addNotation(reqJson);
        return new ErrorInfo<>(Constants.Code.OK,Constants.Msg.SUCCESS);
    }
}
