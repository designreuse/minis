package net.minis.api.spring.webmvc;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.ToString;

/**
 * The web application properties context.
 * 
 * @author yen.
 */
@ToString
public class WebAppProperties extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public WebAppProperties(HttpServletRequest request, HttpServletResponse response) {
        this.put("contextPath", request.getContextPath());
    }

}
