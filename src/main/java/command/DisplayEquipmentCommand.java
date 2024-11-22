package command;

import model.Knight;
import java.util.logging.*;

public class DisplayEquipmentCommand implements Command {
    private final Knight knight;
    private static final Logger logger = Logger.getLogger(DisplayEquipmentCommand.class.getName());

    public DisplayEquipmentCommand(Knight knight) {
        this.knight = knight;
    }

    @Override
    public void execute() {
        try {
            knight.displayEquipment();
            logger.info("Список екіпірування відображено.");
        } catch (Exception e) {
            logger.severe("Помилка при відображенні екіпірування: " + e.getMessage());
        }
    }

    @Override
    public String info() {
        return "Вивести екіпірування.";
    }
}
