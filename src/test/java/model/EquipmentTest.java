package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquipmentTest {
    @Test
    public void testEquipment() {
        Equipment equipment = new Equipment("Меч", 1.5, 150);
        assertEquals("Меч", equipment.getName());
        assertEquals(1.5, equipment.getWeight());
        assertEquals(150, equipment.getPrice());
    }

    @Test
    public void testToString() {
        Equipment equipment = new Equipment("Меч", 1.5, 150);
        assertEquals("Меч: Вага = 1.5 Ціна = 150.0", equipment.toString());
    }
}
