package command;

import model.Knight;
import model.Equipment;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class LoadFromFileCommandTest {
    private Knight knight;

    @BeforeEach
    public void setUp() {
        knight = new Knight();
    }

    @Test
    public void testLoadFromFile() throws IOException {
        String filePath = "test.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Щит,2.0,150.0");
            writer.newLine();
        }

        LoadFromFileCommand loadFromFileCommand = new LoadFromFileCommand(knight, new Scanner(filePath));
        loadFromFileCommand.execute();

        assertEquals(1, knight.getEquipmentList().size());
        Equipment loadedEquipment = knight.getEquipmentList().get(0);
        assertEquals("Щит", loadedEquipment.getName());
        assertEquals(2.0, loadedEquipment.getWeight());
        assertEquals(150.0, loadedEquipment.getPrice());

        new File(filePath).delete();
    }
}
