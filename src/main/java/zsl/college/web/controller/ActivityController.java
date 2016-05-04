package zsl.college.web.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zsl.college.web.common.Constants;
import zsl.college.web.dbproxy.entity.Activity;
import zsl.college.web.dbproxy.entity.PageBean;
import zsl.college.web.service.ActivityService;
import zsl.college.web.util.ResponseUtil;
import zsl.college.web.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 * 活动控制层
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Resource
    private ActivityService activityService;

    /**
     * 获取活动列表
     * @param currPage
     * @param pageSize
     * @param response
     * @return
     * @throws Exception
     */
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
        List<Activity> activities = activityService.getActivityList(map);
        JSONObject result = new JSONObject();
        if(activities.isEmpty()){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(activities);
        result.put("rows", jsonArray);
//        result.put("total", total);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 根据活动ID获取活动
     * @param activityId
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/getActivityById")
    public String getActivityById(@RequestParam(value = "activityId",required = true) Long activityId,
                                  HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(activityId == null || activityId < 1){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","活动id不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        Activity activity = activityService.getActivityById(activityId);
        if(activity == null){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(activity);
        result.put("rows", jsonArray);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 根据关键字获取活动
     * @param keyword       关键字
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/getActivityByKeyWord")
    public String getActivityByKeyWord(@RequestParam(value = "keyword",required = true) String keyword,
                                       HttpServletResponse response) throws Exception{
        JSONObject result = new JSONObject();
        if(StringUtil.isEmpty(keyword)){
            result.put("statusCode", Constants.RESULT_CODE_ILLEGAL_REQUST);
            result.put("message","关键字不能为空");
            ResponseUtil.write(response, result);
            return null;
        }
        Activity activity = activityService.getActivityByKeyword(keyword);
        if(activity == null){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(activity);
        result.put("rows", jsonArray);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }



}
