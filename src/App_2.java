import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class App_2 {
    public static void main(String[] args) {

        BigDecimal a = new BigDecimal(3.1415926).setScale(10, RoundingMode.HALF_UP);
        BigDecimal a1 = new BigDecimal(3.1415926933).setScale(10, RoundingMode.HALF_UP);
        BigDecimal a2 = new BigDecimal(3232131.1415926933213123).setScale(10, RoundingMode.HALF_UP);
        BigDecimal a3 = new BigDecimal(3232131.14159).setScale(10, RoundingMode.HALF_UP);
        BigDecimal a4 = new BigDecimal(3232131.1142111111);
        BigDecimal a5 = new BigDecimal(3232131.1142);

        System.out.println(new DecimalFormat("#.##########").format(a));
        System.out.println(new DecimalFormat("#.##########").format(a1));
        System.out.println(new DecimalFormat("#.##########").format(a2));
        System.out.println(new DecimalFormat("#.##########").format(a3));
        System.out.println(new DecimalFormat("#.0000000000").format(a4));
        System.out.println(new DecimalFormat("#.0000000000").format(a5));
    }
}
