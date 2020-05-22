package web.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-05-22 16:47
 **/
public class ParameterResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer = null;
    private ServletOutputStream out = null;
    private PrintWriter writer = null;
    //构造器
    public ParameterResponseWrapper(HttpServletResponse response) throws IOException {
        //调用了父类的方法
        super(response);
        //实际存储数据的buffer，ByteArrayOutputStream方便转换成String
        buffer = new ByteArrayOutputStream();
        //自定义一个ServletOutputStream的子类，实现在下方，是一个内部类，包裹 buffer
        out = new WrappedOutputStream(buffer);
        //自定义一个PrintWriter包裹 buffer
        writer = new PrintWriter(new OutputStreamWriter(buffer, "UTF-8"));
    }

    //servlet调用这个方法时获取到的时包裹了buffer的ServletOutputStream，如果写入就是往buffer中写入
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }
    //servlet调用这个方法时获取到的时包裹了buffer的PrintWriter，如果写入就是往buffer中写入
    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    //强制刷新缓存区
    //servlet会将写入输出流的内容先放在缓存区，等到了一定的字节后再做输出
    //因此需要手动进行强制刷新，让输出力立马进行输出
    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }
    @Override
    public void reset() {
        buffer.reset();
    }

    //获取response的内容
    public String getResponseData(String charset) throws IOException {
        //刷新缓存区，将内容写入buffer
        flushBuffer();
        //创建一个byte数组
        byte[] bytes = buffer.toByteArray();
        try {
            //通过byte数组创建一个String
            String data = new String(bytes, charset);
            return data;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    //自定义的ServletOutputStream子类
    class WrappedOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos = null;
        //构造器
        public WrappedOutputStream(ByteArrayOutputStream stream) throws IOException {
            //指向同一个堆中的ByteArrayOutputStream
            bos = stream;
        }

        @Override
        public void write(int b) throws IOException {
            //往buffer中写入
            bos.write(b);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener listener) {

        }
    }
}
