package reflect;

import org.junit.Test;

import javax.swing.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 动态代理测试类
 * @author: kuroneko
 * @create: 2020-05-27 10:52
 **/
public class TestProxy {
    interface Hello{
        void morning(String name);
    }

    /**
     * 使用动态代理不用实现接口就能创建实例和实现方法
     */
    @Test
    public void helloProxy(){
        //方法实现的InvocationHandler
        //当执行代理对象的方法时，该执行什么逻辑
        InvocationHandler handler = new InvocationHandler() {
            /**
             *
             * @param proxy ：正在返回的代理对象，一般情况下，在invoke中不使用。就是Proxy.newProxyInstance中返回的代理类的对象。
             * @param method ：正在被调用的方法，就是代理类的的方法
             * @param args ： 就是代理类调用方法的传参
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("morning")){
                    System.out.println("moring : " + args[0]);
                }
                return null;
            }
        };
        // 获取类加载器ClassLoader，使用代理类一样的类加载器
        ClassLoader classLoader = Hello.class.getClassLoader();
        // 要实现的接口列表
        Class[] classes = {Hello.class};

        Hello hello = (Hello) Proxy.newProxyInstance(classLoader, classes, handler);
        hello.morning("Alice");
    }

    /**
     * 工厂接口
     */
    interface ClothFactory{
        void produceCloth();
        String printBrand(String brandName);
    }

    /**
     * 实现类
     */
    class NikeClothFactory implements  ClothFactory{

        @Override
        public void produceCloth() {
            System.out.println("Nike ClothFactory produces cloths");
        }

        @Override
        public String printBrand(String brandName) {
            return "Nike " + brandName;
        }
    }

    /**
     * 代理类
     */
    class FactoryProxy {

        Object target;
        //初始化被代理类
        public FactoryProxy(Object target) {
            this.target = target;
        }

        public Object getProxy(){
            // 获取类加载器ClassLoader，使用被代理类一样的类加载器
            ClassLoader classLoader = target.getClass().getClassLoader();
            // 被代理类的接口列表
            Class[] classes = target.getClass().getInterfaces();
            //在执行方法前后加一些逻辑
            //方法实现的InvocationHandler
            //当执行代理对象的方法时，该执行什么逻辑
            InvocationHandler invocationHandler = new InvocationHandler() {
                /**
                 *  在执行代理对象的所有方法时，都会执行这个invoke方法
                 * @param proxy ：正在返回的代理对象，一般情况下，在invoke中不使用。就是Proxy.newProxyInstance中返回的代理类的对象。
                 * @param method ：正在被调用的方法，就是代理类的的方法
                 * @param args ： 就是代理类调用方法的传参
                 * @return
                 * @throws Throwable
                 */
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                    System.out.println(proxy.getClass());
                    System.out.println("prepare...");
                    //调用代理对象的方法，采用被代理的实现，代理类的如入参。
                    //可能有返回参数
                    Object result = method.invoke(target, args);
                    System.out.println("sale cloths");
                    //return 返回参数
                    return result;
                }
            };
            //返回代理对象
            return Proxy.newProxyInstance(classLoader,classes,invocationHandler);
        }
    }

    @Test
    public void testProxy(){
        //被代理对象
        ClothFactory factory = new NikeClothFactory();
        //代理工厂
        FactoryProxy proxy =  new FactoryProxy(factory);
        //生成代理对象
        ClothFactory instance = (ClothFactory) proxy.getProxy();
        //执行代理对象的方法时，会同时执行被代理对象中的同名方法
        instance.produceCloth();
        //执行有返回值的方法
        String adidas = instance.printBrand("adidas");
        System.out.println(adidas);
    }
}
