package inventorymanager.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * nextPartId generates a new id for parts
     * @return int
     */
    public int nextPartId() {
        return allParts.size() + 1;
    }

    /**
     * nextProductId generates a new id for products
     * @return int
     */
    public int nextProductId() {
        return allProducts.size() + 1;
    }

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public Part lookupPart(int partId) {
        return allParts.stream().filter(p -> p.getId() == partId).findFirst().orElse(null);
    }

    public Product lookupProduct(int productId) {
        return allProducts.stream().filter(p -> p.getId() == productId).findFirst().orElse(null);
    }

    public ObservableList<Part> lookupPart(String partName) {
        return allParts.filtered(p -> p.getName().equals(partName));
    }

    public ObservableList<Product> lookupProduct(String productName) {
        return allProducts.filtered(p -> p.getName().equals(productName));
    }

    public void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    public boolean deletePart(Part selectedPart) {
        return allParts.removeIf(p -> p.equals(selectedPart));
    }

    public boolean deleteProduct(Product selectedProduct) {
        return allProducts.removeIf(p -> p.equals(selectedProduct));
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }


}
