package command;

import model.Knight;
import java.util.Scanner;
import java.util.logging.*;

public class RemoveEquipmentCommand implements Command {
    private final Knight knight;
    private final Scanner scanner;
    private static final Logger logger = Logger.getLogger(RemoveEquipmentCommand.class.getName());

    public RemoveEquipmentCommand(Knight knight, Scanner scanner) {
        this.knight = knight;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.print("Введіть назву екіпірування для видалення: ");
            String name = scanner.next();
            knight.removeEquipment(name);
            logger.info("Екіпірування видалено: " + name);
        } catch (Exception e) {
            logger.severe("Помилка при видаленні екіпірування: " + e.getMessage());
        }
    }

    @Override
    public String info() {
        return "Видалити екіпірування.";
    }
}
