package zsl.college.web.service.impl;

import org.springframework.stereotype.Service;
import zsl.college.web.dbproxy.dao.ActivityDao;
import zsl.college.web.dbproxy.entity.Activity;
import zsl.college.web.service.ActivityService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/4.
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {



    @Resource
    private ActivityDao activityDao;

    @Override
    public List<Activity> getActivityList(Map<String, Object> map){
        return activityDao.getActivityList(map);
    }


    @Override
    public Activity getActivityById(Long activityId){
        return activityDao.getActivityById(activityId);
    }

    @Override
    public List<Activity> getActivityByKeyword(String  keyword){
        return activityDao.getActivityByKeyword(keyword);
    }

    @Override
    public int create(Activity activity){
        return activityDao.create(activity);
    }

}
