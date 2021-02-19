package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.httpClient.WebClient;

public class PrimaryController implements Initializable {

    @FXML private TableView<PaymentRow> theTable;
    @FXML private TableColumn<PaymentRow, Long> clientIdColumn;
    @FXML private TableColumn<PaymentRow, String> ownerColumn;
    @FXML private TableColumn<PaymentRow, String> addressColumn;
    @FXML private TableColumn<PaymentRow, Integer> amountColumn;
    @FXML private TableColumn<PaymentRow, Date> periodColumn;


    @FXML
    private void deleteThePayment() throws IOException {

//        Optional<PaymentRow> selectedRow = Optional.ofNullable(theTable.getSelectionModel().getSelectedItem());
////        selectedRow.ifPresent(row -> transferToPayment(row.getId()));

        WebClient.connect();
    }


    @FXML
    private void switchToThePayment() {

        Optional<PaymentRow> selectedRow = Optional.ofNullable(theTable.getSelectionModel().getSelectedItem());
        selectedRow.ifPresent(payment -> transferToPayment(payment.getId()));
    }


    @FXML
    private void switchToNewPayment() throws IOException {

        transferToPayment(-1L);
    }


    @FXML
    private void request(){

    }

//    @FXML
//    private void initialize(){
////        App.setRoot("primary");
//
////        WebClient.connect();
//
//
//        clientIdColumn.setCellValueFactory(new PropertyValueFactory<PaymentDto, Long>("id"));
//        ownerColumn.setCellValueFactory(new PropertyValueFactory<PaymentDto, String>("name"));
//        addressColumn.setCellValueFactory(new PropertyValueFactory<PaymentDto, String>("address"));
//        amountColumn.setCellValueFactory(new PropertyValueFactory<PaymentDto, Integer>("amount"));
//        periodColumn.setCellValueFactory(new PropertyValueFactory<PaymentDto, Date>("period"));
//
//        theTable.setItems(getPayments());
//
//    }

    private ObservableList<PaymentRow> getPayments() {

        ObservableList<PaymentRow> observableList = FXCollections.observableArrayList();

        Date date = new Date();
        observableList.add(new PaymentRow(1L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(2L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(3L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(4L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(5L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(6L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(7L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(8L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(9L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(1L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(2L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(3L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(4L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(5L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(6L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(7L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(8L,"Pete", "arbat", 2000, date));
        observableList.add(new PaymentRow(9L,"Pete", "arbat", 2000, date));


        return observableList;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<PaymentRow, Long>("id"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<PaymentRow, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<PaymentRow, String>("address"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<PaymentRow, Integer>("amount"));
        periodColumn.setCellValueFactory(new PropertyValueFactory<PaymentRow, Date>("period"));

        theTable.setItems(getPayments());
    }


    public void transferToPayment(Long id) {

        Parent root = null;

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/form.fxml"));
            root = loader.load();

            FormController formController = loader.getController();
            formController.transferId(id);

            App.setRoot("form");

        } catch (IOException ex) {
            System.err.println(ex);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Error");
            stage.show();
        }
    }
}