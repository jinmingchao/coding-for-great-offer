package class02;

public class App_1 {
    public static void main(String[] args) {
        int resLen = 0;
        int[] arr = new int[]{2,5,3,5,7,8,1,11,6};
        int posRight = 0, posLeft = 0, max = arr[0], min = arr[arr.length - 1];

        for (int i = 1; i < arr.length; i++) {
                if (arr[i] < max) {
                    posRight = i;
                } else {
                    max = arr[i];
                }
        }

        for (int i = arr.length - 1; i >= 0 ; i--) {
                if (arr[i] > min) {
                      posLeft = i;
                } else {
                    min = arr[i];
                }
        }

        resLen = posRight - posLeft + 1;
    }
}
