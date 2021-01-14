package others.threads;

public class Main {
    public static void main(String[] args){
        Producer p = new Producer(5, 5);
        p.mainLoop();
        p.stopJobs();
    }
}
