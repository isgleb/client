package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class PrimaryController implements Initializable {

    @FXML private TableView<PaymentRow> theTable;
    @FXML private TableColumn<PaymentRow, Long> clientIdColumn;
    @FXML private TableColumn<PaymentRow, String> ownerColumn;
    @FXML private TableColumn<PaymentRow, String> addressColumn;
    @FXML private TableColumn<PaymentRow, Integer> amountColumn;
    @FXML private TableColumn<PaymentRow, Date> periodColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<PaymentRow, Long>("clientId"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<PaymentRow, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<PaymentRow, String>("address"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<PaymentRow, Integer>("amount"));
        periodColumn.setCellValueFactory(new PropertyValueFactory<PaymentRow, Date>("period"));

        try {
            theTable.setItems(HttpController.getPaymentRows());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void deleteThePayment() {

        Optional<PaymentRow> selectedRow = Optional.ofNullable(theTable.getSelectionModel().getSelectedItem());
        selectedRow.ifPresent(row -> {
            try {
                HttpController.deletePayment(row.getId());
                theTable.getItems().remove(row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    private void switchToThePayment() {

        Optional<PaymentRow> selectedRow = Optional.ofNullable(theTable.getSelectionModel().getSelectedItem());
        selectedRow.ifPresent(payment -> {
            try {
                App.setPaymentId(payment.getId());
                App.setRoot("form");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    private void switchToNewPayment() {

        try {
            App.setPaymentId(null);
            App.setRoot("form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}