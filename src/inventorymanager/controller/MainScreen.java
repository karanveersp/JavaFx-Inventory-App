package inventorymanager.controller;

import inventorymanager.Util;
import inventorymanager.model.Part;
import inventorymanager.model.Product;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreen implements Initializable {

    public Button exitBtn;

    // part buttons
    public Button addPartBtn;
    public Button deletePartBtn;
    public Button modifyPartBtn;
    public Button searchPartsBtn;

    public TextField searchPartsField;

    // product buttons
    public Button addProductBtn;
    public Button deleteProductBtn;
    public Button modifyProductBtn;
    public Button searchProductsBtn;

    public TextField searchProductsField;

    // table views
    public TableView<Part> partsTableView;
    public TableView<Product> productsTableView;

    // parts table cols
    public TableColumn<Part, Integer> partIdCol;
    public TableColumn<Part, String> partNameCol;
    public TableColumn<Part, Integer> partStockCol;
    public TableColumn<Part, String> partPriceCol;

    // products table cols
    public TableColumn<Product, Integer> productIdCol;
    public TableColumn<Product, String> productNameCol;
    public TableColumn<Product, Integer> productStockCol;
    public TableColumn<Product, String> productPriceCol;

    public void onSearchPart() {
        String partName = searchPartsField.getText();
        if (partName == null || partName.length() == 0) {
            // initialize with full inventory
            initializePartsTableView(Util.getInventory().getAllParts());
            return;
        }

        // else filtered list
        ObservableList<Part> filtered = Util.getInventory().lookupPart(partName);
        initializePartsTableView(filtered);
    }

    public void onSearchProduct() {
        String productName = searchProductsField.getText();
        if (productName == null || productName.length() == 0) {
            // initialize with full inventory
            initializeProductsTableView(Util.getInventory().getAllProducts());
            return;
        }

        // else filtered list
        ObservableList<Product> filtered = Util.getInventory().lookupProduct(productName);
        initializeProductsTableView(filtered);
    }


    private void initializePartsTableView(ObservableList<Part> parts) {
        partsTableView.getItems().clear();
        partsTableView.getItems().addAll(parts);

        TableView.TableViewSelectionModel<Part> selectedPart = partsTableView.getSelectionModel();
        selectedPart
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> Util.setSelectedPart(newValue));


    }

    private void initializeProductsTableCols() {
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("priceDisplay"));
    }

    private void initializePartsTableCols() {
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("priceDisplay"));
    }

    private void initializeProductsTableView(ObservableList<Product> products) {
        productsTableView.getItems().clear();
        productsTableView.getItems().addAll(products);

        TableView.TableViewSelectionModel<Product> selectedProduct = productsTableView.getSelectionModel();
        selectedProduct
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> Util.setSelectedProduct(newValue));
    }

    public void onAddPart() throws IOException {
        Util.changeScreen(Util.getStageFromButton(addPartBtn), Util.ADD_PART_FXML);
    }

    public void onDeletePart() {
        Part selected = Util.getSelectedPart();
        if (selected == null) {
            selected = partsTableView.getSelectionModel().getSelectedItem();
        }

        if (selected == null || partsTableView.getItems().size() == 0) {
            Util.validationDialog("No part selected");
            return;
        }

        if (Util.confirmDialog()) {
            if (partsTableView.getItems().size() == 1) {
                selected = partsTableView.getItems().get(0);
            }
            Util.getInventory().deletePart(selected);
            partsTableView.getItems().remove(selected);
            Util.setSelectedPart(null);
        }
    }
    public void onModifyPart() throws IOException {
        Part selected = Util.getSelectedPart();

        if (selected == null) {
            selected = partsTableView.getSelectionModel().getSelectedItem();
        }

        if (selected == null || partsTableView.getItems().size() == 0) {
            Util.validationDialog("No selected part to modify");
            return;
        }

        if (partsTableView.getItems().size() == 1) {
            Util.setSelectedPart(partsTableView.getItems().get(0)); // sole item is selected
        }
        Util.changeScreen(Util.getStageFromButton(modifyPartBtn), Util.MODIFY_PART_FXML);
    }

    public void onAddProduct() throws IOException {
        Util.changeScreen(Util.getStageFromButton(addProductBtn), Util.ADD_PRODUCT_FXML);
    }

    public void onDeleteProduct() {
        Product selected = Util.getSelectedProduct();
        if (selected == null) {
            selected = productsTableView.getSelectionModel().getSelectedItem();
        }

        if (selected == null || productsTableView.getItems().size() == 0) {
            Util.validationDialog("No product selected");
            return;
        }

        if (Util.confirmDialog()) {
            if (productsTableView.getItems().size() == 1) {
                selected = productsTableView.getItems().get(0);
            }
            Util.getInventory().deleteProduct(selected);
            productsTableView.getItems().remove(selected);
            Util.setSelectedProduct(null);
        }
    }

    public void onModifyProduct() throws IOException {
        Product selected = Util.getSelectedProduct();

        if (selected == null) {
            selected = productsTableView.getSelectionModel().getSelectedItem();
        }

        if (selected == null || productsTableView.getItems().size() == 0) {
            Util.validationDialog("No selected product to modify");
            return;
        }

        if (productsTableView.getItems().size() == 1) {
            Util.setSelectedProduct(productsTableView.getItems().get(0)); // sole item is selected
        }
        Util.changeScreen(Util.getStageFromButton(modifyProductBtn), Util.MODIFY_PRODUCT_FXML);
    }


    public void onExit() {
        Util.getStageFromButton(exitBtn).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing mainScreen controller");
        initializePartsTableCols();
        initializeProductsTableCols();
        initializePartsTableView(Util.getInventory().getAllParts());
        initializeProductsTableView(Util.getInventory().getAllProducts());
    }
}
