package inventorymanager.controller;

import inventorymanager.Main;
import inventorymanager.Util;
import inventorymanager.model.InHouse;
import inventorymanager.model.Outsourced;
import inventorymanager.model.Part;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ModifyPart extends PartController implements Initializable {

    public void saveBtnAction() throws IOException {
        try {
            validateFields();
        } catch (Exception e) {
            Util.validationDialog(e.getMessage());
            return;
        }
        // create new part object but with same id as selected part
        Part newPart = getNewPartFromFields(Util.getSelectedPart().getId());

        // get index of selected part in inventory
        int partIndex = Util.getInventory().getAllParts().indexOf(Util.getSelectedPart());

        // update the selected part with the new part, preserving the id
        Util.getInventory().updatePart(partIndex, newPart);

        System.out.println("Part modified successfully");
        Util.changeScreen(Util.getStageFromButton(saveBtn), Util.MAIN_SCREEN_FXML);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing modifyPart controller");
        Part selected = Util.getSelectedPart();
        idField.setText(String.valueOf(selected.getId()));
        partNameField.setText(selected.getName());
        invField.setText(String.valueOf(selected.getStock()));
        priceOrCostField.setText(String.valueOf(selected.getPrice()));
        minField.setText(String.valueOf(selected.getMin()));
        maxField.setText(String.valueOf(selected.getMax()));
        if (selected instanceof InHouse) {
            machineIdField.setText(String.valueOf(((InHouse) selected).getMachineId()));
            inHouseRadio.selectedProperty().set(true);
            setInHouse();
        }
        else {
            companyNameField.setText(((Outsourced) selected).getCompanyName());
            outsourcedRadio.selectedProperty().set(true);
            setOutsourced();
        }
    }
}
