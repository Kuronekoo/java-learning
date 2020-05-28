package cn.kuroneko.aop;

import cn.kuroneko.aop.service.AnnotationHelloService;
import cn.kuroneko.aop.service.HelloService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-05-27 16:35
 **/
//@EnableAspectJAutoProxy
@SpringBootApplication
public class AopApp {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AopApp.class);
        ConfigurableApplicationContext ctx = springApplication.run(args);
        HelloService hello = ctx.getBean(HelloService.class);
        hello.hello("Alice");
//        hello.hello("apple");
        hello.shout("Baris","house");

        AnnotationHelloService aHello = ctx.getBean(AnnotationHelloService.class);
        aHello.aHello("Catalina");
    }


}
