package zsl.college.web.service.impl;

import org.springframework.stereotype.Service;
import zsl.college.web.dbproxy.dao.ActivityApplyDao;
import zsl.college.web.dbproxy.entity.ActivityApply;
import zsl.college.web.service.ActivityApplyService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/6.
 */
@Service("activityApplyService")
public class ActivityApplyServiceImpl implements ActivityApplyService {

    @Resource
    private ActivityApplyDao activityApplyDao;

    @Override
    public List<ActivityApply> getListByMap(Map<String, Object> map){
        return activityApplyDao.getListByMap(map);
    }

    @Override
    public int create(ActivityApply activityApply){
       return activityApplyDao.create(activityApply);
    }
}
