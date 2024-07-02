package class03;

import java.io.IOException;

public class App_1 {
    public static void main(String[] args) throws IOException {

        int[] arr = new int[]{5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        int left = 0, right = arr.length - 1;
        int[][] f = new int[arr.length][arr.length], g = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                f[i][j] = -1;
                g[i][j] = -1;
            }
        }

        int res1t = Math.max(processf(left, right, arr), processn(left, right, arr));
        int res2t = Math.max(processf1(left, right, arr, f, g), processn1(left, right, arr, f, g));
        int res3t = process_dp(arr);

        System.out.println("res1t:" + res1t);
        System.out.println("res2t:" + res2t);
        System.out.println("res3t:" + res3t);
    }

    private static int process_dp(int[] arr) {
        int l = arr.length, r = arr.length;
        int[][] f = new int[l][r], g = new int[l][r];
        int colStart = 0;

        while (colStart < r) {
            for (int i = 0, j = colStart; j < r; i++, j++) {
                if (i == j) {
                    f[i][j] = arr[i];
                    g[i][j] = 0;
                } else {
                    int leftRes = arr[i] + g[i + 1][j];
                    int rightRes = arr[j] + g[i][j - 1];
                    f[i][j] = Math.max(leftRes, rightRes);

                    leftRes = f[i + 1][j];
                    rightRes = f[i][j - 1];
                    g[i][j] = Math.min(leftRes, rightRes);
                }
            }
            colStart++;
        }
        return Math.max(f[0][r - 1],g[0][r - 1]);
    }

    static int processf(int left, int right, int[] arr) {

        if (left > right) {
            return 0;
        }

        if (left == right) {
            return arr[left];
        }

        int leftRes = arr[left] + processn(left + 1, right, arr);
        int rightRes = arr[right] + processn(left, right - 1, arr);
        return Math.max(leftRes, rightRes);
    }

    static int processn(int left, int right, int[] arr) {

        if (left >= right) return 0;
        int leftRes = processf(left + 1, right, arr);
        int rightRes = processf(left, right - 1, arr);
        return Math.min(leftRes, rightRes);
    }

    static int processf1(int left, int right, int[] arr, int[][] f, int[][] g) {

        if (left > right) {
            return 0;
        }

        if (left == right) {
            return arr[left];
        }
        if (f[left][right] != -1) return f[left][right];


        int leftRes = arr[left] + (g[left + 1][right] != -1 ? g[left + 1][right] : (g[left + 1][right] = processn1(left + 1, right, arr, f, g)));
        int rightRes = arr[right] + (g[left][right - 1] != -1 ? g[left][right - 1] : (g[left][right - 1] = processn1(left, right - 1, arr, f, g)));

        return f[left][right] = Math.max(leftRes, rightRes);
    }

    static int processn1(int left, int right, int[] arr, int[][] f, int[][] g) {
        if (left >= right) return 0;
        if (g[left][right] != -1) return g[left][right];

        int leftRes = f[left + 1][right] != -1 ? f[left + 1][right] : (f[left + 1][right] = processf1(left + 1, right, arr, f, g));
        int rightRes = f[left][right - 1] != -1 ? f[left][right - 1] : (f[left][right - 1] = processf1(left, right - 1, arr, f, g));

        return g[left][right] = Math.min(leftRes, rightRes);
    }

    static int process2(int left, int right, int[] arr) {

        int[][] dp = new int[arr.length - 1][arr.length - 1];

        int l = dp.length, r = dp.length;

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < r; j++) {
                if (i < j) {

                } else if (i == j) {

                } else {

                }
            }
        }

//        return dp[][];
        return -1;
    }

    static int process1(int left, int right, int[] arr, int[][] dp) {
        if (left > right) {
            return 0;
        }

        if (left == right) {
            return arr[left];
        }

        if (dp[left][right] != -1) {
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
