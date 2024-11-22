package command;

import model.Knight;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

public class LoadFromFileCommand implements Command {
    private final Knight knight;
    private final Scanner scanner;
    private static final Logger logger = Logger.getLogger(LoadFromFileCommand.class.getName());

    public LoadFromFileCommand(Knight knight, Scanner scanner) {
        this.knight = knight;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Введіть шлях до файлу: ");
        String filePath = scanner.next();
        knight.loadFromFile(filePath);
        logger.info("Екіпірування завантажено з файлу: " + filePath);
    }

    @Override
    public String info() {
        return "Завантажити екіпірування з файлу.";
    }
}
