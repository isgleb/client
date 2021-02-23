package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
import java.util.List;

//@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Payment {

    private Long id;
    private int clientId;
    private String ownerName;
    private String address;
//    private Date period;
    List<Expense> expenses;
}
