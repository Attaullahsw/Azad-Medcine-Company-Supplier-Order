package com.example.mtci.azadmedicinecompany;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by AttaUllah on 4/5/2018.
 */

public class Product {
    private int p_code;
    private String product_name;
    private int product_quantity;
    private double product_per_item_price;
    private double Product_total;

    public Product(int p_code,String product_name, int product_quantity, double product_per_item_price, double product_total) {
        this.p_code = p_code;
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.product_per_item_price = product_per_item_price;
        this.Product_total = product_total;
    }

    public int getP_code() {
        return p_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public double getProduct_per_item_price() {
        return product_per_item_price;
    }

    public double getProduct_total() {
        return Product_total;
    }
}
