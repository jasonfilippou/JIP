package generic.threads;

public class Main {
    public static void main(String[] args){
        Producer p = new Producer(2, 10);
        p.mainLoop();
    }
}
