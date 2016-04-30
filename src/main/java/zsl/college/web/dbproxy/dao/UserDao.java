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
}
