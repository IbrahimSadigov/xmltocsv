package com.aris.xmltocsv.entities;


import java.util.ArrayList;

public class LinesBills {

    private String nominal;

    private ArrayList<Bills> bills;

    public LinesBills() {
    }


    public String getNominal() {
        return nominal;
    }

    public LinesBills setNominal(String nominal) {
        this.nominal = nominal;
        return this;
    }

    public ArrayList<Bills> getBills() {
        return bills;
    }

    public LinesBills setBills(ArrayList<Bills> bills) {
        this.bills = bills;
        return this;
    }
}
