package com.songaw.generator.common.exception;

import com.google.common.base.Strings;
import com.songaw.generator.common.pojo.dto.Result;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 全局异常  其他异常
 */
@Controller
@Slf4j
public class GlobalExceptionController extends AbstractErrorController {
    private final ErrorProperties errorProperties;

    public GlobalExceptionController(){
        this(new DefaultErrorAttributes());
    }
    public GlobalExceptionController(ErrorAttributes errorAttributes) {
        this(errorAttributes, new ErrorProperties());
    }
    public GlobalExceptionController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping("${server.error.path:${error.path:/error}}")
    @ResponseBody
    public ResponseEntity error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        log.error("url:{},error:{},message:{}", body.get("path"), body.get("error"), body.get("message"));

        String msg="";
        if(body.get("message")!=null){
            msg=body.get("message").toString();
        }
        //token过期  ExpiredJwtException
        if (!Strings.isNullOrEmpty((String)body.get("exception")) && body.get("exception").equals(ExpiredJwtException.class.getName())){
            body.put("status", HttpStatus.FORBIDDEN.value());
            status = HttpStatus.FORBIDDEN;
            msg="登录已过期";
            return new ResponseEntity(Result.getCodeAuthorityErrResult(msg), status);
        }
        //AccessDeniedException 权限不足
        if (!Strings.isNullOrEmpty((String)body.get("exception")) && body.get("exception").equals(AccessDeniedException.class.getName())){
            body.put("status", HttpStatus.FORBIDDEN.value());
            status = HttpStatus.FORBIDDEN;
            msg="权限不足!";
            return new ResponseEntity(Result.getCodeAuthorityErrResult(msg), status);
        }

        if(status == HttpStatus.NOT_FOUND) {
            return new ResponseEntity(Result.getCodeNotFoundResult(msg), status);
        } else if(status == HttpStatus.FORBIDDEN) {
            if(msg.contains("Access Denied")){
                msg="拒绝访问";
            }
            return new ResponseEntity(Result.getCodeAuthorityErrResult(msg), status);
        } else {
            return new ResponseEntity(Result.getSystemErrorResult(msg), status);

         }


    }

    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    
}
