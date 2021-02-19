package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FormController {

    private Long orderId;

    private PaymentDetails paymentDetails;

    @FXML private TextField clientId;
    @FXML private TextField ownersName;
    @FXML private TextField address;
    @FXML private TextField sum;

    @FXML private TextField coldWater;
    @FXML private TextField hotWater;
    @FXML private TextField electricity;
    @FXML private TextField repairment;




    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    public void transferId(Long id) {

        if (id >= 0) {
            orderId = id;
//            getPaymentDetails(Long id);
        } else {
            orderId = null;
        }

        System.out.println(orderId);
    }

    @FXML
    private void saveChanges() {

        clientId.setText("0");
        ownersName.setText("0");
        address.setText("0");
        sum.setText("0");

        coldWater.setText("0");
        hotWater.setText("0");
        electricity.setText("0");
        repairment.setText("no");

    }

}
