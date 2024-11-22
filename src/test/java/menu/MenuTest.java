package menu;

import command.Command;
import command.DisplayEquipmentCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    public void setup() {
        menu = new Menu();
    }

    @Test
    public void testAddAndExecuteDisplayEquipmentCommand() {
        Command displayCommand = new DisplayEquipmentCommand(menu.knight);
        menu.addCommand(1, displayCommand);

        menu.knight.addEquipment(new model.Equipment("Шолом", 1.2, 100));

        menu.executeCommand(1);

        assertEquals(1, menu.knight.findEquipmentByPriceRange(50, 150).size());
    }
}
