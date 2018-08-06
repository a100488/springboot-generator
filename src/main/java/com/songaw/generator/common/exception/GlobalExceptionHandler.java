package com.songaw.generator.common.exception;

import com.songaw.generator.common.pojo.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.ognl.MethodFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局捕获
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {







    @ResponseBody
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<Result> AuthenticationExceptionHandler(AuthenticationException e) {
        return new ResponseEntity<Result>( Result.getCodeLoginErrResult(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Result> ExceptionHandler(Exception e) throws Exception {
        logger.error(e.getMessage(), e);
     return new ResponseEntity<Result>( Result.getSystemErrorResult(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ResponseBody
    @ExceptionHandler(value = MethodFailedException.class)
    public ResponseEntity<Result> methodFailedException(MethodFailedException e) throws Exception {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<Result>( Result.getSystemErrorResult(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
