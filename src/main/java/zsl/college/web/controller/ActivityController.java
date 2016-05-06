package zsl.college.web.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zsl.college.web.common.ActivityTypeEnum;
import zsl.college.web.common.Constants;
import zsl.college.web.dbproxy.entity.Activity;
import zsl.college.web.dbproxy.entity.Captain;
import zsl.college.web.dbproxy.entity.PageBean;
import zsl.college.web.dbproxy.entity.UserBean;
import zsl.college.web.service.ActivityService;
import zsl.college.web.service.CaptainService;
import zsl.college.web.util.DateUtil;
import zsl.college.web.util.ResponseUtil;
import zsl.college.web.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by zhangshenglan on 16/5/4.
 * 活动控制层
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @Resource
    private CaptainService captainService;

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
                       @RequestParam(value = "keyword",required = false) String keyword,
                       @RequestParam(value = "activityType",required = false) Integer activityType,
                       HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        if (currPage!=null && currPage > 0 && pageSize != null && pageSize > 0) {
            PageBean pageBean = new PageBean(currPage,pageSize);
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        if(activityType != null){
            map.put("activityType", activityType);
        }
        List<Activity> activities = new ArrayList<>();
        if(StringUtil.isNotEmpty(keyword)){
            activities  = activityService.getActivityByKeyword(keyword);
        }
        else {
            activities = activityService.getActivityList(map);
        }
        JSONObject result = new JSONObject();
        if(activities.isEmpty()){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        for(Activity activity : activities){
            String[] timeStr = activity.getBeginTime().split(" ");
            String time = timeStr[0].substring(5,timeStr[0].length());
            activity.setTime(time);
            Date beginDate = DateUtil.formatString(activity.getBeginTime(),"yyyy-MM-dd");
            Date endDate = DateUtil.formatString(activity.getEndTime(),"yyyy-MM-dd");
            if(beginDate != null && endDate != null){
                activity.setDays((endDate.getTime() - beginDate.getTime())/(24*60*60*1000));
            }
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

        if(activity.getActivityType() != null){
            activity.setType(ActivityTypeEnum.getByNum(activity.getActivityType()));
        }
        //领队信息
        if(activity.getCaptainId() != null){
            Captain captain = captainService.getById(activity.getCaptainId());
            if(captain != null){
                JSONArray captainJsonArray = JSONArray.fromObject(captain);
                result.put("captain", captainJsonArray);
            }
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
        List<Activity> activitys = activityService.getActivityByKeyword(keyword);
        if(activitys.isEmpty()){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","没有对应结果");
            ResponseUtil.write(response, result);
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(activitys);
        result.put("rows", jsonArray);
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/create")
    public String createActivity(@RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "beginTime", required = false) String beginTime,
                                 @RequestParam(value = "beginPlace", required = true) String beginPlace,
                                 @RequestParam(value = "endPlace", required = false) String endPlace,
                                 @RequestParam(value = "totalNum", required = false) Integer totalNum,
                                 @RequestParam(value = "captainId", required = false) Long captainId,
                                 @RequestParam(value = "images", required = false) String images,
                                 @RequestParam(value = "introduce", required = false) String introduce,
                                 HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        Activity activity = new Activity();
        activity.setTitle(StringUtil.isNotEmpty(title)? title:"");
        activity.setBeginTime(StringUtil.isNotEmpty(beginTime) ? beginTime : "");
        activity.setBeginPlace(StringUtil.isNotEmpty(beginPlace) ? beginPlace : "");
        activity.setEndPlace(StringUtil.isNotEmpty(endPlace) ? endPlace :"");
        activity.setTotalNum(totalNum != null ? totalNum : 0);
        activity.setCaptainId(captainId != null ? captainId : 0L);
        activity.setImages(StringUtil.isNotEmpty(images) ? images : "");
        activity.setIntroduce(StringUtil.isNotEmpty(introduce) ? introduce : "");
        activity.setCreateTime(DateUtil.getCurrentDate());

        int flag = activityService.create(activity);
        if(flag < 1){
            result.put("statusCode", Constants.RESULT_CODE_SERVER_ERROR);
            result.put("message","添加活动失败");
            ResponseUtil.write(response, result);
            return null;
        }
        result.put("statusCode", Constants.RESULT_CODE_SUCCESS);
        result.put("message","success");
        ResponseUtil.write(response, result);
        return null;
    }


}
