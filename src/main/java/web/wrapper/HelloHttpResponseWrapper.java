package web.wrapper;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-05-22 11:24
 **/
public class HelloHttpResponseWrapper  extends HttpServletResponseWrapper {
    public HelloHttpResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        PrintWriter writer = super.getWriter();
        writer.write("add some thing");
        return writer;
    }
}
