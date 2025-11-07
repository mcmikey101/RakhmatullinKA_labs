import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class CustomAgeException extends Exception {
    public CustomAgeException(String message) {
        super(message);
    }
}

class ExceptionLogger {
    private static final String LOG_FILE = "exceptions.log";

    public static void logException(Exception e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("Исключение: " + e.getClass().getName());
            writer.println("Сообщение: " + e.getMessage());
            writer.println("Стек вызовов:");
            e.printStackTrace(writer);
            writer.println("-----");
        } catch (IOException ioException) {
            System.out.println("Ошибка при записи в лог-файл.");
        }
    }
}

public class CustomException {
    public static void main(String[] args) throws CustomAgeException, NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите возраст: ");
            String input = scanner.nextLine();

            int age = Integer.parseInt(input);

            if (age < 0 || age > 120) {
                throw new CustomAgeException("Недопустимый возраст: " + age);
            }

            System.out.println("Возраст принят: " + age);

        } catch (CustomAgeException e) {
            System.out.println("Ошибка: " + e.getMessage());
            ExceptionLogger.logException(e);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введено нечисловое значение возраста.");
            ExceptionLogger.logException(e);
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
            ExceptionLogger.logException(e);
        } finally {
            scanner.close();
        }
    }
}
