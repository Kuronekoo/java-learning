package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: shenchao
 * @create: 2020-05-09 16:42
 **/
public class ScheduledThreadPoolTests {
    public static volatile int i =1;
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(1);
        ses.scheduleAtFixedRate(()->{
            System.out.println(i);
            if(5==i){
                throw new RuntimeException();
            }
            i++;
        },100,100, TimeUnit.MILLISECONDS);

        Thread.sleep(1000);
        System.out.println(i);
        ses.awaitTermination(1,TimeUnit.SECONDS);
        ses.shutdown();
    }
}
