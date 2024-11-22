package command;

import model.Knight;
import model.Equipment;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;

public class FindEquipmentByPriceRangeCommand implements Command {
    private final Knight knight;
    private final Scanner scanner;
    private static final Logger logger = Logger.getLogger(FindEquipmentByPriceRangeCommand.class.getName());

    public FindEquipmentByPriceRangeCommand(Knight knight, Scanner scanner) {
        this.knight = knight;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.print("Введіть мінімальну ціну: ");
            double minPrice = scanner.nextDouble();
            System.out.print("Введіть максимальну ціну: ");
            double maxPrice = scanner.nextDouble();
            List<Equipment> foundEquipment = knight.findEquipmentByPriceRange(minPrice, maxPrice);
            System.out.println("Найдена екіпіровка:");
            foundEquipment.forEach(System.out::println);
            logger.info("Пошук екіпірування в діапазоні цін від " + minPrice + " до " + maxPrice + " завершено.");
        } catch (Exception e) {
            logger.severe("Помилка при пошуку екіпірування за ціною: " + e.getMessage());
        }
    }

    @Override
    public String info() {
        return "Знайти екіпірування за ціною.";
    }
}
