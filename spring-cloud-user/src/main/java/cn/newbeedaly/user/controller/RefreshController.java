package cn.newbeedaly.user.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Getter
@RefreshScope
@RestController
public class RefreshController {

    @Value("${server-name}")
    private String serverName;


    @GetMapping("/getServerName")
    public String getName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        log.info("server name changed: " + serverName);
        this.serverName = serverName;
    }
}
