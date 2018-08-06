package com.songaw.generator.common.exception;

import com.alibaba.fastjson.JSON;
import com.songaw.generator.common.pojo.dto.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常后返回JSON异常数据
 * 
 * @author wh
 *
 */
@Component
public class MappingExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception) {
		// JSON格式返回
		Result result;
		try {

			// 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
			if (exception instanceof ServiceException) {
				result=Result.getSystemErrorResult(exception.getLocalizedMessage());
			} else if (exception instanceof BadCredentialsException) {
				result=Result.getCodeLoginErrResult("用户名或密码错误，请重新输入！");
			}else{
				result=Result.getSystemErrorResult("操作失败,请重试!");

			}

		} catch (Exception e) {
			result=Result.getSystemErrorResult("操作失败,请重试!");
		}
		ModelAndView empty = new ModelAndView();
		PrintWriter pw = null;
		try {
			pw=response.getWriter();
			pw.write(JSON.toJSONString(result));
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			pw.close();
		}
		empty.clear();
		return empty;
	}

}