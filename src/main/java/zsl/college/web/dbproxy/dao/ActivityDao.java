package zsl.college.web.dbproxy.dao;

import org.springframework.stereotype.Repository;
import zsl.college.web.dbproxy.entity.Activity;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 * 活动dao层
 */
@Repository
public interface ActivityDao {

    List<Activity> getActivityList(Map<String, Object> map);

    Activity getActivityById(Long activityId);

    List<Activity> getActivityByKeyword(String  keyword);

    int create(Activity activity);
}
