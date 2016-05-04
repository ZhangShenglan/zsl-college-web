package zsl.college.web.service.impl;

import org.springframework.stereotype.Service;
import zsl.college.web.dbproxy.dao.UserDao;
import zsl.college.web.dbproxy.entity.UserBean;
import zsl.college.web.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/4/30.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public List<UserBean> getUserList(Map<String, Object> map){
        return userDao.getUserList(map);
    }

    @Override
    public UserBean getUserById(Long userId){
        return userDao.getUserById(userId);
    }

    @Override
    public UserBean getUserByUsername(String userName){
        return userDao.getUserByUsername(userName);
    }

    @Override
    public int createUser(UserBean userBean){
        return userDao.createUser(userBean);
    }
}
