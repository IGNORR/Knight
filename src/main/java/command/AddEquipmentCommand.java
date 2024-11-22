package command;

import model.Knight;
import model.Equipment;
import java.util.Scanner;
import java.util.logging.*;

public class AddEquipmentCommand implements Command {
    private final Knight knight;
    private final Scanner scanner;
    private static final Logger logger = Logger.getLogger(AddEquipmentCommand.class.getName());

    public AddEquipmentCommand(Knight knight, Scanner scanner) {
        this.knight = knight;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.print("Введіть назву: ");
            String name = scanner.next();
            System.out.print("Введіть вагу: ");
            double weight = scanner.nextDouble();
            System.out.print("Введіть ціну: ");
            double price = scanner.nextDouble();
            knight.addEquipment(new Equipment(name, weight, price));
            logger.info("Екіпірування додано: " + name);
        } catch (Exception e) {
            logger.severe("Помилка при додаванні екіпірування: " + e.getMessage());
        }
    }

    @Override
    public String info() {
        return "Додати екіпірування.";
    }
}
