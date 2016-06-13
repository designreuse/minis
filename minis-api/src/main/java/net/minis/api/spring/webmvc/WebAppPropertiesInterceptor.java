package net.minis.api.spring.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minis.api.spring.security.AuthenticationUtils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * prepare setup web applications properties to every request.
 * 
 * @author yen.
 */
public class WebAppPropertiesInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        // Add Context Path to attribute.
        request.setAttribute("contextPath", request.getContextPath());

        // Add User Details to attribute.
        request.setAttribute("username", AuthenticationUtils.getUsername());
        
        WebAppProperties webapps = new WebAppProperties(request, response);
        request.setAttribute("webapps", webapps);
        return true;
    }

}
