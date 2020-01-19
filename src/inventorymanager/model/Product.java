package inventorymanager.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;


public class Product {
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = FXCollections.observableArrayList();
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedAspart) {
        return associatedParts.removeIf(p -> p.getId() == selectedAspart.getId());
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceDisplay() {
        return "$" + String.valueOf(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // overloaded
    public void setPrice(int max) {
        this.price = max;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                stock == product.stock &&
                min == product.min &&
                max == product.max &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, stock, min, max);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
