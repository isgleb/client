package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date period;
    private Long amount;
}
