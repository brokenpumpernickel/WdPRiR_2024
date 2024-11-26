public class Helpers {
    public static boolean isPrime(int i) { // Nie robcie tego w domu!
        for(int j = 2; j < i; ++j)
            if(i % j == 0)
                return false;
        return true;
    }

    public static double fibon(double n) {
        if(n < 2)
            return n;

        return fibon(n - 1) + fibon(n - 2);
    }
}
