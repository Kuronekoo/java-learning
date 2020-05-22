package web.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @description:
 * @author: shenchao
 * @create: 2020-05-19 10:41
 **/
public class HelloHttpServletWrapper extends HttpServletRequestWrapper {
    public HelloHttpServletWrapper(HttpServletRequest request) {
        super(request);
    }
}
