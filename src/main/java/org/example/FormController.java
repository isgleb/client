package org.example;

import java.io.IOException;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormController {

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/primary.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void setOrderId(Long id) {

        expenses.put("cold water", coldWater);
        expenses.put("hot water", hotWater);
        expenses.put("electricity", electricity);
        expenses.put("repairment", repairment);

        orderId = Optional.ofNullable(id);

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

        if (orderId.isPresent()) {


            System.out.println("change existed");


        } else {
            payment = new Payment();

            payment.setClientId(Integer.parseInt(clientId.getText()));
            payment.setAddress(address.getText());
            payment.setOwnerName(ownersName.getText());
//        payment.setPeriod(new Date());
            payment.setExpenses(new ArrayList<Expense>());

            for (Map.Entry<String, TextField> pair : expenses.entrySet()) {
                String name = pair.getKey();
                int amount = Integer.parseInt(pair.getValue().getText());
                payment.getExpenses().add(new Expense(name, amount));
            }

            try {
                HttpController.saveNewPayment(payment);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
