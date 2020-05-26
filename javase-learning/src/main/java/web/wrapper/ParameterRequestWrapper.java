package web.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-05-22 16:47
 **/
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    @Override
    public Map<String, String[]> getParameterMap() {
        //这个方法返回的是一个无法修改的map
        Map<String, String[]> parameterMap = super.getParameterMap();
        //生成一个新的map
        HashMap<String, String[]> newMap = new HashMap<>();
        //将parameterMap放入新的map里面
        newMap.putAll(parameterMap);
        //加入一个自定义的param
        newMap.put("newParam",new String[]{"new para"});
        return newMap;
    }

    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
    }
}
