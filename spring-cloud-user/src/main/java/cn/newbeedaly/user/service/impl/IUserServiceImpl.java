package cn.newbeedaly.user.service.impl;

import cn.newbeedaly.user.dao.UserDao;
import cn.newbeedaly.user.dao.entity.User;
import cn.newbeedaly.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IUserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).orElse(null);
    }

}
