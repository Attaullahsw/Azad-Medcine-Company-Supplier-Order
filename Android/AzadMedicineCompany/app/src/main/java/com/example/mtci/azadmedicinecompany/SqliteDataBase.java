package com.example.mtci.azadmedicinecompany;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by AttaUllah on 5/4/2018.
 */

public class SqliteDataBase extends SQLiteOpenHelper {
    public static String DBName = "amc.db";

    //login table
    public static String login_tbl = "login_tbl";
    public static String _id = "l_id";
    public static String user_name = "user_name";
    public static String user_pass = "user_pass";



    public static String CREATE_LOGIN_TABLE = "CREATE TABLE IF NOT EXISTS "+login_tbl+"("+_id+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
            user_name+" TEXT NOT NULL,"+
            user_pass+" TEXT NOT NULL);";

    // customer table name and coloum names
    public static String customer_tbl = "customer_tbl";
    public static String c_code = "c_code";
    public static String c_name = "c_name";
    public static String c_address = "c_address";
    public static String c_area = "c_area";
    public static String c_contact_no = "c_contact_no";

    public static String CREATE_CUSTOMER_TABLE = "CREATE TABLE IF NOT EXISTS "+customer_tbl+"("+c_code+" INTEGER NOT NULL,"+c_name+
            " TEXT NOT NULL,"+c_address+" TEXT,"+c_area+" TEXT NOT NULL,"+c_contact_no+" TXET ,PRIMARY KEY("+c_code+
            "));";

    //product table name and its coloum names
    public static String product_tbl = "product_tbl";
    public static String p_code = "p_code";
    public static String p_description = "p_description";
    public static String p_tp = "p_tp";

