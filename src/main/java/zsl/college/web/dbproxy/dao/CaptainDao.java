package zsl.college.web.dbproxy.dao;

import org.springframework.stereotype.Repository;
import zsl.college.web.dbproxy.entity.Captain;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/5.
 */
@Repository
public interface CaptainDao {

    List<Captain> getListByMap(Map<String, Object> map);

    Captain getById(Long id);
}
