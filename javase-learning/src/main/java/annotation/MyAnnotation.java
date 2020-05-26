package annotation;

import java.lang.annotation.*;

/**
 * @Author kuroneko
 * 元注解：修饰注解的注解
 *  Retention : 注解的生命周期,默认是class,如果要在反射中使用则需要使用runtime
 *  Target:指定annotation可以修饰哪些元素
 *  Document：被该元注解修饰的注解会被javadoc提取成文档，默认情况下javadoc不包括注解
 *  Inherited：被该元注解修饰的注解具有继承性，即父类有这个注解，子类自动会有
 *  jdk8新特性：可重复注解，类型注解
 *  Repeatabble:可以重复注解，但还是要声明一个MyAnnotations 两者的Inherited、Target和Retention要相同
 *  类型注解：
 *      ElementType.TYPE_PARAMETER：注解能写在类型变量的声明语句中（如：泛型声明）
 *      ElementType.TYPE_USE：注解能写在使用类型的任何语句中
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.TYPE_PARAMETER,ElementType.TYPE_USE})
@Repeatable(MyAnnotations.class)
public @interface MyAnnotation {
    /**
     * 有默认值的注解方法
     * @return
     */
    String value() default "hello";
}
