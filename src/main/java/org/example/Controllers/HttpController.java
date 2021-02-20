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


//try (CloseableHttpClient httpClient = HttpClients.createDefault();
//             CloseableHttpResponse response = httpClient.execute(request)) {
//
//            // Get HttpResponse Status
//            System.out.println(response.getProtocolVersion());              // HTTP/1.1
//            System.out.println(response.getStatusLine().getStatusCode());   // 200
//            System.out.println(response.getStatusLine().getReasonPhrase()); // OK
//            System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK
//
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                // return it as a String
//                String result = EntityUtils.toString(entity);
//                System.out.println(result);
//            }
//
//        }

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

    public static void savePayment(Payment payment) throws IOException {

        HttpPost post = new HttpPost(baseUrl + "/payments");
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(payment);
        post.setEntity(new StringEntity(message));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)){
        }

//        return new Payment();
    }

}
