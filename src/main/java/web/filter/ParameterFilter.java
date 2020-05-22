package web.filter;

import web.wrapper.ParameterRequestWrapper;
import web.wrapper.ParameterResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-05-22 16:49
 **/
@WebFilter("/wrap")
public class ParameterFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //将request进行包装
        ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(req);
        //将response进行包装
        ParameterResponseWrapper responseWrapper = new ParameterResponseWrapper(res);
        //执行后续的servlet和filter
        super.doFilter(requestWrapper, responseWrapper, chain);
        //servlet执行完毕，response中相关内容已写入
        //获取buffer中存的字符串
        String responseData = responseWrapper.getResponseData("UTF-8");
        System.out.println(responseData);
        //获取HttpServletResponse原生的writer
        PrintWriter writer = res.getWriter();
        //写入buffer中存的字符串
        writer.write(responseData);
        //刷新到浏览器
        writer.flush();
    }
}
