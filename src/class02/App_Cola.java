package class02;

/*
 * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
 * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
 * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
 * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
 * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
 * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
 * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
 * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
 * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
 */

public class App_Cola {
    public static void main(String[] args) {
        int[] valOfCoins = new int[]{100,50,10};
        int[] numOfCoins = new int[]{3,4,1};
        int x = 250;
        int m = 2;
        int op = solution(valOfCoins, numOfCoins, x, m);
        System.out.println("共需要"+op+"次操作.");
    }

    private static int solution(int[] valOfCoins, int[] numOfCoins, int x, int m) {

            int historicalVal = 0;
            int historicalOp = 0;
            int op = 0;

            // 从大面值到小面值, 计算购买可乐的数量
            for (int i = 0; i < valOfCoins.length && m != 0; i++){
                   //先花历史金额
                   if (historicalVal >= x) {
                      op += historicalOp;
                      historicalOp = 0;
                      historicalVal %= x;
                   }

                    //计算购买一个x需要i种硬币的张数
                    int numOfCoinsUsed = (x + valOfCoins[i] - 1) / valOfCoins[i];
                    //使用i种硬币可以购买多少x
                    int numOfXbought = numOfCoins[i] / numOfCoinsUsed;

                    for (int j = 0; j < numOfXbought; j++) {
                        //找零
                        int restVal = numOfCoinsUsed * valOfCoins[i] - x;
                        //找零数累加到后续硬币种类上
                        updateRemain(valOfCoins, numOfCoins, i, restVal);
                        //更新op
                        op += numOfCoinsUsed;
                        //更新cola
                        if (--m == 0) {
                            return op;
                        }
                    }

                    //购买完之后剩余i种硬币的数量
                    int numOfCoinsRemain = numOfCoins[i] % numOfCoinsUsed;

                    //更新历史值
                    historicalVal += numOfCoins[i] - numOfCoinsUsed * numOfXbought;
                    historicalOp += numOfCoinsRemain;
            }
            return op;

    }

    private static void updateRemain(int[] valOfCoins, int[] numOfCoins, int i, int restVal) {
        for( ;i < valOfCoins.length && restVal > 0; i++){
            numOfCoins[i] += restVal / valOfCoins[i];
            restVal %= valOfCoins[i];
        }
    }


}
