package com.hante.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {

    @RequestMapping(value = "/save")
    @ResponseBody
    public String testSave(HttpServletRequest request) {
        request.getSession().setAttribute("test", "test");
        return "save success";
    }

    @RequestMapping(value = "/fetch")
    @ResponseBody
    public Object testFetch(HttpServletRequest request) {
        return request.getSession().getAttribute("test");
    }
}
