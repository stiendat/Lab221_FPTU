package vn.edu.fpt.pojo;

import vn.edu.fpt.pojo.vo.OrderVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Order implements Serializable {
    public final static long serialVersionUID = 1L;

    private String customerName;
    
    private Hashtable<Fruit, Integer> cart;

    public Order(String customerName) {
        this.customerName = customerName;
        this.cart = new Hashtable<>();
    }

    public void addMerchandise(Fruit fruit, Integer quantity) {
        this.cart.put(fruit, quantity);
    }

    public static Double calcPrice(Fruit fruit, Integer quantity) {
        return fruit.getPrice() * quantity.doubleValue();
    }

    public Double calcTotalPrice() {
        Double totalPrice = 0.0;
        for (Map.Entry<Fruit, Integer> entry : this.cart.entrySet()) {
            totalPrice = totalPrice + calcPrice(entry.getKey(), entry.getValue());
        }
        return totalPrice;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Hashtable<Fruit, Integer> getCart() {
        return this.cart;
    }

    static void insertionSort(OrderVO arr[], Integer arrLen) {
        if (arrLen <= 1)                             
        {
            return;
        }

        insertionSort( arr, arrLen-1 );        

        OrderVO last = arr[arrLen-1];                        
        int j = arrLen-2;                                

        while (j >= 0 && arr[j].getQuantity() > last.getQuantity())                 
        {
            arr[j+1] = arr[j];                          
            j--;
        }
        arr[j+1] = last;
    }

    public void printCart() {

        ArrayList<OrderVO> quantityList = new ArrayList<>();
        for (Map.Entry<Fruit, Integer> entry : this.cart.entrySet()) {
            quantityList.add(new OrderVO(entry.getKey(), entry.getValue()));
        }
        OrderVO[] sortedList = quantityList.toArray(new OrderVO[0]);
        insertionSort(sortedList, sortedList.length);

        String[] headers = {"| ++ Product ++ |", "| ++ Quantity ++ |", "| ++ Price ++ |", "| ++ Amount ++ |"};
        int[] columnWidths = {0, 0, 0, 0};
        for (int i = 0; i< headers.length; i++) {
            columnWidths[i] = headers[i].length();
        }

        for (int i = 0; i < headers.length; i++) {
            System.out.printf("%-" + (columnWidths[i] + 2) + "s ", headers[i]);
        }
        System.out.println();

        for (OrderVO entry : sortedList) {
            System.out.printf("%-" + (columnWidths[0] + 2) + "s %-" + (columnWidths[1] + 2) + "s %-" + (columnWidths[2] + 2) + "s %-" + (columnWidths[3] + 2) + "s\n", entry.getFruit().getId().toString() + "." + entry.getFruit().getName(), entry.getQuantity().toString(), entry.getFruit().getPrice().toString(), "$" + calcPrice(entry.getFruit(), entry.getQuantity()).toString());
        }

        System.out.println("Total: " + "$" + this.calcTotalPrice());
    }

    public void print() {
        System.out.println("Customer: " + this.customerName);
        this.printCart();
    }
}
