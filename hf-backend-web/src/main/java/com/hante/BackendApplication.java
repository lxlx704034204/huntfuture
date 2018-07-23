package com.hante;

import com.hante.common.contants.SysConfigContants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableJpaAuditing//开启JPA SQL增强
@EnableRedisHttpSession(redisNamespace = "backend", maxInactiveIntervalInSeconds = SysConfigContants.SESSION_EXPIRE)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BackendApplication {
    public static void main(String[] args) {
        //注意在配置文件里面查看启动端口
        SpringApplication.run(BackendApplication.class, args);
    }
}
