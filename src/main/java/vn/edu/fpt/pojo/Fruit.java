package vn.edu.fpt.pojo;

import java.io.Serializable;

public class Fruit implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Integer id;
    private String name;
    private Double price;
    private Integer quantity;
    private String origin;

    public Fruit(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.origin = builder.origin;
    }

    public static class Builder {

        private final Integer id;
        private String name;
        private Double price;
        private Integer quantity;
        private String origin;

        public Builder(Integer id) {
            this.id = id;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder price(Integer price) {
            this.price = price.doubleValue();
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder origin(String origin) {
            this.origin = origin;
            return this;
        }

        public Fruit build() {
            if (this.origin == null) {
                this.origin = "Unknown";
            }
            return new Fruit(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", origin='" + origin + '\'' +
                '}';
    }
}
