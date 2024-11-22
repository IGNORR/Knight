package command;

import model.Knight;
import model.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateTotalPriceCommandTest {
    private Knight knight;

    @BeforeEach
    public void setup() {
        knight = new Knight();
    }

    @Test
    public void testExecute() {
        knight.addEquipment(new Equipment("Меч", 1.5, 150));
        knight.addEquipment(new Equipment("Щит", 2.0, 200));

        CalculateTotalPriceCommand calculatePriceCommand = new CalculateTotalPriceCommand(knight);
        calculatePriceCommand.execute();

        assertEquals(350.0, knight.getTotalPrice());
    }
}

