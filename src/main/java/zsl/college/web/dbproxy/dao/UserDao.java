package zsl.college.web.dbproxy.dao;

import org.springframework.stereotype.Repository;
import zsl.college.web.dbproxy.entity.UserBean;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/4/30.
 * 用户相关
 */
@Repository
public interface UserDao {
    /**
     * 获取用户列表
     * @param map       map
     * @return
     */
    List<UserBean> getUserList(Map<String, Object> map);

    /**
     * 根据id获取用户信息
     * @param userId
     * @return
     */
    UserBean getUserById(Long userId);

    /**
     * 根据用户名获取用户信息
     * @param userName      用户名
     * @return
     */
    UserBean getUserByUsername(String userName);

    /**
     * 创建新用户
     * @param userBean
     * @return
     */
    int createUser(UserBean userBean);

    }
