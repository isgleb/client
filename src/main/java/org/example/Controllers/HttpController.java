package org.example.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.Expense;
import org.example.Payment;
import org.example.PaymentRow;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HttpController {

    public static ObservableList<PaymentRow> getPaymentRows() throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet("http://localhost:8080/payments-row-dtos");
        HttpResponse httpresponse = httpclient.execute(httpget);
        HttpEntity httpEntity = httpresponse.getEntity();

        String responseString = EntityUtils.toString(httpEntity, "UTF-8");

        ObjectMapper mapper = new ObjectMapper();

        PaymentRow[] paymentRows = mapper.readValue(responseString, PaymentRow[].class);

        ObservableList<PaymentRow> observableList = FXCollections.observableArrayList(paymentRows);

        return observableList;
    }

    public static void deletePayment(Long id) {

        System.out.println("Payment deleted");
    }

    public static Payment getPayment(Long id) {

        return new Payment();
    }

    public static Expense getExpenses(Long PaymentId) {
        return new Expense();
    }

    public static Payment savePayment(Payment payment) {

        return new Payment();
    }

    public static void saveExpenses(List<Expense> expenseList) {

    }
}
