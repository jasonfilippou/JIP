package generic.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer extends Thread {

    private static final long WORKER_TIMEOUT= 5;
    private boolean workFlag = true;

    private BlockingQueue<Runnable> jobs;

    public Consumer(BlockingQueue<Runnable> jobs){
        this.jobs = jobs;
    }

    public void runJob(){
        while(true) {
            Runnable r = null;
            try {
                runJobIfAllowed(r);
            } catch(InterruptedException iexc){
                // For now those Exceptions will be handled by Producer
                System.out.println("Caught an InterruptedException in Consumer " + Thread.currentThread().getId() + ". This shouldn't happen.");
            }
        }
    }

    private void runJobIfAllowed(Runnable r) throws InterruptedException{
        r = jobs.poll(WORKER_TIMEOUT, TimeUnit.SECONDS); // Can throw InterruptedException if interrupted while waiting
        if(r != null && workFlag)
            r.run();
    }


    public void stopWorking(){
        workFlag = false;
    }

}
