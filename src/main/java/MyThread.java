import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyThread implements Runnable {
    List<Integer> list;
    CountDownLatch latch;

    public MyThread(CountDownLatch latch, List<Integer> list) {
        this.list = list;
        this.latch = latch;

        new Thread(this);
    }

    @Override
    public void run() {
        synchronized (list){
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.set(i,list.get(i)+10));
                latch.countDown();
            }
        }
    }
}
