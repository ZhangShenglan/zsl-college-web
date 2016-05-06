package zsl.college.web.dbproxy.dao;

import org.springframework.stereotype.Repository;
import zsl.college.web.dbproxy.entity.ActivityApply;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/6.
 */
@Repository
public interface ActivityApplyDao{

    List<ActivityApply> getListByMap(Map<String, Object> map);

    int create(ActivityApply activityApply);
}
