package cn.newbeedaly.user.client;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserClient {

    /**
     * 验证token是否有效
     *
     * @param token 用户token
     * @return token验证结果
     */
    @PostMapping("/user/validToken")
    Boolean validToken(@RequestParam(name = "token") String token);

}
