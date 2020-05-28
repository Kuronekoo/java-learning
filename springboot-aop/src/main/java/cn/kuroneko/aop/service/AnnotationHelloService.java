package cn.kuroneko.aop.service;

import cn.kuroneko.aop.annotation.HelloLogging;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-05-28 15:54
 **/
@Component
public class AnnotationHelloService {

    @HelloLogging("说你好～")
    public String aHello(String name){
        System.out.println("ahello : " + name);
        return "ahello : " + name;
    }
}
