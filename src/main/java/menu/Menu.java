package menu;

import command.*;
import model.Knight;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Map<Integer, Command> commands;
    public Knight knight = new Knight();
    private Scanner scanner = new Scanner(System.in);

    public Menu() {
        commands = new HashMap<>();
        initializeCommands();
    }

    private void initializeCommands() {
        addCommand(1, new AddEquipmentCommand(knight, scanner));
        addCommand(2, new DisplayEquipmentCommand(knight));
        addCommand(3, new ReplaceEquipmentCommand(knight, scanner));
        addCommand(4, new RemoveEquipmentCommand(knight, scanner));
        addCommand(5, new CalculateTotalPriceCommand(knight));
        addCommand(6, new SortEquipmentByWeightCommand(knight));
        addCommand(7, new FindEquipmentByPriceRangeCommand(knight, scanner));
        addCommand(8, new SaveToFileCommand(knight, scanner));
        addCommand(9, new LoadFromFileCommand(knight, scanner));
    }

    public void addCommand(int option, Command command) {
        commands.put(option, command);
    }

    public void executeCommand(int option) {
        Command command = commands.get(option);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Невірний варіант. Спробуйте знову.");
        }
    }

    public void displayMenu() {
        System.out.println("\n=== Меню екіпірування лицаря ===");
        for (Map.Entry<Integer, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().info());
        }
        System.out.println("0. Вийти");
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Виберіть пункт: ");
            int choice = scanner.nextInt();
            if (choice == 0) {
                System.out.println("Вихід...");
                break;
            }
            executeCommand(choice);
        }
    }
}
