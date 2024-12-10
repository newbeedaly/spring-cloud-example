package cn.newbeedaly.user.controller;

import cn.newbeedaly.user.client.UserClient;
import cn.newbeedaly.user.dao.entity.User;
import cn.newbeedaly.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController implements UserClient {

    @Autowired
    private IUserService userService;

    @Override
    public Boolean validToken(String token) {
        User user = userService.getUserById(1L);
        return Objects.nonNull(user);
    }

}
