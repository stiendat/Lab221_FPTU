package vn.edu.fpt.services;

import vn.edu.fpt.pojo.Fruit;
import vn.edu.fpt.pojo.Order;
import vn.edu.fpt.pojo.OrdersView;
import vn.edu.fpt.pojo.ShoppingCategory;
import vn.edu.fpt.utils.ConsoleUtils;

import java.util.Scanner;

public class ConsoleApplication {
    private static Scanner scanner = new Scanner(System.in);

    public static void loadTestData() {
        ShoppingCategory shoppingCategory = ShoppingCategory.getInstance();
        shoppingCategory.addFruit(new Fruit.Builder(1).name("Test1")
                .origin("VN")
                .quantity(12)
                .price(23000)
                .build());
        shoppingCategory.addFruit(new Fruit.Builder(2).name("Test2")
                .origin("VNA")
                .quantity(2)
                .price(999000)
                .build());
    }

    public static void mainMenu() {
        ConsoleUtils.clearConsole();

        System.out.println("Please choose from the menu below:\n");
        System.out.println("1. Create fruit");
        System.out.println("2. Update fruit");
        System.out.println("3. View orders");
        System.out.println("4. Shopping");
        System.out.println("5. Exit\n\n");

        Integer choose = ConsoleUtils.promChoose(5);

        switch (choose) {
            case 1:
                createFruit();
                break;
            case 2:
                updateFruit();
                break;
            case 3:
                viewOrders();
                break;
            case 4:
                shopping();
                break;
            case 5:
                return;
        }
    }

    public static void createFruit() {
        ConsoleUtils.clearConsole();
        ShoppingCategory inventory = ShoppingCategory.getInstance();


        System.out.println("Adding new fruit");
        System.out.print("Fruit id: ");
        Integer id = Integer.valueOf(ConsoleUtils.promNumber());

        if (inventory.checkIdExists(id)) {
            System.out.println("This id already exists. Press Y to retry or N to back to main menu");
            if (ConsoleUtils.promYesNo()) {
                createFruit();
            } else {
                mainMenu();
            }
        }

        System.out.print("\nFruit name: ");
        String name = ConsoleUtils.promInput();
        System.out.print("\nFruit price: ");
        Double price = Double.valueOf(ConsoleUtils.promNumber());
        System.out.print("\nQuantity: ");
        Integer quantity = Integer.valueOf(ConsoleUtils.promNumber());
        System.out.print("\nOrigin: ");
        String origin = ConsoleUtils.promInput();

        Fruit fruit = new Fruit.Builder(id).name(name)
                .price(price)
                .quantity(quantity)
                .origin(origin)
                .build();

        inventory.addFruit(fruit);

        mainMenu();
    }

    public static void updateFruit() {
        ConsoleUtils.clearConsole();
        ShoppingCategory inventory = ShoppingCategory.getInstance();

        System.out.println("Inventory listing");
        inventory.print();

        System.out.print("Choose fruit to update: ");
        Integer id = Integer.valueOf(ConsoleUtils.promNumber());
        if (!inventory.checkIdExists(id)) {
            System.out.println("Fruit not exists. Press Y to create new fruit, or press N to back to main menu");
            if (ConsoleUtils.promYesNo()) {
                createFruit();
            } else {
                mainMenu();
            }
        }
        System.out.print("\nInput new quantity: ");
        Integer quantity = Integer.valueOf(ConsoleUtils.promNumber());
        inventory.updateFruit(id, quantity);

        mainMenu();
    }

    public static void viewOrders() {
        ConsoleUtils.clearConsole();

        System.out.println("Order list: \n\n");

        OrdersView orders = OrdersView.getInstance();
        orders.print();

        System.out.println("\nPress Enter to continue");
        scanner.nextLine();
        mainMenu();
    }

    public static Order updateOrder(Order order) {
        ShoppingCategory shoppingCategory = ShoppingCategory.getInstance();
        System.out.print("\n\nChoose fruit you want to order: ");
        Integer id = Integer.valueOf(ConsoleUtils.promNumber());
        if (!shoppingCategory.checkIdExists(id)) {
            System.out.println("There is no fruit with id " + id + ". Press Y to order again or N to back to main menu.");
            if (ConsoleUtils.promYesNo()) {
                updateOrder(order);
            } else {
                mainMenu();
            }
        }
        Fruit fruit = shoppingCategory.getFruitById(id);
        System.out.println("You choose: " + fruit.getName());
        System.out.print("Please input quantity: ");
        Integer quantity = Integer.valueOf(ConsoleUtils.promNumber());
        if (quantity.compareTo(fruit.getQuantity()) > 0) {
            System.out.print("\nThere is not enough quantity available for this fruit. Press Y to order again or N to back to main menu.");
            if (ConsoleUtils.promYesNo()) {
                updateOrder(order);
            } else {
                mainMenu();
            }
        } else {
            order.addMerchandise(fruit, quantity);
        }
        return order;
    }

    public static void shopping() {
        ConsoleUtils.clearConsole();

        Order cart = new Order("");

        ShoppingCategory shoppingCategory = ShoppingCategory.getInstance();
        System.out.println("\nFruit list: ");
        shoppingCategory.print();

        Boolean CONTINUE_ORDER = Boolean.TRUE;
        while (CONTINUE_ORDER) {
            updateOrder(cart);
            System.out.print("\nPress Y to finish order. N to order more.");
            if (ConsoleUtils.promYesNo()) {
                CONTINUE_ORDER = Boolean.FALSE;
                cart.printCart();
            } else {
                ConsoleUtils.clearConsole();
                shoppingCategory.print();
            }
        }

        System.out.print("Input customer name: ");
        String customerName = ConsoleUtils.promInput();
        cart.setCustomerName(customerName);
        OrdersView orders = OrdersView.getInstance();
        orders.addOrder(cart);

        ShoppingCategory inventory = ShoppingCategory.getInstance();
        inventory.updateInventory(cart);

        mainMenu();
    }
}
