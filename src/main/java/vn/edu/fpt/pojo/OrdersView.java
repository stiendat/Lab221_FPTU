package vn.edu.fpt.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class OrdersView implements Serializable {
    private final static long serialVersionUID = 1L;
    private static OrdersView instance;

    private ArrayList<Order> orders;

    private OrdersView() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public static OrdersView getInstance() {
        if (instance == null) {
            instance = new OrdersView();
        }
        return instance;
    }

    public void print() {
        for (Order order : this.orders) {
            System.out.println();
            order.print();
            System.out.println();
        }
    }
}
