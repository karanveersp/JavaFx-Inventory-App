package inventorymanager.controller;

import inventorymanager.Util;
import inventorymanager.model.Part;
import inventorymanager.model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProduct extends ProductController implements Initializable {


    @Override
    public void onSave() throws IOException {
        try {
            validateFields();
        } catch (Exception e) {
            // display alert and allow user to try again
            Util.validationDialog(e.getMessage());
            return;
        }
        // generate new id
        int productId = Util.getInventory().nextProductId();

        // create new product
        Product newProduct = getNewProductFromFields(productId);
        prodPartsTableView.getItems().forEach(newProduct::addAssociatedPart);

        // add
        Util.getInventory().addProduct(newProduct);

        System.out.println("Part added successfully");
        Util.changeScreen(Util.getStageFromButton(saveBtn), Util.MAIN_SCREEN_FXML);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialize all parts table
        initializeAllPartsTableView(Util.getInventory().getAllParts());
        initializeProdPartsTableView();
    }
}
