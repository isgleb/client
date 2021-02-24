package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    private Long id;
    private String name;
    private int amount;

    public Expense(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}
