package command;

import model.Knight;
import model.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class RemoveEquipmentCommandTest {
    private Knight knight;

    @BeforeEach
    public void setup() {
        knight = new Knight();
    }

    @Test
    public void testExecute() {
        knight.addEquipment(new Equipment("Меч", 1.5, 150));

        String input = "Меч\n";
        InputStream systemInBackup = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        try {
            RemoveEquipmentCommand removeCommand = new RemoveEquipmentCommand(knight, new java.util.Scanner(System.in));
            removeCommand.execute();
            assertEquals(0, knight.findEquipmentByPriceRange(100, 200).size());
        } finally {
            System.setIn(systemInBackup);
        }
    }
}
