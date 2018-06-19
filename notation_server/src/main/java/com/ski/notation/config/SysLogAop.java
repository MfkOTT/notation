package com.ski.notation.config;

import java.util.Arrays;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.ski.notation.config.annotation.WebLog;
import com.ski.notation.service.CommonService;
import com.ski.notation.service.NotationService;
import com.ski.notation.thread.TrackThread;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 系统日志切面处理
 *
 * @author maoyl05
 */
@Aspect
@Order(5)
@Component
public class SysLogAop {
    private static Logger logger = LoggerFactory.getLogger(SysLogAop.class);
    private String uuid;
    @Autowired
    private CommonService commonService;
    @Pointcut("execution(public * com.ski.notation.web..*.*(..))")
    public void webLog() {
    }

    @Before("webLog() && @annotation(weblog)")
    public void doBefore(JoinPoint joinPoint, WebLog weblog) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        uuid = UUID.randomUUID().toString();
        Object[] reqObj = joinPoint.getArgs();
        // 记录下请求内容
        logger.info("url :{} ", request.getRequestURL().toString());
        logger.info("http_method :{} ", request.getMethod());
        logger.info("ip :{} ", request.getRemoteAddr());
        logger.info("class_method :{} ", joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        logger.info("start-->UUID:[{}]functionDesc:[{}]staffNumber:[{}]param:[{}] ", uuid, weblog.desc(),
                null != reqObj && reqObj.length >= 1 ? reqObj[0].toString() : "",
                null != reqObj && reqObj.length >= 2 ? reqObj[1].toString() : "");
        TrackThread tt = new TrackThread(commonService,request.getRemoteAddr(),uuid,weblog.desc());
        tt.run();
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("end--->UUID:[{}]result:[{}]", uuid, ret);
    }
}
