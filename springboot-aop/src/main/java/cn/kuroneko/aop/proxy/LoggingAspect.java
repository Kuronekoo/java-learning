package cn.kuroneko.aop.proxy;

import cn.kuroneko.aop.annotation.HelloLogging;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @description:
 * 将一个类声明为一个切面：
 *  1。放入IOC容器尽心管理，切面是Ioc中的一个bean
 *  2。声明为一个切面
 * @Order： 指定切面优先级，值越小优先级越高
 * @author: kuroneko
 * @create: 2020-05-27 16:48
 **/
@Order(1)
@Aspect
@Component
public class LoggingAspect {
    /**
     * 该方法是一个前置方法,目标方法执行之前开始执行
     * 通过JoinPoint来访问连接点的细节
     *
     * * cn.kuroneko.aop.service.HelloService.*(..)
     * 所有修饰符，所有返回值，HelloService类中的所有方法，不限制入参
     *
     *
     */
    @Before("execution(* cn.kuroneko.aop.service.HelloService.*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("methrod "+ name+" begins with args " + Arrays.asList(args));
    }


    /**
     * 后置通知：在目标方法执行后执行，无论是否发生异常
     * 无法访问方法的返回结果
     * @param joinPoint
     */
    @After("execution(public * cn.kuroneko.aop.service.HelloService.*(..))")

    public void afterMethod(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println("methrod "+ name+" ends");

    }

    /**
     * 返回通知：方法正常结束后执行的代码，方法发生异常不执行，可以访问方法的返回值
     * @param joinPoint
     */
    @AfterReturning(value = "execution(public * cn.kuroneko.aop.service.HelloService.*(..))",
    returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        System.out.println("result ====>" + result);
    }

    /**
     * 目标方法出现异常后执行的代码，方法中声明的异常必须和代码抛出的异常一致才会执行，即可以捕获方法抛出的异常
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "execution(public * cn.kuroneko.aop.service.HelloService.*(..))",
            throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
        System.out.println("error occurs  : " + ex.getMessage());
    }

    /**
     * 环绕通知相当于动态代理的全过程
     * 必须携带ProceedingJoinPoint参数，ProceedingJoinPoint可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值为目标方法的返回值
     * @param joinPoint
     */
    @Around(value = "execution(public * cn.kuroneko.aop.service.HelloService.*(..))")
    public Object AroundMethod(ProceedingJoinPoint joinPoint){
        Object result = null;

        //判断是否为方法代理的切入点
        if(joinPoint instanceof MethodInvocationProceedingJoinPoint){
            //类型强转
            MethodInvocationProceedingJoinPoint mJoinPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
            //获取签名
            //MethodInvocationProceedingJoinPoint的Signature本来就是MethodSignatureImpl
            MethodSignature methodSignature = (MethodSignature) mJoinPoint.getSignature();
            //获取代理的方法
            Method method = methodSignature.getMethod();
           //然后可以获取注解等
        }

        //执行目标方法
        try {
            //前置通知
            System.out.println("arounding before...");
            //继续执行参数
            result = joinPoint.proceed();
            //用的新的args来执行目标方法
//            joinPoint.proceed(args);
            //返回通知
            System.out.println("arounding AfterReturning...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            //异常通知
            System.out.println("arounding AfterThrowing...");
        }
        //后置通知
        System.out.println("arounding After...");


        return result;
    }

    /**
     * 定义一个方法，用于声明切入点的表达式，一般的，该方法不需要再添加其他内容
     * 其他通知只用方法名来饮用切入点的表达式
     * 可以跨类，跨包使用调用，加上包名即可
     * 例如 @Before（"cn.kuroneko.aop.proxy.LoggingAspect#delarePointcutMethod()"）
     *
     */
    @Pointcut("execution(public * cn.kuroneko.aop.service.HelloService.*(..))")
    public void delarePointcutMethod(){};

    /**
     * 使用声明了切入点的方法
     * @param joinPoint
     */
    @Before("delarePointcutMethod()")
    public void beforeMethodPointcut(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("---------Pointcut methrod "+ name+" begins with args " + Arrays.asList(args));
    }

    @Pointcut("@annotation(cn.kuroneko.aop.annotation.HelloLogging)")
    public void declareHelloLoggingPointcut(){};

    /**
     * 环绕通知相当于动态代理的全过程
     * 必须携带ProceedingJoinPoint参数，ProceedingJoinPoint可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值为目标方法的返回值
     * @param joinPoint
     */
    @Around(value = "declareHelloLoggingPointcut())")
    public Object aHelloAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;

        //判断是否为方法代理的切入点
        if(joinPoint instanceof MethodInvocationProceedingJoinPoint){
            //类型强转
            MethodInvocationProceedingJoinPoint mJoinPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
            //获取签名
            //MethodInvocationProceedingJoinPoint的Signature本来就是MethodSignatureImpl
            MethodSignature methodSignature = (MethodSignature) mJoinPoint.getSignature();
            //获取代理的方法
            Method method = methodSignature.getMethod();
            //然后可以获取注解等
            HelloLogging helloLogging = method.getAnnotation(HelloLogging.class);
            //do something
            String value = helloLogging.value();
            System.out.println(value);
        }

        //继续执行参数
        result = joinPoint.proceed();
        return result;
    }


}
