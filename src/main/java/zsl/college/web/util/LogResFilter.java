package zsl.college.web.util;

import org.apache.log4j.MDC;
import zsl.college.web.dbproxy.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zhangshenglan on 16/4/30.
 */
public class LogResFilter implements Filter {
    private final static double DEFAULT_USERID = 0.0;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // System.out.println("进入过滤器");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        if (session == null) {
            MDC.put("userName", DEFAULT_USERID);
            MDC.put("userRole", DEFAULT_USERID);
        } else if(session.getAttribute("currentUser")==null){
            MDC.put("userName", DEFAULT_USERID);
            MDC.put("userRole", DEFAULT_USERID);
        }
        else{
            // StuInfor stuInfor =(StuInfor)session.getAttribute("admin");
            // 用户的id
            User user = (User) session.getAttribute("currentUser");
            // 用户的类型
            String userName = user.getUserName();
            String userRole = user.getRoleName();
            if (userName == null && userRole == null) {
                MDC.put("userName", DEFAULT_USERID);
                MDC.put("userRole", DEFAULT_USERID);
            } else {
                MDC.put("userName", userName);
                MDC.put("userRole", userRole);
            }
        }
        MDC.put("ip", getIpAddr(req));
        chain.doFilter(request, response);
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("PRoxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
