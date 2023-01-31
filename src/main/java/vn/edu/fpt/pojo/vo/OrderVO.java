package vn.edu.fpt.pojo.vo;

import vn.edu.fpt.pojo.Fruit;

public class OrderVO {
    private Fruit fruit;
    private Integer quantity;

    public OrderVO(Fruit fruit, Integer quantity) {
        this.fruit = fruit;
        this.quantity = quantity;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
