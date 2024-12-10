package cn.newbeedaly.user.dao;


import cn.newbeedaly.user.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author newbeedaly
 */
public interface UserDao extends JpaRepository<User, Long> {

}
