package class02;

import java.util.*;

public class App_1 {

    public static void main(String[] args) {

        int M = 10, N = 5;
        int[] abilityArr = new int[N];

        DifficultyAndPaymentObject[] dapArr = new DifficultyAndPaymentObject[M];
        Random ran = new Random();

        for (int i = 0; i < M; i++) {
            dapArr[i] = new DifficultyAndPaymentObject(1 + ran.nextInt(15), 2 + ran.nextInt(10));
        }

        for (int i = 0; i < N; i++) {
            abilityArr[i] = 1 + ran.nextInt(15);
        }

        solution(dapArr, abilityArr);
    }

    static int[] solution(DifficultyAndPaymentObject[] dapArr, int[] abilityArr) {

        TreeMap<Integer, Integer> tm = new TreeMap(DifficultyAndPaymentObject.comparator);
        int[] res = new int[abilityArr.length];
        int[] difficultyArr = new int[dapArr.length];

        for (DifficultyAndPaymentObject dap : dapArr){
            tm.put(dap.difficulty, dap.payment);
        }

        int idx = 0;


        for(Integer key :tm.keySet()) difficultyArr[idx++] = key;

        for (int i = 0; i < res.length; i++) {
            res[i] = binarySearch(difficultyArr, tm,  abilityArr[i]);
        }

        return res;

    }

    private static int binarySearch(int[] difficultyArr, TreeMap<Integer, Integer> tm, int target) {
        int left = 0, right = difficultyArr.length;
        while (left < right) {
            int mid = left + (right - left) >> 1;
            if(target < difficultyArr[mid]) {
                right = mid;
            } else if(target > difficultyArr[mid]){
                left = mid + 1;
            } else {
                return tm.get(mid);
            }
        }

        return tm.get(left - 1);



    }


    static class DifficultyAndPaymentObject  {

        int payment;
        int difficulty;
        static Comparator<DifficultyAndPaymentObject> comparator;

        static {
            // 先按照难度升序排序，难度一样的，按照工资升序排序
            comparator = (o1, o2) -> {
                if (o1.difficulty != o2.difficulty) {
                    return o1.difficulty - o2.difficulty;
                } else {
                    return o1.payment - o2.payment;
                }
            };
        }

        private DifficultyAndPaymentObject(int payment, int difficulty) {
            this.payment = payment;
            this.difficulty = difficulty;

        }

    }
}
