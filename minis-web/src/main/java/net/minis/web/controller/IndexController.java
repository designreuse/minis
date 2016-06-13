package net.minis.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
public class IndexController {

    @RequestMapping(value = { "/", "/index.html" }, method = GET)
    public String indexPage() {
        return "/index.jsp";
    }
    
}
