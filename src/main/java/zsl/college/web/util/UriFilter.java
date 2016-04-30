package zsl.college.web.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhangshenglan on 16/4/30.
 */
public class UriFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("Service ShutDown");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 把ServletRequest和ServletResponse转换成真正的类型
        HttpServletRequest req = (HttpServletRequest) request;
        // 由于web.xml中设置Filter过滤全部请求，可以排除不需要过滤的url
        String requestURI = req.getRequestURI();
        // System.out.println(requestURI);
        if (requestURI.endsWith("admin")) {
            ((HttpServletResponse) response).sendRedirect("main.jsp");
            return;
        }
        if (requestURI.endsWith("wechat")) {
            ((HttpServletResponse) response).sendRedirect("http://www.qess.me/index/index.html");
            return;
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("UriFilter Init");
    }
}
