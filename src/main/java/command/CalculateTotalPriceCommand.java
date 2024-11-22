package command;

import model.Knight;
import java.util.logging.*;

public class CalculateTotalPriceCommand implements Command {
    private final Knight knight;
    private static final Logger logger = Logger.getLogger(CalculateTotalPriceCommand.class.getName());

    public CalculateTotalPriceCommand(Knight knight) {
        this.knight = knight;
    }

    @Override
    public void execute() {
        try {
            double totalPrice = knight.getTotalPrice();
            System.out.println("Ціна екіпірування: " + totalPrice);
            logger.info("Загальна ціна екіпірування обчислена: " + totalPrice);
        } catch (Exception e) {
            logger.severe("Помилка при обчисленні загальної ціни екіпірування: " + e.getMessage());
        }
    }

    @Override
    public String info() {
        return "Порахувати ціну екіпірування.";
    }
}
