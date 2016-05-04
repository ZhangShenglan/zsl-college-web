package zsl.college.web.service;

import zsl.college.web.dbproxy.entity.UserBean;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/4/30.
 */
public interface UserService {
    /**
     * 获取用户列表
     * @param map       map
     * @return
     */
    List<UserBean> getUserList(Map<String, Object> map);

    /**
     * 根据id获取用户信息
     * @param userId    用户ID
     * @return
     */
    UserBean getUserById(Long userId);

    /**
     * 根据username获取用户信息
     * @param userName
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
