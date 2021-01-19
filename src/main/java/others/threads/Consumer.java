package others.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer extends Thread {

    private static final long WORKER_TIMEOUT= 5;
    private boolean workFlag = true;

    private BlockingQueue<Runnable> jobs;

    public Consumer(BlockingQueue<Runnable> jobs){
        this.jobs = jobs;
    }

    @Override
    public void run() {
        System.out.println("Consumer thread with ID " + Thread.currentThread().getId() + " starting execution.");
        while(!jobs.isEmpty()) {
            try {
                runJob();
            } catch(InterruptedException iexc){
                // Those Exceptions will be handled by Producer, so they shouldn't arrive at Consumer.
                System.out.println("Caught an " + iexc.getClass().getSimpleName() + " in Consumer with ID " + Thread.currentThread().getId() + ". This shouldn't happen.");
                // TODO: should I perhaps do a stopWorking() here? I mean, I'm expecting the Producer to send me this message...
            }
        }
        System.out.println("Consumer thread with ID " + Thread.currentThread().getId() + " ending execution.");
    }

    private void runJob() throws InterruptedException{
        Runnable r = jobs.poll(WORKER_TIMEOUT, TimeUnit.SECONDS); // Can throw InterruptedException if interrupted while waiting
        if(r != null)
            r.run();
    }


    public void stopWorking(){
        workFlag = false;
    }

}
