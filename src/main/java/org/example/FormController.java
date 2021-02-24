package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormController implements Initializable {

    private Optional<Long> orderId;

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

        App.setRoot("primary");
    }


    public void setOrderId(Long id) {

        expenses.put("cold water", coldWater);
        expenses.put("hot water", hotWater);
        expenses.put("electricity", electricity);
        expenses.put("repairment", repairment);

        orderId = Optional.ofNullable(id);

        payment = new Payment();

        if (orderId.isPresent()) {

            try {
                payment = HttpController.getPayment(orderId.get());
            } catch (IOException e) {
                e.printStackTrace();
            }

            clientId.setText(String.valueOf(payment.getClientId()));
            ownersName.setText(payment.getOwnerName());
            address.setText(payment.getAddress());

            int totalSum = 0;
            for (Expense anExpense : payment.getExpenses()) {
                expenses.get(anExpense.getName()).setText(String.valueOf(anExpense.getAmount()));
                totalSum += anExpense.getAmount();
            }
            sum.setText(String.valueOf(totalSum));
        }
    }


    @FXML
    private void saveChanges() {

        try {
            payment.setClientId(Integer.parseInt(clientId.getText()));
        } catch (NumberFormatException e) {}

        payment.setAddress(address.getText());
        payment.setOwnerName(ownersName.getText());
//        payment.setPeriod(new Date());

        if (orderId.isPresent()) {

            try {
                for (Expense exp : payment.getExpenses()) {
                    try {
                        exp.setAmount(Integer.parseInt(expenses.get(exp.getName()).getText()));
                    } catch (NumberFormatException e) {}
                }

                HttpController.saveChangedPayment(payment);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                payment.setExpenses(new ArrayList<Expense>());
                for (Map.Entry<String, TextField> pair : expenses.entrySet()) {
                    String name = pair.getKey();
                    int amount = Integer.parseInt(pair.getValue().getText());
                    payment.getExpenses().add(new Expense(name, amount));
                }
                HttpController.saveNewPayment(payment);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        expenses.put("cold water", coldWater);
        expenses.put("hot water", hotWater);
        expenses.put("electricity", electricity);
        expenses.put("repairment", repairment);

        orderId = Optional.ofNullable(App.getPaymentId());

        payment = new Payment();

        if (orderId.isPresent()) {

            try {
                payment = HttpController.getPayment(orderId.get());
            } catch (IOException e) {
                e.printStackTrace();
            }

            clientId.setText(String.valueOf(payment.getClientId()));
            ownersName.setText(payment.getOwnerName());
            address.setText(payment.getAddress());

            int totalSum = 0;
            for (Expense anExpense : payment.getExpenses()) {
                expenses.get(anExpense.getName()).setText(String.valueOf(anExpense.getAmount()));
                totalSum += anExpense.getAmount();
            }
            sum.setText(String.valueOf(totalSum));
        }

    }
}
