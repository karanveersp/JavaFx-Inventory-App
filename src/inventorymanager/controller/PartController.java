package inventorymanager.controller;


import inventorymanager.Util;
import inventorymanager.model.InHouse;
import inventorymanager.model.Inventory;
import inventorymanager.model.Outsourced;
import inventorymanager.model.Part;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Parent class for Add/Modify part created to reduce code duplication.
 * This class contains all members common to both add/modify part controllers.
 */
public abstract class PartController {

    public TextField idField;
    public TextField partNameField;
    public TextField invField;
    public TextField priceOrCostField;
    public TextField minField;
    public TextField maxField;

    public Label machineIdLabel;
    public TextField machineIdField;
    public Label companyNameLabel;
    public TextField companyNameField;
    public RadioButton inHouseRadio;
    public RadioButton outsourcedRadio;

    public Button saveBtn;
    public Button cancelBtn;
    public ToggleGroup partRadioGroup;

    // add/modify must override this method
    public abstract void saveBtnAction() throws IOException;

    // following are the shared methods for add/modify part

    protected void validateFields() throws Exception {
        if (partNameField.getText().isEmpty()
                || invField.getText().isEmpty()
                || priceOrCostField.getText().isEmpty()
                || minField.getText().isEmpty()
                || maxField.getText().isEmpty()
        ) {
            throw new Exception("Part Name, Inv, Price, Min and Max must have values");
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
        if (inHouseRadio.isSelected() && machineIdField.getText().isEmpty()) {
            throw new Exception("Machine ID value is required for In-House option");
        }
        if (!inHouseRadio.isSelected() && companyNameField.getText().isEmpty()) {
            throw new Exception("Company Name is required for Outsourced option");
        }
    }

    public void setInHouse() {
        companyNameField.setVisible(false);
        companyNameLabel.setVisible(false);
        machineIdLabel.setVisible(true);
        machineIdField.setVisible(true);
    }

    public void setOutsourced() {
        machineIdField.setVisible(false);
        machineIdLabel.setVisible(false);
        companyNameField.setVisible(true);
        companyNameLabel.setVisible(true);
    }

    protected Part getNewPartFromFields(int partId) {
        String price = priceOrCostField.getText();
        if (price.contains("$")) {
            price = price.substring(1);
        }
        if (inHouseRadio.isSelected()) {
            return new InHouse(
                    partId,
                    partNameField.getText(),
                    Double.parseDouble(price),
                    Integer.parseInt(invField.getText()),
                    Integer.parseInt(minField.getText()),
                    Integer.parseInt(maxField.getText()),
                    Integer.parseInt(machineIdField.getText())
            );
        }
        return new Outsourced(
                    partId,
                    partNameField.getText(),
                    Double.parseDouble(price),
                    Integer.parseInt(invField.getText()),
                    Integer.parseInt(minField.getText()),
                    Integer.parseInt(maxField.getText()),
                    companyNameField.getText()
            );
    }

    public void cancelBtnAction() throws IOException {
        boolean isConfirmed = Util.confirmDialog();
        if (isConfirmed) {
            Util.changeScreen(Util.getStageFromButton(cancelBtn), Util.MAIN_SCREEN_FXML);
        }
    }
}
