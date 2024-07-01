package class03;

import java.io.IOException;

public class App_1 {
    public static void main(String[] args) throws IOException {
        int[] arr = new int[]{1,2,3,5,4};
        int left = 0, right = arr.length - 1;
        int[][] dp = new int[arr.length][arr.length];
        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                dp[i][j] = -1;
            }
        }
        int res = process(left, right, arr);
        int res1 = process1(left, right, arr, dp);

        System.out.println("res:" + res);
        System.out.println("res1:" + res1);
    }

    static int process1(int left, int right, int[] arr, int[][] dp) {
        if (left > right) {
            return 0;
        }

        if (left == right) {
            return arr[left];
        }

        if(dp[left][right] != -1) {
            return dp[left][right];
        }

        //摸左边
        int leftRes = arr[left] + Math.min(process(left + 1 + 1, right, arr), process(left + 1, right - 1, arr));
        //摸右边
        int rightRes = arr[right] + Math.min(process(left, right - 1 - 1, arr), process(left + 1, right - 1, arr));

        int r = Math.max(leftRes, rightRes);
        return dp[left][right] = r;
    }

    static int process(int left, int right, int[] arr) {
        if (left > right) {
            return 0;
        }

        if (left == right) {
            return arr[left];
        }

        //摸左边
        int leftRes = arr[left] + Math.min(process(left + 1 + 1, right, arr), process(left + 1, right - 1, arr));
        //摸右边
        int rightRes = arr[right] + Math.min(process(left, right - 1 - 1, arr), process(left + 1, right - 1, arr));

        return Math.max(leftRes, rightRes);
    }
}
