package com.example.mtci.azadmedicinecompany;

/**
 * Created by AttaUllah on 5/9/2018.
 */

public class AllRecordDataHolderClass {
    int i;
    private int order_no;
    private String order_date;
    private int c_code;
    private String c_name;

    public AllRecordDataHolderClass(int order_no, String order_date, int c_code , String c_name ,int i) {
        this.order_no = order_no;
        this.order_date = order_date;
        this.c_code = c_code;
        this.c_name = c_name;
        this.i = i;
    }

    public int getOrder_no() {
        return order_no;
    }

    public String getOrder_date() {
        return order_date;
    }

    public int getC_code() {
        return c_code;
    }

    public String getC_name() {
        return c_name;
    }
    public int geti() {
        return i;
    }
}
