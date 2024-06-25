package com.example.tiktokapp.model;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@RequiredArgsConstructor
public class SimpleAPIRespone {
    private int err;
    private String mes;

    public SimpleAPIRespone(int err, String mes) {
        this.err = err;
        this.mes = mes;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
