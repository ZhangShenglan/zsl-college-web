package zsl.college.web.service;

import zsl.college.web.dbproxy.entity.ActivityApply;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/6.
 */
public interface ActivityApplyService {

    List<ActivityApply> getListByMap(Map<String, Object> map);

    int create(ActivityApply activityApply);
}
