package class01;

import java.io.IOException;
import java.util.*;

public class App_1 {

    public static void main(String[] args) throws IOException {
        for(int tm = 0; tm < 101; tm++) {
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

            int[] res = solution(dapArr, abilityArr);
            System.out.println();
        }
    }

    static int[] solution(DifficultyAndPaymentObject[] dapArr, int[] abilityArr) {

        Arrays.sort(dapArr, DifficultyAndPaymentObject.comparator);
        TreeMap<Integer, Integer> tm = new TreeMap();
        int[] res = new int[abilityArr.length];

        tm.put(dapArr[0].difficulty, dapArr[0].payment);
        DifficultyAndPaymentObject pre = dapArr[0];

        for (int i = 1; i < dapArr.length; i++) {
            if ( dapArr[i].payment > pre.payment ) {
                tm.put(dapArr[i].difficulty, dapArr[i].payment);
                pre = dapArr[i];
            }
        }

        int idx = 0;
        int[] difficultyArr = new int[tm.size()];

        for (Integer key : tm.keySet()) difficultyArr[idx++] = key;

        for (int i = 0; i < res.length; i++) {
            res[i] = binarySearch(difficultyArr, tm, abilityArr[i]);
        }

        return res;

    }

    private static int binarySearch(int[] difficultyArr, TreeMap<Integer, Integer> tm, int target) {
        int left = 0, right = difficultyArr.length - 1;

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (target < difficultyArr[mid]) {
                right = mid;
            } else if (target > difficultyArr[mid]) {
                left = mid + 1;
            } else {
                return tm.get(difficultyArr[mid]);
            }
        }

        if (difficultyArr[left] > target) {
            return left - 1 >= 0 && tm.get(difficultyArr[left - 1]) != null ? tm.get(difficultyArr[left - 1]) : 0;
        }
        return tm.get(difficultyArr[left]);
    }


    static class DifficultyAndPaymentObject {

        Integer payment;
        Integer difficulty;
        static Comparator<DifficultyAndPaymentObject> comparator;

        static {
            // 先按照难度升序排序，难度一样的，按照工资降序排序
            comparator = (o1, o2) -> {
                if (o1.difficulty != o2.difficulty) {
                    return o1.difficulty - o2.difficulty;
                } else {
                    return o2.payment - o1.payment;
                }
            };
        }

        private DifficultyAndPaymentObject(int payment, int difficulty) {
            this.payment = payment;
            this.difficulty = difficulty;

        }

    }
}
