package cn.newbeedaly.gateway.adaptor.user;

import cn.newbeedaly.gateway.configuration.DefaultFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 用户基本信息表 前端控制器
 * </p>
 *
 * @author newbeedaly
 * @since 2023-06-26
 */
@Component
@FeignClient(name = "user", contextId = "UserFeignClient", configuration = DefaultFeignClientConfiguration.class)
public interface UserFeignClient {

    /**
     * 验证token是否有效
     *
     * @param token 用户token
     * @return token验证结果
     */
    @PostMapping("/user/validToken")
    Boolean validToken(@RequestParam(name = "token") String token);

}
