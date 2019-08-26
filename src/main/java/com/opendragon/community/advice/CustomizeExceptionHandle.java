package com.opendragon.community.advice;

import com.alibaba.fastjson.JSON;
import com.opendragon.community.dto.ResultDTO;
import com.opendragon.community.exception.CustomizeErrorCode;
import com.opendragon.community.exception.CustomizeException;
import com.opendragon.community.exception.ICustomizeErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/26
 */
@ControllerAdvice
public class CustomizeExceptionHandle extends ResponseEntityExceptionHandler implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExceptionHandler(Exception.class)
    Object handleControllerException(HttpServletRequest request, HttpServletResponse response, Throwable ex, Model model) {
        if(request.getContentType().equals("application/json")){
            ResultDTO resultDTO = null;
            if(ex instanceof CustomizeException){
                resultDTO = ResultDTO.errorOf((ICustomizeErrorCode)ex);
            }else{
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERR);
            }
            response.setContentType("application/json;charset=utf-8");
            try {
                PrintWriter writer = response.getWriter();
                writer.print(JSON.toJSONString(resultDTO));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            String message = null;
            if(ex instanceof CustomizeException){
                message = ex.getMessage();
            }else{
                message = "无服务热炸了，要不稍后再试试！";
            }
            model.addAttribute("message", message);
            return new ModelAndView("error");
        }

        return null;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
