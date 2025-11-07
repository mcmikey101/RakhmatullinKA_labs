public class test {
    public static void main(String[] args) {
        try {
            int x = 10 / 0;
        } catch (TypeNotPresentException e) {
            System.out.println("Caught exception");
        } finally {
            System.out.println("Finally executes");
        }
        System.out.println("After try-catch-finally");
    }
}
