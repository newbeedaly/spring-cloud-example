package cn.newbeedaly.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private StreamBridge streamBridge;

    @PostMapping("/sendKafkaMsg")
    public Boolean sendKafkaMsg(@RequestParam(value = "msg", defaultValue = "1") String msg) {
        return streamBridge.send("kafkaMsg-in-0", msg);
    }

}
