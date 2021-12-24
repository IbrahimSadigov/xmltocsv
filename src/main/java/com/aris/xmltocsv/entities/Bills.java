package com.aris.xmltocsv.entities;



public class Bills {

    private long id;

    private String serialNumber;

    private String suitableForATM;

    private String fit;

    private String unfit;

    private String errorCode;

    public Bills() {
    }

    public long getId() {
        return id;
    }

    public Bills setId(long id) {
        this.id = id;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Bills setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public String getSuitableForATM() {
        return suitableForATM;
    }

    public Bills setSuitableForATM(String suitableForATM) {
        this.suitableForATM = suitableForATM;
        return this;
    }

    public String getFit() {
        return fit;
    }

    public Bills setFit(String fit) {
        this.fit = fit;
        return this;
    }

    public String getUnfit() {
        return unfit;
    }

    public Bills setUnfit(String unfit) {
        this.unfit = unfit;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Bills setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    @Override
    public String toString() {
        return "Bills{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", suitableForATM=" + suitableForATM +
                ", fit=" + fit +
                ", unfit=" + unfit +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
