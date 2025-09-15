public class Primes {
    public static void main(String[] args) {
        for (int i = 2; i < Integer.parseInt(args[0]); i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isPrime(int n) {
        boolean isprime = true;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                isprime = false;
            }
        }
        return isprime;
    }
}