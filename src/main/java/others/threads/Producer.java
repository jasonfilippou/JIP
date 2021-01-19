package others.threads;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class Producer{

    private final BlockingQueue<Runnable> jobs = new LinkedBlockingQueue<>();
    private final List<Consumer> workers = new LinkedList<>();
    private static final int DEFAULT_WORKERS = 2, DEFAULT_JOBS = 5;
    private int workerNum = 0, jobNum = 0;

    public Producer(int workerNum, int jobNum) {
        addWorkers(workerNum);
        addJobs(jobNum);
    }

    public Producer() {
        this(DEFAULT_WORKERS, DEFAULT_JOBS);
    }

    public void addWorkers(int workerNum) {
        assert workerNum > 0 : "Need a positive int for my workers.";
        IntStream.range(0, workerNum).forEach(ignored -> workers.add(new Consumer(jobs)));
        this.workerNum += workerNum;
    }

    public void addJobs(int jobNum) {
        assert jobNum > 0 : "Need a positive int for my jobs.";
        IntStream.range(0, jobNum).forEach(ignored -> jobs.add(new Job())); // This BlockingQueue instance is also viewed by the Consumers.
        this.jobNum += jobNum;
    }

    public void stopJobs() {
        for (Consumer c : workers)
            c.stopWorking();
        for (Consumer c : workers) {
            try {
                c.join();
            } catch(InterruptedException iexc){
                System.out.println("A call to Thread.join() threw an instance of " + iexc.getClass().getSimpleName() + ". This should not happen.");
            }
        }
    }

    public void mainLoop(){
        for (Consumer c : workers)
            c.start();
        for (Consumer c : workers)
            try {
                c.join();
            } catch(InterruptedException ignored){}
    }

}
