package zsl.college.web.service;

import zsl.college.web.dbproxy.entity.Captain;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/5.
 */
public interface CaptainService {

    List<Captain> getListByMap(Map<String, Object> map);

    Captain getById(Long id);
}
