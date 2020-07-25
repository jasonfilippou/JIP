package generic.threads;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class Producer {

    private final BlockingQueue<Runnable> jobs = new LinkedBlockingQueue<>();
    private final List<Consumer> workers = new LinkedList<>();
    private static final long SEED = 47;
    private Random r = new Random(SEED);
    private static final int MAX_JOBS_ADDED = 10;

    public void addWorkers(int workerNum){
        assert workerNum > 0 : "Need a positive int for my workers.";
        IntStream.range(0, workerNum).forEach(ignored->workers.add(new Consumer(jobs)));
    }

    public void addJobs(int jobNum){
        assert jobNum > 0 : "Need a positive int for my jobs.";
        IntStream.range(0, jobNum).forEach(ignored->jobs.add(new Job())); // This BlockingQueue instance is also viewed by the Consumers.
    }


    public void stopJobs(){
        for (Consumer c : workers)
            c.stopWorking();
    }

    public void mainLoop(){
        try {
            while(true) {
                for (Consumer c : workers)
                    c.runJob();
                for (Consumer c : workers) // TODO: Do I want to wait for every iteration?
                    c.join();
                addJobs(r.nextInt(MAX_JOBS_ADDED));
            }
        } catch(InterruptedException iexc){
            stopJobs();
        }
    }
}
