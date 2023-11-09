package com.hady.automation;

public class UdvashAdmissionModel {
    public UdvashAdmissionModel(String name, String number, String amount) {
        this.name = name;
        this.number = number;
        this.amount = amount;
    }

    private String name, number, amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }



}
