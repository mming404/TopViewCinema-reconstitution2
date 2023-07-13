package com.ysm.www.aop;


import com.ysm.www.entity.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;

/**
 * @author Albumen
 */
@ControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public CommonResult<Void> handlerIllegalArgumentException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException", ex);
        return CommonResult.operateFailWithMessage(ex.getMessage());
    }


    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public CommonResult<Void> handlerIllegalStateException(IllegalStateException ex) {
        log.error("IllegalStateException", ex);
        return CommonResult.operateFailWithMessage(ex.getMessage());
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    @ResponseBody
    public CommonResult<Void> handlerSqlSyntaxErrorException(SQLSyntaxErrorException ex) {
        log.error("SQLSyntaxErrorException", ex);
        return CommonResult.operateFailWithMessage(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResult<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException", ex);
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Optional<String> errors = fieldErrors.stream().map(FieldError::toString).reduce((a, b) -> a + " \n" + b);
        return CommonResult.operateFailWithMessage(errors.orElse("参数错误"));
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public CommonResult<Void> handleValidationException(ValidationException ex) {
        log.error("MethodArgumentNotValidException", ex);
        return CommonResult.operateFailWithMessage(ex.getCause().getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public CommonResult<Void> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException", ex);
        return CommonResult.operateFailWithMessage(ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public CommonResult<Void> handlerNoFoundException(Exception ex) {
        log.error("NoHandlerFoundException", ex);
        return CommonResult.operateFailWithMessage("路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public CommonResult<Void> handleDuplicateKeyException(DuplicateKeyException ex) {
        log.error("DuplicateKeyException", ex);
        return CommonResult.operateFailWithMessage("数据重复，请检查后提交");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult<Void> handleException(Exception ex) {
        log.error("Exception", ex);
        return CommonResult.operateFailWithMessage("系统繁忙，请稍后再试！错误信息：" + ex.getLocalizedMessage());
    }
}