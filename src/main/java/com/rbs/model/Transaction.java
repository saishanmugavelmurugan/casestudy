package com.rbs.model;

public class Transaction {
    public String sourceAccountNumber;
    public String destinationAccountNumber;
    public Double amount;

    public Transaction() { }

    public Transaction(String sourceAccountNumber, String destinationAccountNumber, Double amount) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.amount = amount;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sourceAccountNumber='" + sourceAccountNumber + '\'' +
                ", destinationAccountNumber='" + destinationAccountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
