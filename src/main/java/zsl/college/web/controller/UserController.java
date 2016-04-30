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
}
