package zsl.college.web.service.impl;

import org.springframework.stereotype.Service;
import zsl.college.web.dbproxy.dao.CaptainDao;
import zsl.college.web.dbproxy.entity.Captain;
import zsl.college.web.service.CaptainService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshenglan on 16/5/5.
 */
@Service("captainService")
public class CaptainServiceImpl implements CaptainService {

    @Resource
    private CaptainDao captainDao;

    @Override
    public List<Captain> getListByMap(Map<String, Object> map){
        return captainDao.getListByMap(map);
    }

    @Override
    public Captain getById(Long id){
        return captainDao.getById(id);
    }


}
