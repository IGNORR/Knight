package model;

import util.EmailService;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class Knight {
    private List<Equipment> equipmentList = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Knight.class.getName());

    static {
        try {
            InputStream inputStream = Knight.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch (IOException e) {
            System.err.println("Не вдалося завантажити конфігурацію логування: " + e.getMessage());
        }
    }

    public Knight() {
    }

    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
        logger.info("Додано екіпірування: " + equipment);
    }

    public void displayEquipment() {
        System.out.println("Екіпірування:");
        for (int i = 0; i < equipmentList.size(); i++) {
            System.out.println((i + 1) + ". " + equipmentList.get(i));
        }
        logger.info("Відображено список екіпірування.");
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Equipment equipment : equipmentList) {
            totalPrice += equipment.getPrice();
        }
        logger.info("Розраховано загальну вартість екіпірування: " + totalPrice);
        return totalPrice;
    }

    public void sortEquipmentByWeight() {
        equipmentList.sort(Comparator.comparingDouble(Equipment::getWeight));
        logger.info("Екіпірування відсортовано за вагою.");
    }

    public List<Equipment> findEquipmentByPriceRange(double minPrice, double maxPrice) {
        List<Equipment> result = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            if (equipment.getPrice() >= minPrice && equipment.getPrice() <= maxPrice) {
                result.add(equipment);
            }
        }
        logger.info("Знайдено екіпірування в ціновому діапазоні від " + minPrice + " до " + maxPrice);
        return result;
    }

    public void replaceEquipment(String oldName, Equipment newEquipment) {
        for (int i = 0; i < equipmentList.size(); i++) {
            if (equipmentList.get(i).getName().equalsIgnoreCase(oldName)) {
                equipmentList.set(i, newEquipment);
                logger.info("Екіпірування замінено: " + oldName + " на " + newEquipment);
                return;
            }
        }
        logger.warning("Екіпірування з назвою " + oldName + " не знайдено для заміни.");
    }

    public void removeEquipment(String name) {
        boolean removed = equipmentList.removeIf(e -> e.getName().equalsIgnoreCase(name));
        if (removed) {
            logger.info("Екіпірування з назвою " + name + " видалено.");
        } else {
            logger.warning("Екіпірування з назвою " + name + " не знайдено для видалення.");
        }
    }

    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Equipment equipment : equipmentList) {
                writer.write(equipment.getName() + "," + equipment.getWeight() + "," + equipment.getPrice());
                writer.newLine();
            }
            logger.info("Екіпірування збережено у файл: " + filePath);
        } catch (IOException e) {
            logger.severe("Помилка збереження у файл: " + e.getMessage());
            EmailService.sendCriticalEmail("Помилка збереження в файл", e);
        }
    }

    public void loadFromFile(String filePath) {
        equipmentList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    double weight = Double.parseDouble(parts[1]);
                    double price = Double.parseDouble(parts[2]);
                    equipmentList.add(new Equipment(name, weight, price));
                }
            }
            logger.info("Екіпірування завантажено з файлу: " + filePath);
        } catch (IOException e) {
            logger.severe("Помилка завантаження з файлу: " + e.getMessage());
            EmailService.sendCriticalEmail("Помилка завантаження з файлу", e);
        }
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }
}
