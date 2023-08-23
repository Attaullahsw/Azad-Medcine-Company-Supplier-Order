package com.example.mtci.azadmedicinecompany;

public class AllProductDataHolderClass {
    private int p_code;
    private String p_name;
    private boolean check;
    private boolean seperator;

    public AllProductDataHolderClass(int p_code, String p_name,boolean check) {
        this.p_code = p_code;
        this.p_name = p_name;
        this.check = check;
        seperator = false;
    }

    public int getP_code() {
        return p_code;
    }

    public String getP_name() {
        return p_name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check){
        this.check = check;
    }

    public boolean isSeperator() {
        return seperator;
    }

    public void setSeperator(boolean check){
        this.seperator = check;
    }
}
