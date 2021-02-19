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
import java.util.List;
import java.util.Map;

public class HttpController {

    private static String baseUrl = "http://localhost:8080";

    public static ObservableList<PaymentRow> getPaymentRows() throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(baseUrl + "/payments-row-dtos");
        HttpResponse httpresponse = httpClient.execute(httpget);
        HttpEntity httpEntity = httpresponse.getEntity();

        String responseString = EntityUtils.toString(httpEntity, "UTF-8");

        ObjectMapper mapper = new ObjectMapper();

        PaymentRow[] paymentRows = mapper.readValue(responseString, PaymentRow[].class);

        ObservableList<PaymentRow> observableList = FXCollections.observableArrayList(paymentRows);

        httpClient.close();

        return observableList;
    }

    public static void deletePayment(Long id) {

        System.out.println("Payment deleted");
    }

    public static Payment getPayment(Long id) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(baseUrl + "/payments" + "/" + id);
        HttpResponse httpresponse = httpClient.execute(httpget);
        HttpEntity httpEntity = httpresponse.getEntity();

        String responseString = EntityUtils.toString(httpEntity, "UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        Payment payment = mapper.readValue(responseString, Payment.class);

        httpClient.close();

        return payment;
    }

    public static Map<String,Integer> getExpenses(Long PaymentId) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(baseUrl + "/expenses/?paymentId=" + PaymentId);
        HttpResponse httpresponse = httpClient.execute(httpget);
        HttpEntity httpEntity = httpresponse.getEntity();

        String responseString = EntityUtils.toString(httpEntity, "UTF-8");

        ObjectMapper mapper = new ObjectMapper();


        Map<String, Integer> map = mapper.readValue(responseString, Map.class);

        httpClient.close();

        return map;
    }

    public static Payment savePayment(Payment payment) {

        return new Payment();
    }

    public static void saveExpenses(List<Expense> expenseList) {

    }
}