    public static String CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS "+product_tbl+"("+p_code+" INTEGER NOT NULL,"+p_description+
            " TEXT NOT NULL,"+p_tp+" REAL,PRIMARY KEY("+p_code+"));";

    //order main table
    public static String order_main_tbl = "order_main_tbl";
    public static String order_no = "order_no";
    public static String order_date = "order_date";
    public static String order_c_code = "c_code";
    public static String status = "status";

    public static String CREATE_ORDER_MAIN_TABLE = "CREATE TABLE IF NOT EXISTS "+order_main_tbl+"("+order_no+
            " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +order_date+ " TEXT NOT NULL,"+c_code+
            " INTEGER NOT NULL,"+status+" INTEGER,FOREIGN KEY("+c_code+") REFERENCES customer_tbl("+c_code+") ON DELETE CASCADE );";

    //order details table
    public static String order_details_tbl = "order_details_tbl";
    public static String order_details_id = "order_details_id";
    public static String order_main_no = "order_no";
    public static String order_p_code = "p_code";
    public static String order_details_quantity = "order_details_quantity";

    public static String CREATE_ORDER_DETAILS_TABLE = "CREATE TABLE IF NOT EXISTS "+order_details_tbl+"("+order_details_id+
            " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +order_main_no+ " INTEGER NOT NULL,"+p_code+
            " INTEGER NOT NULL, "+order_details_quantity+" INTEGER DEFAULT 1 , FOREIGN KEY("+order_main_no+") REFERENCES order_main_tbl("+
            order_main_no+") ON DELETE CASCADE," +
            " FOREIGN KEY("+order_p_code+") REFERENCES product_tbl("+order_p_code+") ON DELETE CASCADE );";


    public SqliteDataBase(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_LOGIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_CUSTOMER_TABLE);
        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);
        sqLiteDatabase.execSQL(CREATE_ORDER_MAIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_ORDER_DETAILS_TABLE);
        ContentValues values = new ContentValues();
        values.put(user_name,"admin");
        values.put(user_pass,"mtci_azad");
        sqLiteDatabase.insert(login_tbl,null,values);


    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys = ON;");
    }

    //check login detail
    public boolean checkDetail(String user_name,String user_pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+login_tbl+" WHERE "+this.user_name+
                " = '"+user_name+"' and "+this.user_pass+" = '"+user_pass+"'",null);

        if(result.getCount() == 0){
            return false;
        }else {
            return true;
        }

    }

    //Get Area From Data Base
    public String[] getArea(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor result = db.rawQuery("SELECT DISTINCT "+c_area+" FROM "+customer_tbl,null);
            String[] name = new String[result.getCount()];
            int i=0;
            result.moveToFirst();
            while (result.isAfterLast() == false){
                name[i] = result.getString(0);
                result.moveToNext();
                i++;
            }
            return name;
        }catch (Exception ex){
            ex.printStackTrace();

            return null;
        }
    }

    //Get Customer From DataBase..
    public Cursor getCustomer(String c_area){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor result = db.rawQuery("SELECT "+c_code+" , "+c_name+" FROM "+customer_tbl+" WHERE "+this.c_area+
                    " = '"+c_area+"' ",null);
            return result;
        }catch (Exception ex){
            ex.printStackTrace();

            return null;
        }
    }


    //Get product From Data Base
    public Cursor getProduct(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor result = db.rawQuery("SELECT "+p_code+","+p_description+" FROM "+product_tbl+" ORDER BY "+p_description,null);

            return result;
        }catch (Exception ex){
            ex.printStackTrace();

            return null;
        }
    }


    //Get product price From Data Base
    public String getProductPrice(int p_code){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor result = db.rawQuery("SELECT "+p_tp+" FROM "+product_tbl+" WHERE "+this.p_code+
                    " = '"+p_code+"'",null);

            result.moveToFirst();

            return result.getString(0);
        }catch (Exception ex){
            ex.printStackTrace();

            return ex.toString();
        }
    }


    //insert customer code into order main table
    long insertOrderMainTble(int c_code,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(order_c_code,c_code);
        values.put(order_date,date);
        values.put(status,0);
        return db.insert(order_main_tbl,null,values);
    }

    //insert order into order details table
    void insertOrderDetailTble(int c_code, ArrayList<Product> p){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor result = db.rawQuery("SELECT "+order_no+" FROM "+order_main_tbl+" WHERE "+this.order_c_code+
                    " = '" + c_code + "' ORDER BY "+order_no+" DESC", null);
            result.moveToFirst();

            for (int i = 0; i < p.size(); i++) {
                Product product = p.get(i);
                ContentValues values = new ContentValues();
                values.put(order_main_no, Integer.parseInt(result.getString(0)));
                values.put(order_p_code, product.getP_code());
                values.put(order_details_quantity, product.getProduct_quantity());
                db.insert(order_details_tbl, null, values);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    //insert Excisting order into order details table
    void insertNewOrderDetailTble(int order, ArrayList<Product> p){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            for (int i = 0; i < p.size(); i++) {
                Product product = p.get(i);
                ContentValues values = new ContentValues();
                values.put(order_main_no, order);
                values.put(order_p_code, product.getP_code());
                values.put(order_details_quantity, product.getProduct_quantity());
                db.insert(order_details_tbl, null, values);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }






    //get ordered customer from order main tbl
    ArrayList<AllRecordDataHolderClass> getOrderMainTble(int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<AllRecordDataHolderClass> arrayList = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + order_main_tbl + " WHERE status = "+status+" ORDER BY " + this.order_main_no + " DESC", null);
            cursor.moveToFirst();
            int i=1;
            while (cursor.isAfterLast() == false) {
                int o_code = cursor.getInt(0);
                String date = cursor.getString(1);
                int c_code = cursor.getInt(2);
                Cursor c =  db.rawQuery("SELECT "+c_name+" FROM " + customer_tbl + " WHERE " + this.c_code +
                        " = '"+c_code+"' ", null);

                c.moveToFirst();
                String name = c.getString(0);
                AllRecordDataHolderClass allRecordDataHolderClass = new AllRecordDataHolderClass(o_code,date, c_code , c.getString(0),i);
                arrayList.add(allRecordDataHolderClass);
                cursor.moveToNext();
                i++;
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    //get order detail and orderd product names from order_detail_tbl and product_tbl
    Pair<int[],ArrayList<Product>> getOrderdRecord(int order_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Product> list = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery("SELECT "+p_code+" , "+order_details_quantity+" , "+order_details_id+" FROM " + order_details_tbl +
                    " WHERE " + this.order_no + " = '"+order_no+"'", null);
            int[] i = new int[cursor.getCount()];
            int x=0;
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                int p_code = cursor.getInt(0);
                int quantity = cursor.getInt(1);
                i[x] = cursor.getInt(2);
                x++;
                Cursor c =  db.rawQuery("SELECT "+p_description+" , "+p_tp+" FROM " + product_tbl + " WHERE " + this.p_code +
                        " = '"+p_code+"' ", null);
                c.moveToFirst();
                String p_name = c.getString(0);
                double p_tp = c.getDouble(1);
                Product p =new Product(p_code,p_name,quantity,p_tp,p_tp*quantity);
                list.add(p);
                cursor.moveToNext();
            }
            return new Pair<>(i,list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    //updtae record to order detail tbl
    public boolean updateRecordToOrderDetailTbl(ContentValues values,int order_id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(order_details_tbl,values,order_details_id+" = "+order_id,null) > 0;

    }


    //delete record from order detail tbl
    public boolean deletDataFromOrderDetailTbl(int order_id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(order_details_tbl,order_details_id+" = "+order_id,null)>0;
    }

    //delete record from order main tbl
    public boolean deletDataFromOrderMainTbl(int order_no){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(order_main_tbl,this.order_no+" = "+order_no,null)>0;
    }

    //Update status in order main tbl
    public boolean updateStatus(int order_no){
        ContentValues values = new ContentValues();
        values.put(status,1);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(order_main_tbl,values,this.order_no+" = "+order_no,null)>0;
    }



    //delete data not match with mysql
    public void delete(int[] x , int[] y){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select c_code from "+customer_tbl,null);
        cursor.moveToFirst();
        boolean check = true;
        while (cursor.isAfterLast() == false){
            int id = cursor.getInt(0);
            for(int i=0; i<x.length; i++){
                if(id == x[i]){
                    check = false;
                    break;
                }
            }
            if(check){
                db.delete(customer_tbl,c_code+" = "+id,null);
            }

            cursor.moveToNext();
            check = true;
        }


        Cursor cursor2 = db.rawQuery("select p_code from "+product_tbl,null);
        cursor2.moveToFirst();
        check = true;
        while (cursor2.isAfterLast() == false){
            int id = cursor2.getInt(0);
            for(int i=0; i<y.length; i++){
                if(id == y[i]){
                    check = false;
                    break;
                }
            }
            if(check){
                db.delete(customer_tbl,p_code +" = "+id,null);
            }

            cursor2.moveToNext();
            check = true;
        }


    }


    void getProductFromMySql(ContentValues values){
        int id = values.getAsInteger("p_code");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+product_tbl+" where "+p_code+" = '"+id+"'",null);
        if(cursor.getCount() == 0){
            db.insert(product_tbl,null,values);
        }else {
            values.remove("p_code");
            db.update(product_tbl,values,p_code+" = "+id,null);
        }
    }
    void getCustomerFromMySql(ContentValues values){
        int id = values.getAsInteger("c_code");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+customer_tbl+" where "+c_code+" = '"+id+"'",null);
        if(cursor.getCount() == 0){
            db.insert(customer_tbl ,null,values);
        }else {
            values.remove("c_code");
            db.update(customer_tbl,values,this.c_code+" = "+id,null);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
