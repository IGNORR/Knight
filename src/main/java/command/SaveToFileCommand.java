package command;

import model.Knight;
import java.util.Scanner;

public class SaveToFileCommand implements Command {
    private final Knight knight;
    private final Scanner scanner;

    public SaveToFileCommand(Knight knight, Scanner scanner) {
        this.knight = knight;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Введіть шлях до файлу: ");
        String filePath = scanner.next();
        knight.saveToFile(filePath);
        System.out.println("Завантаження в файл успішне.");
    }
    @Override
    public String info() {
        return "Завантажити екіпірування в файл.";
    }
}
