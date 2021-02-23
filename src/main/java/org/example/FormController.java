package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Controllers.HttpController;

public class FormController {

    private Long orderId;

    private Payment payment;
    private Map<String, TextField> expenses = new HashMap<>();

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
//        App.setRoot("primary");


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/primary.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void makeId(Long id) {
        orderId = id;

        try {
            payment = HttpController.getPayment(orderId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(payment);

        clientId.setText(String.valueOf(payment.getClientId()));
        ownersName.setText(payment.getOwnerName());
        address.setText(payment.getAddress());

        expenses.put("cold water", coldWater);
        expenses.put("hot water", hotWater);
        expenses.put("electricity", electricity);
        expenses.put("repairment", repairment);

        int totalSum = 0;
        for (Expense anExpense : payment.getExpenses()) {
            expenses.get(anExpense.getName()).setText(String.valueOf(anExpense.getAmount()));
            totalSum += anExpense.amount;
        }
        sum.setText(String.valueOf(totalSum));
    }


    @FXML
    private void saveChanges() {

        payment.setClientId(Integer.parseInt(clientId.getText()));
        payment.setAddress(address.getText());
        payment.setOwnerName(ownersName.getText());
//        payment.setPeriod(new Date());

        for (Map.Entry<String, TextField> pair : expenses.entrySet()) {
            int amount = Integer.parseInt(pair.getValue().getText());
            for (Expense expense: payment.getExpenses()) {
                expense.setAmount(amount);
            }
        }
        try {

            payment.setId(10L);

            HttpController.savePayment(payment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
