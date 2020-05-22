package annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: shenchao
 * @create: 2020-03-14 22:52
 **/
@MyAnnotation("test")
public class AnnotationTest {

    //jdk8的新特性
    class Generic< @MyAnnotation  T>{
        public void show(){
            List<@MyAnnotation String> list = new ArrayList<>();
            int num = (@MyAnnotation int)10L;
        }
    }

    @SuppressWarnings("unused")
    //jdk8以前的做法
    @MyAnnotations({@MyAnnotation,@MyAnnotation("test")})
    public void annos() {
        int num = 1;
    }
    //jdk8以后可以使用重复注解
    @MyAnnotation
    @MyAnnotation("test")
    public void annos2() {
        int num = 1;
    }

    @Test
    public void testAnnotationGet(){
        System.out.println("---类的注解----");
        Class<AnnotationTest> clazz = AnnotationTest.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        System.out.println("---方法的注解----");

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
            Annotation[] methodAnnotations = declaredMethod.getAnnotations();
            for (Annotation methodAnnotation : methodAnnotations) {
                System.out.println("    "+methodAnnotation);

            }
        }
    }



}
