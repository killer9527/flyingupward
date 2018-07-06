package com.flyingupword.membership.manage.config;

import com.flyingupword.common.model.vo.BaseResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by killer9527 on 2018/5/14.
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponseVO unknownException(Exception e) {
        this.logger.error(String.format("捕获全局异常 , message: %s", e.getMessage()), e);
        BaseResponseVO result = new BaseResponseVO();
        result.setMsg("服务器错误");
        result.setStatus(500);
        return result;
    }
}
