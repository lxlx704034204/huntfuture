package com.hante.controller.account;

import com.hante.domain.query.SysAccountQuery;
import com.hante.service.account.SysAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "account")
public class AccountController {
    @Autowired
    private SysAccountService sysAccountService;

    @RequestMapping(value = "/queryList")
    public Object queryList(SysAccountQuery query){
        return sysAccountService.queryListByExample(query);
    }

}
