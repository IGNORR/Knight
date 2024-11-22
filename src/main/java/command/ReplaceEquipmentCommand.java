package command;

import model.Knight;
import model.Equipment;
import java.util.Scanner;
import java.util.logging.*;

public class ReplaceEquipmentCommand implements Command {
    private final Knight knight;
    private final Scanner scanner;
    private static final Logger logger = Logger.getLogger(ReplaceEquipmentCommand.class.getName());

    public ReplaceEquipmentCommand(Knight knight, Scanner scanner) {
        this.knight = knight;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.print("Введіть назву екіпірування для зміни: ");
            String name = scanner.next();
            System.out.print("Введіть нову назву: ");
            String newName = scanner.next();
            System.out.print("Введіть нову вагу: ");
            double newWeight = scanner.nextDouble();
            System.out.print("Введіть нову ціну: ");
            double newPrice = scanner.nextDouble();
            knight.replaceEquipment(name, new Equipment(newName, newWeight, newPrice));
            logger.info("Екіпірування замінено: " + name + " на " + newName);
        } catch (Exception e) {
            logger.severe("Помилка при заміні екіпірування: " + e.getMessage());
        }
    }

    @Override
    public String info() {
        return "Замінити екіпірування.";
    }
}
