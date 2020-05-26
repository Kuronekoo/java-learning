package web.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-05-19 10:41
 **/
public class HelloHttpRequestWrapper extends HttpServletRequestWrapper {
    public HelloHttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }


}
