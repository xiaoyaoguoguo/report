package com.panda.framework.web.exception;


import com.panda.framework.domain.AjaxResult;
import com.panda.framework.domain.JsonReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jamie
 * @ClassName: DefaultExceptionHandler
 * @Description: 自定义异常处理器
 * @data 2018-08-29 16:22
 **/
@RestControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);


    /**
     * 请求方式不支持
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public JsonReturn handleException(HttpRequestMethodNotSupportedException e)
    {
        log.error(e.getMessage(), e);
        return new JsonReturn(JsonReturn.CODE_ERROR, "不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public JsonReturn notFount(RuntimeException e)
    {
        log.error("运行时异常:", e);
        return new JsonReturn(JsonReturn.CODE_ERROR, e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public JsonReturn handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return new JsonReturn(JsonReturn.CODE_ERROR, "服务器错误，请联系管理员");
    }



}
