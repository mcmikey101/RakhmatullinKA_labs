abstract class Book {
    protected String title;
    protected String author;
    protected int year;

    public Book() {
        this.title = "";
        this.author = "";
        this.year = 0;
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() { 
        return title; 
    }
    public void setTitle(String title) { 
        this.title = title; 
    }

    public String getAuthor() { 
        return author;
    }
    public void setAuthor(String author) { 
        this.author = author;
    }

    public int getYear() { 
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public abstract void displayInfo();
    public abstract void read();
}

class Audiobook extends Book {
    private int duration;
    private String narrator;

    public Audiobook() {
        super();
        this.duration = 0;
        this.narrator = "";
    }

    public Audiobook(String title, String author, int year, int duration, String narrator) {
        super(title, author, year);
        this.duration = duration;
        this.narrator = narrator;
    }

    public int getDuration() { 
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNarrator() {
        return narrator;
    }
    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    @Override
    public void displayInfo() {
        System.out.println("Аудиокнига: " + title + " (" + year + "), автор: " + author +
                           ", продолжительность: " + duration + " мин, диктор: " + narrator);
    }

    @Override
    public void read() {
        System.out.println("Воспроизведение аудиокниги: " + title);
    }
}

class Film extends Book {
    private int duration;
    private String genre;

    public Film() {
        super();
        this.genre = "";
        this.duration = 0;
    }

    public Film(String title, String author, int year, String genre, int duration) {
        super(title, author, year);
        this.genre = genre;
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void displayInfo() {
        System.out.println("Фильм: " + title + " (" + year + "), режиссер: " + author +
                           ", жанр: " + genre + ", длительность: " + duration + " мин");
    }

    @Override
    public void read() {
        System.out.println("Просмотр фильма: " + title);
    }
}

class Musical extends Film {
    private static int counter = 0;
    private String composer;

    public Musical() {
        super();
        this.composer = "";
        counter++;
    }

    public Musical(String title, String author, int year, String genre, int duration, String composer) {
        super(title, author, year, genre, duration);
        this.composer = composer;
        counter++;
    }

    public String getComposer() {
        return composer;
    }
    public void setComposer(String composer) {
        this.composer = composer;
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public void displayInfo() {
        System.out.println("Мюзикл: " + getTitle() + " (" + getYear() + "), режиссер: " + getAuthor() +
                           ", жанр: " + getGenre() + ", длительность: " + getDuration() + " мин, композитор: " + composer);
    }

    @Override
    public void read() {
        System.out.println("Просмотр мюзикла: " + getTitle());
    }
}

public class OOP {
    public static void main(String[] args) {
        Audiobook ab = new Audiobook("Война и мир", "Лев Толстой", 1869, 1200, "Иван Иванов");
        Film f = new Film("Интерстеллар", "Кристофер Нолан", 2014, "Научная фантастика", 169);
        Musical m1 = new Musical("Кошки", "Тревор Нанн", 1981, "Мюзикл", 120, "Эндрю Ллойд Уэббер");
        Musical m2 = new Musical("Призрак Оперы", "Гарольд Принс", 1986, "Мюзикл", 160, "Эндрю Ллойд Уэббер");

        ab.displayInfo();
        ab.read();

        f.displayInfo();
        f.read();

        m1.displayInfo();
        m1.read();

        m2.displayInfo();
        m2.read();

        System.out.println("Кол-во объектов мюзиклов: " + Musical.getCounter());
    }
}
