package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.example.Controllers.HttpController;

public class FormController implements Initializable {

    private Long orderId;

    private Payment payment;
    private List<Expense> expenses;

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

        payment = HttpController.savePayment(payment);
        expenses.forEach(expense -> expense.setPaymentId(payment.getId()));
        HttpController.saveExpenses(expenses);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Long id = 1L;

        try {
            payment = HttpController.getPayment(id);

            clientId.setText(String.valueOf(payment.getClientId()));
            ownersName.setText(payment.getOwnerName());
            address.setText(payment.getAddress());

            HttpController.getExpenses(payment.getId());


        } catch (IOException e) {
            e.printStackTrace();
        }

//        HttpController.getExpenses(id);
    }


}
