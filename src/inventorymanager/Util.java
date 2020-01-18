package inventorymanager;

import inventorymanager.model.Inventory;
import inventorymanager.model.Part;
import inventorymanager.model.Product;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Contains shared helper functions and constants
 */
public class Util {
    public final static String MAIN_SCREEN_FXML = "view/mainScreen.fxml";
    public final static String ADD_PART_FXML = "view/addPart.fxml";
    public static final String MODIFY_PART_FXML = "view/modifyPart.fxml";
    public static final String ADD_PRODUCT_FXML = "view/addProduct.fxml";
    public static final String MODIFY_PRODUCT_FXML = "view/modifyProduct.fxml";

    public static Inventory getInventory() {
        return Main.inventory;
    }

    public static Part getSelectedPart() {
        return Main.selectedPart;
    }

    public static Product getSelectedProduct() {
        return Main.selectedProduct;
    }

    public static void setSelectedPart(Part part) {
        Main.selectedPart = part;
    }

    public static void setSelectedProduct(Product product) {
        Main.selectedProduct = product;
    }

    /**
     * validationDialog displays a validation window with a message
     * @param message String
     */
    public static void validationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(message);
        alert.setContentText("Please try again.");
        alert.showAndWait();
    }

    /**
     * changeScreen loads the given fxml file for the given stage
     * @param stage Stage
     * @param screenFxml String
     * @throws IOException If load fails
     */
    public static void changeScreen(Stage stage, String screenFxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(screenFxml));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    /**
     * getStageFromButton is a helper function to retrieve
     * the stage object from a button reference
     * @param btn Button
     * @return Stage
     */
    public static Stage getStageFromButton(Button btn) {
        return (Stage) btn.getScene().getWindow();
    }

    /**
     * confirmDialog returns true or false based on
     * whether the user clicks ok or cancel, respectively.
     * @return true or false
     */
    public static boolean confirmDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
