package zsl.college.web.dbproxy.entity;

import java.io.Serializable;

/**
 * Created by zhangshenglan on 16/5/6.
 */
public class ActivityApply implements Serializable{
    private Long id;
    private Long activityId;
    private Long userId;
    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
