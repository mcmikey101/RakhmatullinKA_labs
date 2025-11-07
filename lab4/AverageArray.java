import java.util.InputMismatchException;
import java.util.Scanner;

public class AverageArray {
    public static void main(String[] args) throws InputMismatchException, ArrayIndexOutOfBoundsException {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите количество элементов массива: ");
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("Размер массива должен быть положительным числом.");
                return;
            }

            double[] array = new double[n];
            System.out.println("Введите элементы массива:");
            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextDouble();
            }

            double sum = 0;
            for (int i = 0; i < n; i++) {
                sum += array[i];
            }

            double average = sum / n;
            System.out.println("Среднее арифметическое: " + average);

            //array[n+1] = 2.0;

        } catch (InputMismatchException e) {
            System.out.println("Ошибка: введены неверные данные.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: выход за пределы массива.");
        } finally {
            scanner.close();
        }
    }
}
