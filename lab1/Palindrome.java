public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(isPalindrome(args[i]));
        }
    }

    public static String reverseString(String str) {
        String s = "";
        for (int i = 1; i <= str.length(); i++) {
            s += str.charAt(str.length() - i);
        }
        return s;
    }
    public static boolean isPalindrome(String str) {
        return str.equals(reverseString(str));
    }
}
