package class19;

/**
 *  背包问题
 *   w[] 物品重量
 *   v[] 物品价值
 *   bag 背包剩余空间
 *   返回最大价值数
 */
public class App_1 {

    public static void main(String[] args) {

        int[] w = new int[]{3,2,4,7,3,1,7};
        int[] v = new int[]{5,6,3,19,12,4,2};
        int bag = 15;
        System.out.println(maxVal(w,v,bag));

        int[][] memory = new int [w.length][bag + 1];
        for(int i = 0; i < w.length; i++) {
            for (int j = 0; j < bag + 1; j++) {
                memory[i][j] = -1;
            }
        }
        System.out.println(maxVal1(w,v,bag,memory));
    }

    private static int maxVal1(int[] w, int[] v, int bag, int[][] memory) {
        return process1(v, w, bag, 0, memory);
    }

    static int process1(int[] v, int[] w, int bag, int idx, int[][] memory) {
        // 健壮性判断
        if ( idx == w.length || bag < 0 ) { //已经到了最后一个货物
            return 0;
        }

        if (memory[idx][bag] != -1 ) {
           return memory[idx][bag];
        }

        //选idx
        int res1 = 0;
        if(w[idx] > bag) { //装不下idx, 去下一个试试
            res1 =  memory[idx + 1][bag] != -1 ? memory[idx + 1][bag] : (memory[idx + 1][bag] = process(v, w, bag, idx + 1));
        } else { //装的下idx，可以选择装或者不装, 选较大的
            res1 =  Math.max(memory[idx + 1][bag] != -1 ? memory[idx + 1][bag] : (memory[idx + 1][bag] = process(v, w, bag, idx + 1)), v[idx] + memory[idx + 1][bag - w[idx]] != -1 ? memory[idx + 1][bag - w[idx]] : (memory[idx + 1][bag - w[idx]] = process(v, w, bag - w[idx], idx + 1))); //
        }

        //不选idx
        int res2 = memory[idx + 1][bag] != -1 ? memory[idx + 1][bag] : (memory[idx + 1][bag] = process(v, w, bag, idx + 1));
        return Math.max(res1, res2);
    }

    private static int maxVal(int[] w, int[] v, int bag) {
        return process(v, w, bag, 0);
    }

    /**
     * 当前考虑到了idx号货物
     * @param v
     * @param w
     * @param bag
     * @param idx
     * @return 考虑了idx号货物时，可以获取的最大价值
     */
    static int process(int[] v, int[] w, int bag, int idx){
           // 健壮性判断
           if ( idx == w.length || bag < 0 ) { //已经到了最后一个货物
                return 0;
           }

           //选idx
           int res1 = 0;
           if(w[idx] > bag) { //装不下idx, 去下一个试试
               res1 =  process(v, w, bag, idx + 1);
           } else { //装的下idx，可以选择装或者不装, 选较大的
               res1 =  Math.max(process(v, w, bag, idx + 1), v[idx] + process(v, w, bag - w[idx], idx + 1)); //
           }
           //不选idx
           int res2 = process(v, w, bag, idx + 1);
           return Math.max(res1, res2);
    }
}
