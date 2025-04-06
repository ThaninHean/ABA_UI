package com.example.aba_bank.ui.model;

public class Transaction {
    private String name;
    private String amount;
    private String time;

    public Transaction(String time, String amount, String name) {
        this.time = time;
        this.amount = amount;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
