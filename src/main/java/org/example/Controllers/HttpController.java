package org.example.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.Payment;
import org.example.PaymentRow;

import java.io.IOException;


public class HttpController {

    private static String baseUrl = "http://localhost:8080";

    public static ObservableList<PaymentRow> getPaymentRows() throws IOException {

        HttpGet httpget = new HttpGet(baseUrl + "/payments-row-dtos");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpresponse = httpClient.execute(httpget)) {
        HttpEntity httpEntity = httpresponse.getEntity();

        String responseString = EntityUtils.toString(httpEntity, "UTF-8");

        ObjectMapper mapper = new ObjectMapper();

        PaymentRow[] paymentRows = mapper.readValue(responseString, PaymentRow[].class);

        ObservableList<PaymentRow> observableList = FXCollections.observableArrayList(paymentRows);

        return observableList;
        }
    }

    public static void deletePayment(Long id) {

        System.out.println("Payment deleted");
    }

    public static Payment getPayment(Long id) throws IOException {

        HttpGet httpget = new HttpGet(baseUrl + "/payments" + "/" + id);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpresponse = httpClient.execute(httpget) ) {

            HttpEntity httpEntity = httpresponse.getEntity();
            String responseString = EntityUtils.toString(httpEntity, "UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            Payment payment = mapper.readValue(responseString, Payment.class);

        return payment;
        }
    }

    public static void savePayment(Payment payment) throws IOException {

        HttpPost post = new HttpPost(baseUrl + "/payments");
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(payment);
        post.setEntity(new StringEntity(message));
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)){
        }
    }
}
