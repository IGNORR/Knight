package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {
    private Knight knight;

    @BeforeEach
    public void setup() {
        knight = new Knight();
    }

    @Test
    public void testAddEquipment() {
        Equipment equipment = new Equipment("Меч", 1.5, 150);
        knight.addEquipment(equipment);
        assertEquals(1, knight.findEquipmentByPriceRange(100, 200).size());
    }

    @Test
    public void testGetTotalPrice() {
        knight.addEquipment(new Equipment("Меч", 1.5, 150));
        knight.addEquipment(new Equipment("Щит", 2.0, 200));
        assertEquals(350, knight.getTotalPrice());
    }

    @Test
    public void testSortEquipmentByWeight() {
        knight.addEquipment(new Equipment("Щит", 3.0, 200));
        knight.addEquipment(new Equipment("Меч", 1.5, 150));
        knight.sortEquipmentByWeight();
        assertEquals("Меч", knight.findEquipmentByPriceRange(100, 200).get(0).getName());
    }

    @Test
    public void testFindEquipmentByPriceRange() {
        knight.addEquipment(new Equipment("Меч", 1.5, 150));
        knight.addEquipment(new Equipment("Шолом", 0.5, 50));
        List<Equipment> foundEquipment = knight.findEquipmentByPriceRange(100, 200);
        assertEquals(1, foundEquipment.size());
        assertEquals("Меч", foundEquipment.get(0).getName());
    }

    @Test
    public void testReplaceEquipment() {
        knight.addEquipment(new Equipment("Меч", 1.5, 150));
        knight.replaceEquipment("Меч", new Equipment("Спис", 2.5, 250));
        assertEquals("Спис", knight.findEquipmentByPriceRange(200, 300).get(0).getName());
    }

    @Test
    public void testRemoveEquipment() {
        knight.addEquipment(new Equipment("Меч", 1.5, 150));
        knight.removeEquipment("Меч");
        assertEquals(0, knight.findEquipmentByPriceRange(100, 200).size());
    }

    @Test
    public void testSaveAndLoadFromFile() throws IOException {
        knight.addEquipment(new Equipment("Меч", 1.5, 150));
        String filePath = "test.txt";
        knight.saveToFile(filePath);

        Knight loadedKnight = new Knight();
        loadedKnight.loadFromFile(filePath);
        assertEquals(1, loadedKnight.findEquipmentByPriceRange(100, 200).size());

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
