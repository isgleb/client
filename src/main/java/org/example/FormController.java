package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.example.Controllers.HttpController;

public class FormController implements Initializable {

    private Long orderId;

    private Payment payment;
    private Map<String, Integer> expenses = new HashMap<>();

    @FXML
    private TextField clientId;
    @FXML
    private TextField ownersName;
    @FXML
    private TextField address;
    @FXML
    private TextField sum;

    @FXML
    private TextField coldWater;
    @FXML
    private TextField hotWater;
    @FXML
    private TextField electricity;
    @FXML
    private TextField repairment;


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
//
//        payment = HttpController.savePayment(payment);
//        expenses.forEach(expense -> expense.setPaymentId(payment.getId()));
//        HttpController.saveExpenses(expenses);



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Long id = 1L;

        try {
            payment = HttpController.getPayment(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        clientId.setText(String.valueOf(payment.getClientId()));
        ownersName.setText(payment.getOwnerName());
        address.setText(payment.getAddress());

        int totalSum = 0;
        for (Expense anExpense : payment.getExpenses()) {
            expenses.put(anExpense.getName(), anExpense.getAmount());
            totalSum += anExpense.amount;
        }

        sum.setText(String.valueOf(totalSum));

        coldWater.setText(String.valueOf(expenses.get("cold water")));
        hotWater.setText(String.valueOf(expenses.get("hot water")));
        electricity.setText(String.valueOf(expenses.get("electricity")));
        repairment.setText(String.valueOf(expenses.get("repairment")));

    }
}
