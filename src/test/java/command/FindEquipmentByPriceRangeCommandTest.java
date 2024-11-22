package command;

import model.Knight;
import model.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindEquipmentByPriceRangeCommandTest {
    private Knight knight;

    @BeforeEach
    public void setup() {
        knight = new Knight();
    }

    @Test
    public void testExecute() {
        knight.addEquipment(new Equipment("Меч", 1.5, 150));
        knight.addEquipment(new Equipment("Щит", 2.0, 200));

        String input = "100\n200\n";
        InputStream systemInBackup = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        try {
            FindEquipmentByPriceRangeCommand findCommand = new FindEquipmentByPriceRangeCommand(knight, new java.util.Scanner(System.in));
            findCommand.execute();

            List<Equipment> foundEquipment = knight.findEquipmentByPriceRange(100, 200);
            assertEquals(2, foundEquipment.size());
            assertEquals("Меч", foundEquipment.get(0).getName());
            assertEquals("Щит", foundEquipment.get(1).getName());
        } finally {
            System.setIn(systemInBackup);
        }
    }
}

