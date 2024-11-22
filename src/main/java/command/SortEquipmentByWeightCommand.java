package command;

import model.Knight;

public class SortEquipmentByWeightCommand implements Command {
    private final Knight knight;

    public SortEquipmentByWeightCommand(Knight knight) {
        this.knight = knight;
    }

    @Override
    public void execute() {
        knight.sortEquipmentByWeight();
        System.out.println("Екіпіровка відсортована.");
    }
    @Override
    public String info() {
        return "Посортувати екіпірування за вагою.";
    }
}
