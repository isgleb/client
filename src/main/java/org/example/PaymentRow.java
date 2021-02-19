package org.example;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRow {

    private Long id;
    private int clientId;
    private String name;
    private String address;
    private Date period;
    private Long amount;
}
