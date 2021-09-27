import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("Hello, world!");
        Manager manager = new Manager();
        manager.start();
    }
}
