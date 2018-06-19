package com.ski.notation.config.vo;

public class Constants {
    public static class Code{
        public static final Integer OK = 0;//通信成功
        public static final Integer ERROR = -100;//通信失败
		public static final Integer FAIL = 99;//业务失败
		public static final Integer TIME_OUT = 98;//业务失败
    }

    public static class Msg{
        public static final String FAIL = "网络超时...";
        public static final String SUCCESS = "成功";
        public static final String EMPTY_DATA = "未查询到数据";

		public static final String NULL_PARAM = "参数不能为空";

    }
    public static class Notation{

    	public static final int DEFAULT_PAGESIZE = 6;//默认每页数量
    	public static final int DEFAULT_PAGENUM = 1;//首页
    }
}
