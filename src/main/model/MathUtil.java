package main.model;

public class MathUtil {

    //LNKO több számra
    public static int GCD(int... nums) {
        int ret = 0;
        for (int num : nums) {
            ret = GCD(num, ret);
        }
        return ret;
    }

    //LNKO két számra
    public static int GCD(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return GCD(num2, num1 % num2);
    }

    //LKKT több számra
    public static int LCM(int... nums) {
        int ret = 1;
        for (int num : nums) {
            ret = LCM(num, ret);
        }
        return ret;
    }

    //LKKT két számra
    public static int LCM(int num1, int num2) {
        return (num1 * num2) / GCD(num1, num2);
    }

    //Prím szám ellenörző
    public static boolean isPrime(int k) {
        if (k == 0 || k == 1) return false;
        for (int i = 2; i < k; ++i) {
            if (k % i == 0) {
                return false;
            }
        }
        return true;
    }

}
