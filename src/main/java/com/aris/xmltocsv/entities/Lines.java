package com.aris.xmltocsv.entities;


public class Lines {

    private long id;

    private String userID;

    private String machineNumber;

    private String machineModel;

    private String depositNumber;

    private String packageNumber;

    private String startedAt;

    private String endedAt;

    private String sendedAt;

    private String rejectionExists;

    private String CurrencyCode;

    private long nominal;

    private long quantity;

    private long amount;

    private String rejected;

    public Lines() {
    }

    public long getId() {
        return id;
    }

    public Lines setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserID() {
        return userID;
    }

    public Lines setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public Lines setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
        return this;
    }

    public String getMachineModel() {
        return machineModel;
    }

    public Lines setMachineModel(String machineModel) {
        this.machineModel = machineModel;
        return this;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public Lines setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
        return this;
    }

    public String getPackageNumber() {
        return packageNumber;
    }

    public Lines setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
        return this;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public Lines setStartedAt(String startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    public String getEndedAt() {
        return endedAt;
    }

    public Lines setEndedAt(String endedAt) {
        this.endedAt = endedAt;
        return this;
    }

    public String getSendedAt() {
        return sendedAt;
    }

    public Lines setSendedAt(String sendedAt) {
        this.sendedAt = sendedAt;
        return this;
    }

    public String getRejectionExists() {
        return rejectionExists;
    }

    public Lines setRejectionExists(String rejectionExists) {
        this.rejectionExists = rejectionExists;
        return this;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public Lines setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
        return this;
    }

    public long getNominal() {
        return nominal;
    }

    public Lines setNominal(long nominal) {
        this.nominal = nominal;
        return this;
    }

    public long getQuantity() {
        return quantity;
    }

    public Lines setQuantity(long quantity) {
        this.quantity = quantity;
        return this;
    }

    public long getAmount() {
        return amount;
    }

    public Lines setAmount(long amount) {
        this.amount = amount;
        return this;
    }

    public String getRejected() {
        return rejected;
    }

    public Lines setRejected(String rejected) {
        this.rejected = rejected;
        return this;
    }
}
