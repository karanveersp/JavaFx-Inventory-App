package inventorymanager.controller;

import inventorymanager.Util;
import inventorymanager.model.Part;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPart extends PartController implements Initializable {

    public void saveBtnAction() throws IOException {
        try {
            validateFields();
        } catch (Exception e) {
            // display alert and allow user to try again
            Util.validationDialog(e.getMessage());
            return;
        }
        // generate new id
        int partId = Util.getInventory().nextPartId();

        // create new part
        Part newPart = getNewPartFromFields(partId);

        // add
        Util.getInventory().addPart(newPart);

        System.out.println("Part added successfully");
        Util.changeScreen(Util.getStageFromButton(saveBtn), Util.MAIN_SCREEN_FXML);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing addPart controller");
        if (inHouseRadio.selectedProperty().get()) {
            setInHouse();
        }
        else {
            setOutsourced();
        }
    }
}
