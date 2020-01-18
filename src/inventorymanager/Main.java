package inventorymanager;

import inventorymanager.model.Inventory;
import inventorymanager.model.Part;
import inventorymanager.model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    // the following static fields are defined on this class since its state
    // persists beyond view changes.

    // inventory reference
    public final static Inventory inventory = new Inventory();

    // selected part/product references
    public static Part selectedPart;
    public static Product selectedProduct;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(Util.MAIN_SCREEN_FXML));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
