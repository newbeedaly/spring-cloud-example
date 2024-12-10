package cn.newbeedaly.user.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author newbeedaly
 */
@Data
@Entity(name = "sc_u_user")
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 创建时间
     */
    private Date createTime;
}
