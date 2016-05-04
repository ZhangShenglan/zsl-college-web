package zsl.college.web.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zsl.college.web.common.Constants;
import zsl.college.web.dbproxy.entity.PageBean;
import zsl.college.web.dbproxy.entity.UserBean;
import zsl.college.web.service.UserService;
import zsl.college.web.util.DateUtil;
import zsl.college.web.util.ResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/4/30.
 * 用户控制层
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping("/list")
    public String list(@RequestParam(value = "currPage", required = false) Integer currPage,
                       @RequestParam(value = "pageSize", required = false) Integer pageSize,
                       HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        if (currPage!=null && currPage > 0 && pageSize != null && pageSize > 0) {
            PageBean pageBean = new PageBean(currPage,pageSize);
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        List<UserBean>  userBeans = userService.getUserList(map);
        JSONObject result = new JSONObject();
        if(userBeans.isEmpty()){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(userBeans);
        result.put("rows", jsonArray);
//        result.put("total", total);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 根据用户名获取用户信息
     * @param userName    用户名
     * @return
     */
    @RequestMapping("/get")
    public String getUserByUserName(@RequestParam(value = "userName", required = true) String userName,
                              HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(userName == null || "".equals(userName) ){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户名不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        UserBean userBean = userService.getUserByUsername(userName);
        if(userBean == null){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(userBean);
        result.put("rows", jsonArray);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 新建新用户
     * @return
     */
    @RequestMapping("/create")
    public String createUser(@RequestParam(value = "userName", required = true) String userName,
                             @RequestParam(value = "schoolName", required = true)  String schoolName,
                             @RequestParam(value = "phone",required = true) String phone,
                             @RequestParam(value = "introduce",required = true)String introduce,
                             @RequestParam(value = "password",required = true) String password,
                             @RequestParam(value = "gender",required = true) Integer gender,
                             HttpServletResponse response)throws Exception{
        JSONObject result = new JSONObject();
        if(userName == null || "".equals(userName)){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户名不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        if(schoolName == null || "".equals(schoolName)){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","学校不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        if(password == null || "".equals(password)){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","密码不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        if(userService.getUserByUsername(userName)!=null){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","用户名已经被占用");
            ResponseUtil.write(response, result);
            return null;
        }
        UserBean userBean = new UserBean();
        userBean.setUserName(userName);
        userBean.setSchoolId(1L);
        userBean.setPhone(phone);
        userBean.setIntroduce(introduce);
        userBean.setPassword(password);
        userBean.setGender(gender);
        userBean.setCreateTime(DateUtil.getCurrentDate());

        int flag = userService.createUser(userBean);
        if(flag < 1){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","添加用户失败");
            ResponseUtil.write(response, result);
            return null;
        }
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }

}
