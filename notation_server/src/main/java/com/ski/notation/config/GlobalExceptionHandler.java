package com.ski.notation.config;

import javax.servlet.http.HttpServletRequest;

import com.ski.notation.config.vo.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 定义全局异常处理类
 * 
 * @author maoyl05
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 默认处理Exception异常，返回json结果
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ErrorInfo<String> defaultErrorHandler(HttpServletRequest req, Exception e) {
		ErrorInfo<String> r = new ErrorInfo<>();
		r.setMessage(e.getMessage());
		r.setCode(ErrorInfo.ERROR);
		r.setData("Some Data");
		r.setUrl(req.getRequestURL().toString());
		return r;
	}
}
