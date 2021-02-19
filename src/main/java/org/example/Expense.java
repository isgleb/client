package org.example;

import lombok.Data;

@Data
public class Expense {


    Long id;
    Long paymentId;
    int coldWater;
    int hotWater;
    int electricity;
    int repairment;
}
