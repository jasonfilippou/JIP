import java.util.Random;

public class IterativeBinarySearch {

    public static int find(int[] a, int x){
        if(a == null || a.length == 0){
            return -1;
        } else {
            int start = 0, end = a.length - 1;
            while(start <= end){
                int mid = (start + end) / 2;
                if(x < a[mid])
                    end = mid - 1;
                else if (x > a[mid])
                    start = mid + 1;
                else
                    return mid;
            }
            return -1;
        }
    }

    public static void main(String[] args){

        int[] a1 = {};

        System.out.println(find(a1, (new Random()).nextInt())); // Should be -1

        int[] a2 = null;

        System.out.println(find(a2, (new Random()).nextInt())); // Should be -1
        
        int[] a3 = {-10};
        System.out.println(find(a3, -10)); // Should be 0
        System.out.println(find(a3, 10)); // Should be -1        


        int[] a4 = {200, 300};
        System.out.println(find(a4, 200)); // Should be 0
        System.out.println(find(a4, 300)); // Should be 1
        System.out.println(find(a4, 301)); // Should be -1
        
        int[] a5 = {20, 25, 30, 33, 40, 45};
        System.out.println(find(a5, 25)); // Should be 1
        System.out.println(find(a5, 20)); // Should be 0
        System.out.println(find(a5, 23)); // Should be -1
        System.out.println(find(a5, 30)); // Should be 2
        System.out.println(find(a5, 45)); // Should be 5


        


        

        

    }
}
