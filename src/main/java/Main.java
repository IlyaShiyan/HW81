import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args){

        List<Integer> syncList=Collections.synchronizedList(new ArrayList<>(Arrays.asList(1,2,3,4,5)));
        System.out.println(syncList);
        CountDownLatch cd1 = new CountDownLatch(syncList.size());
        CountDownLatch cd2 = new CountDownLatch(syncList.size());
        CountDownLatch cd3 = new CountDownLatch(syncList.size());

        ExecutorService es = Executors.newFixedThreadPool(3);

        es.execute(new MyThread(cd1,syncList));
        es.execute(new MyThread(cd2,syncList));
        es.execute(new MyThread(cd3,syncList));

        try {
            cd1.await();
            cd2.await();
            cd3.await();
        }catch (InterruptedException e){
        System.out.println(e);
        }
        es.shutdown();

        System.out.println(syncList);

    }
}
