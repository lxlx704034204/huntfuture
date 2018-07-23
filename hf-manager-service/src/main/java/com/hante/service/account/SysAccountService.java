package com.hante.service.account;

import com.hante.common.domain.dto.PageData;
import com.hante.domain.model.SysAccount;
import com.hante.domain.query.SysAccountQuery;

public interface SysAccountService {
    /**
     * 后台页面-账号管理，查询账号列表
     * @param query 前台传入对象
     * @return 封装好的分页数据
     */
    PageData<SysAccount> queryListByExample(SysAccountQuery query);

}
