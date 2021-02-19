package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

//@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {

    private Long id;
    private int clientId;
    private String ownerName;
    private String address;
    private Date period;
}

//id": 1,
//        "clientId": 23,
//        "ownerName": "Victor",
//        "address": "Arbat",
//        "period": null
