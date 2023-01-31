package vn.edu.fpt.pojo;

import vn.edu.fpt.utils.ConsoleUtils;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

public class ShoppingCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    private static ShoppingCategory instance;

    private Hashtable<Integer, Fruit> map;

    private ShoppingCategory() {
        this.map = new Hashtable<>();
    }

    public void addFruit(Fruit fruit) {
        this.map.put(fruit.getId(), fruit);
    }

    public Boolean checkIdExists(Integer id) {
        if (this.map.containsKey(id)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Fruit getFruitById(Integer id) {
        return this.map.get(id);
    }

    public void updateFruit(Integer id, Integer quantity) {
        Fruit _fruit = this.map.get(id);
        _fruit.setQuantity(quantity);
        this.addFruit(_fruit);
    }

    public void updateInventory(Order order) {
        Hashtable<Fruit, Integer> cart = order.getCart();
        for (Map.Entry<Fruit, Integer> entry : cart.entrySet()) {
            this.updateFruit(entry.getKey().getId(), entry.getValue());
        }
    }

    public static ShoppingCategory getInstance() {
        if (instance == null) {
            instance = new ShoppingCategory();
        }
        return instance;
    }

    public void print() {
        ConsoleUtils.clearConsole();

        String[] headers = {"| ++ Item ++ |", "| ++ Fruit Name ++ |", "| ++ Origin ++ |", "| ++ Price ++ |"};
        int[] columnWidths = {0, 0, 0, 0};
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = headers[i].length();
        }

        for (int i = 0; i < headers.length; i++) {
            System.out.printf("%-" + (columnWidths[i] + 2) + "s ", headers[i]);
        }
        System.out.println();

        for (Map.Entry<Integer, Fruit> entry : this.map.entrySet()) {
            System.out.printf("%-" + (columnWidths[0] + 2) + "s %-" + (columnWidths[1] + 2) +"s %-" + (columnWidths[2] + 2) +"s %-" + (columnWidths[3] + 2) +"s\n", entry.getKey(), entry.getValue().getName(), entry.getValue().getOrigin(), entry.getValue().getPrice());
        }

    }
}
