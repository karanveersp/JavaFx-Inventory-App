package inventorymanager.controller;

import inventorymanager.Util;
import inventorymanager.model.Part;
import inventorymanager.model.Product;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public abstract class ProductController {
    public TextField idField;
    public TextField productNameField;
    public TextField invField;
    public TextField priceField;
    public TextField maxField;
    public TextField minField;

    public Button searchPartBtn;
    public TextField searchPartField;

    // all parts table
    public TableView<Part> allPartsTableView;
    public TableColumn<Part, Integer> partIdCol;
    public TableColumn<Part, String> partNameCol;
    public TableColumn<Part, Integer> partStockCol;
    public TableColumn<Part, Double> partPriceCol;

    public Button addPartBtn;

    // prod parts table
    public TableView<Part> prodPartsTableView;
    public TableColumn<Part, Integer> prodPartIdCol;
    public TableColumn<Part, String> prodPartNameCol;
    public TableColumn<Part, Integer> prodPartStockCol;
    public TableColumn<Part, Double> prodPartPriceCol;

    public Button deletePartBtn;

    public Button saveBtn;
    public Button cancelBtn;

    public void onCancel() throws IOException {
        boolean isConfirmed = Util.confirmDialog();
        if (isConfirmed) {
            Util.changeScreen(Util.getStageFromButton(cancelBtn), Util.MAIN_SCREEN_FXML);
        }
    }

    public abstract void onSave() throws IOException;


    public void onSearchPart() {
        String partName = searchPartField.getText();
        if (partName == null || partName.length() == 0) {
            // initialize with all parts
            initializeAllPartsTableView(Util.getInventory().getAllParts());
            return;
        }

        // else filtered list
        ObservableList<Part> filtered = Util.getInventory().lookupPart(partName);
        initializeAllPartsTableView(filtered);
    }


    protected void initializeAllPartsTableView(ObservableList<Part> parts) {
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("priceDisplay"));

        allPartsTableView.getItems().clear();
        allPartsTableView.getItems().addAll(parts);
    }

    public void onAddPart() {
        if (allPartsTableView.getItems().size() == 0) {
            Util.validationDialog("No part selected");
            return;
        }
        prodPartsTableView.getItems().add(allPartsTableView.getSelectionModel().getSelectedItem());
        prodPartsTableView.refresh();
    }

    public void onDeletePart() {
        if (prodPartsTableView.getItems().size() == 0) {
            Util.validationDialog("No part selected");
            return;
        }
        if (Util.confirmDialog()) {
            int selected = prodPartsTableView.getSelectionModel().getSelectedIndex();
            prodPartsTableView.getItems().remove(selected);
            prodPartsTableView.refresh();
        }
    }

    protected void initializeProdPartsTableView() {
        prodPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("priceDisplay"));

        prodPartsTableView.getItems().clear();

    }


    protected void validateFields() throws Exception {
        if (productNameField.getText().isEmpty()
                || invField.getText().isEmpty()
                || priceField.getText().isEmpty()
                || minField.getText().isEmpty()
                || maxField.getText().isEmpty()
        ) {
            throw new Exception("Name, Inv, Price, Min and Max must have values");
        }
        int min, max;
        try {
            min = Integer.parseInt(minField.getText());
            max = Integer.parseInt(maxField.getText());
        } catch (NumberFormatException e) {
            throw new Exception("Min and Max values must be valid integers");
        }
        if (min > max) {
            throw new Exception("Min cannot be greater than Max");
        }
    }

    protected Product getNewProductFromFields(int productId) {
        return new Product(
                    productId,
                    productNameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(invField.getText()),
                    Integer.parseInt(minField.getText()),
                    Integer.parseInt(maxField.getText())
        );
    }

}
