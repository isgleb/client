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
import org.example.PaymentRow;

import java.io.IOException;
import java.util.Arrays;

public class HttpController {


    public static ObservableList<PaymentRow> getPaymentRows() throws IOException {


        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet("http://localhost:8080/payments");
        HttpResponse httpresponse = httpclient.execute(httpget);
        HttpEntity httpEntity = httpresponse.getEntity();

        String responseString = EntityUtils.toString(httpEntity, "UTF-8");
        System.out.println(responseString);

        ObjectMapper mapper = new ObjectMapper();

        PaymentRow[] paymentRows;
        paymentRows = mapper.readValue(responseString, PaymentRow[].class);

        for (PaymentRow paymentRow : Arrays.asList(paymentRows)) {
            System.out.println(paymentRow);
        }

        ObservableList<PaymentRow> observableList = FXCollections.observableArrayList(paymentRows);

//        observableList.

        return observableList;
    }
}
