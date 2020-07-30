package generic.threads;

import java.util.Random;

public class Job implements Runnable{

    private static final long STREAM_SIZE = 1000000;


    @Override
    public void run() {
        Random r = new Random();
        int result = r.ints(STREAM_SIZE).map(x->x*x).sum();  // Sum of squares of STREAM_SIZE many ints. Can overflow; we don't care.
        System.out.println("Thread " + Thread.currentThread().getId() + " computed: " + result + ". and exiting.");
    }
}
