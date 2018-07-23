package com.hante.service.account.impl;

import com.hante.common.component.jpa.PageableUtil;
import com.hante.common.component.jpa.QueryExample;
import com.hante.common.domain.dto.PageData;
import com.hante.domain.model.SysAccount;
import com.hante.domain.query.SysAccountQuery;
import com.hante.repository.SysAccountRepository;
import com.hante.service.account.SysAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysAccountServiceImpl implements SysAccountService {
    @Autowired
    private SysAccountRepository sysAccountRepository;

    @Override
    public PageData<SysAccount> queryListByExample(SysAccountQuery query) {
        return new PageData<>(sysAccountRepository.findAll(new QueryExample<>(query), PageableUtil.getPageRequest(query)));
    }
}
