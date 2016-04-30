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
}
