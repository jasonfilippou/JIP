package generic.threads;

import java.math.BigInteger;
import java.util.Random;

public class Job implements Runnable{

    private static final long STREAM_SIZE = 10000;

    @Override
    public void run() {
        Random r = new Random();
        BigInteger result = new BigInteger(String.valueOf(r.ints(STREAM_SIZE).map(x->x*x).sum()));  // Sum of squares of STREAM+SIZE many ints
        System.out.println("Thread " + Thread.currentThread().getId() + " computed: " + result + ". and exiting.");
    }
}
