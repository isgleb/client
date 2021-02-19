package org.example;

import lombok.Data;

import java.util.Date;

@Data
public class Payment {

    private Long id;
    private String clientName;
    private String ownersName;
    private String address;
    private Date period;
}
