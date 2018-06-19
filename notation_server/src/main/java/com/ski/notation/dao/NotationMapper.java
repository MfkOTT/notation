package com.ski.notation.dao;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ski.zhou on 2018/5/29.
 */
public interface NotationMapper {
    @Results({ @Result(property = "notationId", column = "NOTATION_ID"),
            @Result(property = "title", column = "TITLE"),
            @Result(property = "subtitle", column = "SUBTITLE"),
            @Result(property = "bio", column = "BIO"),
            @Result(property = "notationUrl", column = "NOTATION_URL"),
            @Result(property = "bmgUrl", column = "BMG_URL"),
            @Result(property = "authorUrl", column = "AUTHOR_URL"),
            @Result(property = "accTitle", column = "ACC_TITLE"),
            @Result(property = "accUrl", column = "ACC_URL"),
            @Result(property = "type", column = "TYPE"),
            @Result(property = "resourceUrl", column = "RESOURCE_URL"),
            @Result(property = "createTime", column = "CREATETIME") })
    @Select({"<script>SELECT NOTATION_ID,TITLE,SUBTITLE,BIO,NOTATION_URL,BMG_URL,AUTHOR_URL,ACC_TITLE,ACC_URL,TYPE,RESOURCE_URL,CREATETIME FROM NOTATION ",
            "<if test='type != \"\"'>WHERE TYPE = #{type}, </if>",
            "</script>"})
    List<JSONObject> getNotationList(@Param("type") String type);
    @Results({ @Result(property = "notationId", column = "NOTATION_ID"),
            @Result(property = "title", column = "TITLE"),
            @Result(property = "subtitle", column = "SUBTITLE"),
            @Result(property = "bio", column = "BIO"),
            @Result(property = "notationUrl", column = "NOTATION_URL"),
            @Result(property = "bmgUrl", column = "BMG_URL"),
            @Result(property = "authorUrl", column = "AUTHOR_URL"),
            @Result(property = "accTitle", column = "ACC_TITLE"),
            @Result(property = "accUrl", column = "ACC_URL"),
            @Result(property = "type", column = "TYPE"),
            @Result(property = "resourceUrl", column = "RESOURCE_URL"),
            @Result(property = "createTime", column = "CREATETIME") })
    @Select({"SELECT NOTATION_ID,TITLE,SUBTITLE,BIO,NOTATION_URL,BMG_URL,AUTHOR_URL,ACC_TITLE,ACC_URL,TYPE,RESOURCE_URL,CREATETIME ",
            "FROM NOTATION WHERE LOCATE(#{title},TITLE) OR LOCATE(#{title},SUBTITLE)"
    })
    List<JSONObject> fuzzyByTitle(@Param("title")String title);

    @Insert({"INSERT INTO NOTATION (TITLE,SUBTITLE,BIO,NOTATION_URL,BMG_URL,AUTHOR_URL,ACC_TITLE,ACC_URL,TYPE,RESOURCE_URL,CREATETIME)" +
            "VALUES(#{title},#{subtitle},#{bio},#{notationUrl},#{bmgUrl},#{authorUrl},#{accTitle},#{accUrl},#{type}," +
            "#{resourceUrl},#{createTime})"})
    void addNotation(JSONObject reqJson);
}
