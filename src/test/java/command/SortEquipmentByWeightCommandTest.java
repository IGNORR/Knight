package command;

import model.Knight;
import model.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortEquipmentByWeightCommandTest {
    private Knight knight;

    @BeforeEach
    public void setup() {
        knight = new Knight();
    }

    @Test
    public void testExecute() {
        knight.addEquipment(new Equipment("Меч", 1.5, 150));
        knight.addEquipment(new Equipment("Щит", 2.0, 200));

        SortEquipmentByWeightCommand sortCommand = new SortEquipmentByWeightCommand(knight);
        sortCommand.execute();

        assertEquals("Меч", knight.findEquipmentByPriceRange(0, 1000).get(0).getName());
        assertEquals("Щит", knight.findEquipmentByPriceRange(0, 1000).get(1).getName());
    }
}
