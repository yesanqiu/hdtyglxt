package com.example.hdtyglxt.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final Set<String> NOT_INTERCEPT_URI = new HashSet<>();//不拦截的URI

    static {
        NOT_INTERCEPT_URI.add("/login.html");
        NOT_INTERCEPT_URI.add("/register.html");
        NOT_INTERCEPT_URI.add("/swagger-ui.html");
        NOT_INTERCEPT_URI.add("/swagger-resources/configuration/ui");
        NOT_INTERCEPT_URI.add("/null/swagger-resources/configuration/ui");
        NOT_INTERCEPT_URI.add("/swagger-resources");
        NOT_INTERCEPT_URI.add("/user/login");
        NOT_INTERCEPT_URI.add("/user/register");
        NOT_INTERCEPT_URI.add("/favicon.ico");

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (NOT_INTERCEPT_URI.contains(uri)) {
            log.info("不拦截" + uri);
            return true;
        }
        String[] strs = uri.split("/");
        if("css".equals(strs[1]) || "js".equals(strs[1]) || "img".equals(strs[1]) || "webjars".equals(strs[1]) ||"swagger-resources" .equals(strs[1])){
            log.info("不拦截" + uri);
            return true;
        }
        log.info("拦截" + uri);
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/login.html");
            throw new RuntimeException("用户未登陆");
        }
        return true;
    }
}
