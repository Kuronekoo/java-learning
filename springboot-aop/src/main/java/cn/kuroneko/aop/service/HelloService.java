package cn.kuroneko.aop.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-05-27 16:41
 **/
@Component
public class HelloService {
    public String hello(String name){
        if("Apple".equalsIgnoreCase(name)){
            throw new RuntimeException("it's an bad apple!");
        }
        System.out.println("hello : " + name);
        return "hello : " + name;
    }

    public void shout(String name,String place){
        System.out.println("shout : " + name + " in " + place);
    }
}
