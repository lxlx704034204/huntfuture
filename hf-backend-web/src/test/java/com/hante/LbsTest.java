package com.hante;

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
        System.out.println(sysAccountService.queryListByExample(new SysAccountQuery()));
    }
}
