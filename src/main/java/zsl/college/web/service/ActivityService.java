package zsl.college.web.service;

import zsl.college.web.dbproxy.entity.Activity;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 * 活动service层
 */
public interface ActivityService {
    /**
     * 获取活动列表
     * @param map
     * @return
     */
    List<Activity> getActivityList(Map<String, Object> map);

    /**
     * 根据活动id获取活动
     * @param activityId
     * @return
     */
    Activity getActivityById(Long activityId);

    /**
     * 根据关键字获取活动
     * @param keyword
     * @return
     */
    Activity getActivityByKeyword(String  keyword);

    /**
     * 创建新活动
     * @param activity
     * @return
     */
    int create(Activity activity);
}
