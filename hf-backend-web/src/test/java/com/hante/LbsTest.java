package com.hante;

import com.hante.common.utils.uuid.UUIDUtil;
import com.hante.domain.model.SysAccount;
import com.hante.domain.query.SysAccountQuery;
import com.hante.service.account.SysAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = BackendApplication.class)
@RunWith(SpringRunner.class)
public class LbsTest {
    @Autowired
    private SysAccountService sysAccountService;

    @Test
    public void accountAutoQuery(){
        SysAccountQuery query = new SysAccountQuery();
        query.setLoginName("lbs");
        query.setRoleId(UUIDUtil.uuid());
        query.setPageNumber(1);
        query.setPageSize(10);
        System.out.println(sysAccountService.queryListByExample(query));
    }
}
