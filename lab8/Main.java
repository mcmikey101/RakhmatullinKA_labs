import java.io.IOException;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

// ------------------------------------------------------------
// Аннотация
// ------------------------------------------------------------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface DataProcessor { }

// ------------------------------------------------------------
// Менеджер
// ------------------------------------------------------------
class DataManager {
    private final List<Object> processors = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private List<String> processed = new ArrayList<>();

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    public void loadData(String source) {
        try {
            data = Files.readAllLines(Paths.get(source));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processData() {
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Callable<List<String>>> tasks = new ArrayList<>();

        for (Object proc : processors) {
            for (Method m : proc.getClass().getDeclaredMethods()) {
                if (m.isAnnotationPresent(DataProcessor.class)) {
                    tasks.add(() -> {
                        try {
                            return (List<String>) m.invoke(proc, data.stream());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        }

        try {
            for (Future<List<String>> f : pool.invokeAll(tasks)) {
                processed = f.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
        }
    }

    public void saveData(String destination) {
        try {
            Files.write(Paths.get(destination), processed);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

// ------------------------------------------------------------
// Обработчики
// ------------------------------------------------------------
class FilterProcessor {
    @DataProcessor
    public List<String> filter(Stream<String> s) {
        return s.filter(x -> x.length() > 3).collect(Collectors.toList());
    }
}

class TransformProcessor {
    @DataProcessor
    public List<String> transform(Stream<String> s) {
        return s.map(String::toUpperCase).collect(Collectors.toList());
    }
}

class AggregateProcessor {
    @DataProcessor
    public List<String> aggregate(Stream<String> s) {
        long count = s.count();
        return Collections.singletonList("COUNT=" + count);
    }
}

// ------------------------------------------------------------
// Тест
// ------------------------------------------------------------
public class Main {
    public static void main(String[] args) {
        DataManager m = new DataManager();

        m.registerDataProcessor(new FilterProcessor());
        m.registerDataProcessor(new TransformProcessor());
        m.registerDataProcessor(new AggregateProcessor());

        m.loadData("input.txt");
        m.processData();
        m.saveData("output.txt");
    }
}
