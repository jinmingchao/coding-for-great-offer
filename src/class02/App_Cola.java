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
//        int[] valOfCoins = new int[]{100,50,10};
//        int[] numOfCoins = new int[]{3,4,1};
//        int x = 250;
//        int m = 2;
//        int op = solution(valOfCoins, numOfCoins, x, m);
//        System.out.println("共需要"+op+"次操作.");

        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("如果错误会打印错误数据，否则就是正确");
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int[] valOfCoins = new int[]{100,50,10};
            int[] numOfCoins = new int[]{c,b,a};
            int ans1 = solution(valOfCoins, numOfCoins, x, m);
            int ans2 = right(m, a, b, c, x);
            if (ans1 != ans2) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");
                break;
            }
        }
        System.out.println("test end");
    }


    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = { 100, 50, 10 };
        int[] zhang = { c, b, a };
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }

    public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
        for (; i < 3; i++) {
            zhang[i] += (oneTimeRest / qian[i]) * times;
            oneTimeRest %= qian[i];
        }
    }

    private static void updateRemain(int[] valOfCoins, int[] numOfCoins, int i, int oneTimeRest, int time) {
        for( ;i < valOfCoins.length; i++){
            numOfCoins[i] += (oneTimeRest / valOfCoins[i]) * time;
            oneTimeRest %= valOfCoins[i];
        }
    }
    private static int solution(int[] valOfCoins, int[] numOfCoins, int x, int m) {

            int historicalVal = 0;
            int historicalOp = 0;
            int op = 0;

            // 从大面值到小面值, 计算购买可乐的数量
            for (int i = 0; i < valOfCoins.length && m != 0; i++){
                   //先花历史金额, 包含当前i种硬币, WARN: 之所以要考虑当前i种硬币，是为了能清空historicalOp
                   int numOfCoinsUsedForFirstCola = (x - historicalVal + valOfCoins[i] - 1) / valOfCoins[i];
                   if (numOfCoins[i] >= numOfCoinsUsedForFirstCola) {
                      updateRemain(valOfCoins, numOfCoins, i + 1, historicalVal + numOfCoinsUsedForFirstCola * valOfCoins[i] - x ,1);
                      //当前种货币张数去掉
                      numOfCoins[i] -= numOfCoinsUsedForFirstCola;
                      op += numOfCoinsUsedForFirstCola + historicalOp;
                      historicalOp = 0;
                      historicalVal = 0;
                      m--;

                   } else {
                       historicalVal += numOfCoins[i] * valOfCoins[i];
                       historicalOp += numOfCoins[i];
                       continue;
                   }

                    //计算购买一个x需要i种硬币的张数
                    int numOfCoinsUsed = (x + valOfCoins[i] - 1) / valOfCoins[i];
                    //使用i种硬币可以购买多少x
                    int numOfXbought = Math.min(numOfCoins[i] / numOfCoinsUsed, m);
                    //当前i种货币每次购买可乐的找零数
                    int restVal = numOfCoinsUsed * valOfCoins[i] - x;
                    //直接把所有找零的钱给后面种类的货币
                    updateRemain(valOfCoins, numOfCoins, i + 1, restVal , numOfXbought);
                    //更新op
                    op+= numOfCoinsUsed * numOfXbought;
                    //更新cola
                    m -=  numOfXbought;
                    //当前i种货币扣减张数
                    numOfCoins[i] -= numOfXbought * numOfCoinsUsed;

                    //更新历史值
                    historicalVal += numOfCoins[i] * valOfCoins[i];
                    historicalOp += numOfCoins[i];
            }
            return m == 0 ? op : -1;

    }




}
