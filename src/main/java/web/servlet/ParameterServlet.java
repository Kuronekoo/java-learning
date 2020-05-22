package web.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-05-22 16:40
 **/
@WebServlet("/wrap")
public class ParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用了已覆盖的方法
        Map<String, String[]> parameterMap = req.getParameterMap();
        //用于输出的map
        Map<String,Object> map = new HashMap<>();
        //调用已覆盖的writer
        PrintWriter writer = resp.getWriter();
        map.put("code","200");
        map.put("message","success");
        //如果传的参数为空，data就直接返回
        if(parameterMap.isEmpty()){
            map.put("data","");
            //在覆盖的writer中写入
            writer.write(JSON.toJSONString(map));
            //flush将writer中的内容刷新到buffer中去
            writer.flush();
            return ;
        }
        parameterMap.forEach((k,v)->{
            System.out.println("req key : " + k + "req value : " + v);
        });
        //在data里放入请求参数对象
        map.put("data",parameterMap);
        //在覆盖的writer中写入
        writer.write(JSON.toJSONString(map));
        //flush将writer中的内容刷新到buffer中去
        writer.flush();
        return ;



    }
}
