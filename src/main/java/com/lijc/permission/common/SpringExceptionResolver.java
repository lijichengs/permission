package com.lijc.permission.common;

import com.lijc.permission.exception.ParamException;
import com.lijc.permission.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Spring全局异常处理
 *
 * @author lijc
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {
    private static final String JSON_REQ = ".json";

    private static final String PAGE_REQ = ".page";

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System error";

        /*
            json请求已.json结尾
            页面请求都已.page结尾
         */
        if (url.endsWith(JSON_REQ)) {
            if (ex instanceof PermissionException || ex instanceof ParamException) {
                JsonData result = JsonData.fail(ex.getMessage());
                mv = new ModelAndView("jsonView", result.toMap());
            } else {
                log.info("unknown json exception, url: {}", url, ex);
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView", result.toMap());
            }
        } else if (url.endsWith(PAGE_REQ)) {
            log.info("unknown page exception, url: {}", url, ex);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception", result.toMap());
        } else {
            log.info("unknown exception, url: {}", url, ex);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView", result.toMap());
        }
        return mv;
    }
}
