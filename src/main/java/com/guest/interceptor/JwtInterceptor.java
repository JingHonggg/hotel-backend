package com.guest.interceptor;

import com.guest.utils.JwtUtill;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器、与 JWT配合完成鉴权
 *
 * @author chuanguo.cao
 * @since 2022-03-02
 */
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtill jwtUtill;

    /**
     * 进入到控制器方法之前执行的内容
     * @param request
     * @param response
     * @param handler
     * @return 如果返回false则被拦截，反正放行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            if("OPTIONS".equals(request.getMethod())){
                log.info(request.getMethod());
                return true;
            }


            String url = request.getRequestURL().toString();
            log.info(url);
            Claims claims = jwtUtill.getClaims(request);
            request.setAttribute("num",claims.get("jti"));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.info("未携带token或token过期,被拦截");
            return false;
        }
    }
}
