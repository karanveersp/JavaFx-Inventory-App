package inventorymanager.controller;

import inventorymanager.Util;
import inventorymanager.model.Part;
import inventorymanager.model.Product;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProduct extends ProductController implements Initializable {

    @Override
    public void onSave() throws IOException {
        try {
            validateFields();
        } catch (Exception e) {
            Util.validationDialog(e.getMessage());
            return;
        }
        // create new product object but with same id as selected product
        Product newProduct = getNewProductFromFields(Util.getSelectedProduct().getId());
        prodPartsTableView.getItems().forEach(newProduct::addAssociatedPart);

        // get index of selected
        int productIndex = Util.getInventory().getAllProducts().indexOf(Util.getSelectedProduct());

        // update the selected part with the new part, preserving the id
        Util.getInventory().updateProduct(productIndex, newProduct);

        System.out.println("Part modified successfully");
        Util.changeScreen(Util.getStageFromButton(saveBtn), Util.MAIN_SCREEN_FXML);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing modifyProduct controller");
        Product selected = Util.getSelectedProduct();
        idField.setText(String.valueOf(selected.getId()));
        productNameField.setText(selected.getName());
        invField.setText(String.valueOf(selected.getStock()));
        priceField.setText(String.valueOf(selected.getPrice()));
        minField.setText(String.valueOf(selected.getMin()));
        maxField.setText(String.valueOf(selected.getMax()));
        initializeProdPartsTableView();
        prodPartsTableView.setItems(selected.getAllAssociatedParts());

        initializeAllPartsTableView(Util.getInventory().getAllParts());
    }
}
