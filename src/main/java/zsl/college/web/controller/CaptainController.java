package zsl.college.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zsl.college.web.service.CaptainService;

import javax.annotation.Resource;

/**
 * Created by zhangshenglan on 16/5/5.
 */
@Controller
@RequestMapping("/captain")
public class CaptainController {

    @Resource
    private CaptainService captainService;
}
