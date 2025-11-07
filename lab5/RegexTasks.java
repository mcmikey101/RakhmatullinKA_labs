import java.util.regex.*;
import java.util.*;

public class RegexTasks {

    public static void findNumbers(String text) {
        try {
            Pattern pattern = Pattern.compile("\\d+\\.?\\d+");
            Matcher matcher = pattern.matcher(text);
            System.out.println("Найденные числа:");
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        } catch (Exception e) {
            System.out.println("Ошибка при поиске чисел: " + e.getMessage());
        }
    }

    public static void checkPassword(String password) {
        try {
            String regex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
            if (password.matches(regex))
                System.out.println("Пароль корректен");
            else
                System.out.println("Пароль некорректен");
        } catch (Exception e) {
            System.out.println("Ошибка при проверке пароля: " + e.getMessage());
        }
    }

    public static void markUpperAfterLower(String text) {
        try {
            String result = text.replaceAll("([a-z])([A-Z])", ".!.$1$2.!.");
            System.out.println("Результат:\n" + result);
        } catch (Exception e) {
            System.out.println("Ошибка при обработке текста: " + e.getMessage());
        }
    }

    public static void checkIPAddress(String ip) {
        try {
            String regex = "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$";
            if (ip.matches(regex))
                System.out.println("IP-адрес корректен");
            else
                System.out.println("IP-адрес некорректен");
        } catch (Exception e) {
            System.out.println("Ошибка при проверке IP: " + e.getMessage());
        }
    }

    public static void findWordsStartingWith(String text, char letter) {
        try {
            String regex = "\\b" + Pattern.quote(String.valueOf(letter)) + "\\w*\\b";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            System.out.println("Слова, начинающиеся с '" + letter + "':");
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        } catch (Exception e) {
            System.out.println("Ошибка при поиске слов: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String sampleText = "Текст с числами 123 и 0.008";
        findNumbers(sampleText);
        checkPassword("Password1");
        markUpperAfterLower("пример abC текста с lowerUpper словами");
        checkIPAddress("192.168.0.1");
        findWordsStartingWith("apple banana apricot Avocado orange", 'a');
    }
}
