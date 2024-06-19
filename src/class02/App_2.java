package class02;

import java.util.HashMap;
import java.util.Map;

public class App_2 {

    public static void main(String[] args) {
            int N = 7;
            int start = 2;
            int aim = 4;
            int K = 4;
            Map<String,Integer> cache = new HashMap<>();
            System.out.println(process(N,start,aim,K,cache));
    }

    static int process(int N, int start, int aim, int K, Map<String, Integer> cache) {
           if (K == 0) {
              if(start == aim) {
                  return 1;
              } else {
                  return 0;
              }
           }

           int leftPos = start - 1;
           int rightPos = start + 1;

           if (start == 1) {
               leftPos = 2;
           }
           if (start == N) {
               rightPos = N - 1;
           }

           int r = 0;
           String k = start +"-"+ aim + "-" + (K - 1);
           if (cache.containsKey(k)) {
               return cache.get(k);
           }

           String k1, k2;
           if (leftPos == rightPos) {
               k1 = leftPos + "-" + aim + "-" + (K - 1);
               if (cache.containsKey(k)) {
                   return cache.get(k);
               }
               r = process(N, leftPos, aim, K - 1, cache);
               cache.put(k1, r);
           } else {
               k1 = leftPos + "-" + aim + "-" + (K - 1);
               k2 = rightPos + "-" + aim + "-" + (K - 1);
               int r1, r2;
               if (cache.containsKey(k1)) {
                   r1 = cache.get(k1);
               } else {
                   r1 = process(N, leftPos, aim, K - 1, cache);
                   cache.put(k1, r1);
               }

               if (cache.containsKey(k2)) {
                   r2 = cache.get(k2);
               } else {
                   r2 = process(N, rightPos, aim, K - 1, cache);
                   cache.put(k2, r2);
               }
               r = r1 + r2;
           }

           cache.put(k, r);
           return r;
    }
}
