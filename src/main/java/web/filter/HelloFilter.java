package web.filter;

import web.wrapper.HelloHttpResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: shenchao
 * @create: 2020-05-18 12:43
 **/

@WebFilter(urlPatterns = "/hello")
public class HelloFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //如果需要提前获取 request 中的参数进行逻辑判断，就需要在 doFilter 之前进行判断和处理
        //如果需要对 request 或者 response 进行包裹（使用wrapper类），也需要 doFilter 进行包裹
        System.out.println("before do filter");
        HelloHttpResponseWrapper helloHttpResponseWrapper = new HelloHttpResponseWrapper(res);
        super.doFilter(req, helloHttpResponseWrapper, chain);
        System.out.println("after do filter");
        //如果需要获取 response 中的参数进行逻辑判断，就需要在 doFilter 之后进行判断和处理
    }
}
