import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        String sourceFile = "source.txt";
        String destinationFile = "destination.txt";

        FileInputStream input = null;
        FileOutputStream output = null;

        try {
            input = new FileInputStream(sourceFile);
            output = new FileOutputStream(destinationFile);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            System.out.println("Копирование завершено успешно.");

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлами: " + e.getMessage());
        } finally {
            try {
                if (input != null) input.close();
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии исходного файла.");
            }
            try {
                if (output != null) output.close();
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии целевого файла.");
            }
        }
    }
}
