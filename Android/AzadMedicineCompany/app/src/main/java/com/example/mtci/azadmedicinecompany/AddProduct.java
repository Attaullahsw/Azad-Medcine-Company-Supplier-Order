package com.example.mtci.azadmedicinecompany;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    ListView listView;
    SqliteDataBase sqliteDataBase;
    static private ArrayList<AllProductDataHolderClass> product_detail;
    public static ArrayList<Product> products;
    int c_code;
    String c_name;
    public static ArrayList<Integer> deleteIds;
    //    Reference variabe
    static boolean cheekCart = false;
    static TextView tv;

    SharedPreferences logPre;
    SharedPreferences.Editor preEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        deleteIds = new ArrayList<>();

        //        Action bar
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Product");


        products = new ArrayList<>();
        sqliteDataBase = new SqliteDataBase(this);
        listView = (ListView) findViewById(R.id.product_list);

        c_code = getIntent().getIntExtra("customer_code",0);



        if(c_code == 0){
            Toast.makeText(AddProduct.this,"Customer is not selected properly",Toast.LENGTH_LONG).show();
            finish();
        }

        c_name= getIntent().getStringExtra("customer_name");

        Cursor cursor = sqliteDataBase.getProduct();
        product_detail = new ArrayList<>();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            int code = cursor.getInt(0);
            String name = cursor.getString(1);
            AllProductDataHolderClass allProductDataHolderClass = new AllProductDataHolderClass(code,name,false);
            product_detail.add(allProductDataHolderClass);
            cursor.moveToNext();
        }
        if(product_detail.size() != 0) {
            ProductListViewAdapter2 adapter = new ProductListViewAdapter2(this, product_detail);
            listView.setAdapter(adapter);

//            SideSelector sideSelector = (SideSelector) findViewById(R.id.side_selector);
//            sideSelector.setListView(listView);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.cart);
        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<AllProductDataHolderClass> tempList = new ArrayList<>();
                for(AllProductDataHolderClass temp : product_detail){
                    if(temp.getP_name().toLowerCase().contains(s.toLowerCase())){
                        tempList.add(temp);
                    }else if(String.valueOf(temp.getP_code()).toLowerCase().contains(s.toLowerCase())){
                        tempList.add(temp);
                    }
                }
                if(tempList.size() > 0) {
                    listView.setVisibility(View.VISIBLE);
                    ProductListViewAdapter2 adapter = new ProductListViewAdapter2(AddProduct.this, tempList);
                    listView.setAdapter(adapter);
                }else{
                    listView.setVisibility(View.GONE);
                }

                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

        });

        View itemChooser = item.getActionView();
        tv = (TextView)itemChooser.findViewById(R.id.actionbar_notifcation_textview);
        tv.setVisibility(View.INVISIBLE);
        itemChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProduct.this,cartListView.class);
                intent.putExtra("customer_name",c_name);
                intent.putExtra("customer_code",c_code);
                intent.putExtra("check_exsitng",getIntent().getBooleanExtra("check_exsitng",false));
                intent.putExtra("order_no",getIntent().getIntExtra("order_no",0));
                startActivity(intent);
            }
        });
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.cart:
                break;
            case R.id.logOut:
                logPre = getSharedPreferences("Login",MODE_PRIVATE);
                preEditor = logPre.edit();

                preEditor.remove("user");
                preEditor.remove("pass");
                preEditor.apply();
                preEditor.commit();
                Intent intent = new Intent(AddProduct.this,LoginActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(deleteIds.size() > 0){
            for (int x=0; x<deleteIds.size();x++)
                for(int i=0;i<product_detail.size();i++){
                    if(deleteIds.get(x) == product_detail.get(i).getP_code()){
                        product_detail.get(i).setCheck(false);
                    }
                }
            ProductListViewAdapter2 adapter = new ProductListViewAdapter2(this, product_detail);
            listView.setAdapter(adapter);
        }
//                set the cart Badge
        if(cheekCart){

            if(products.size() != 0){
                tv.setText(String.valueOf(products.size()));
                tv.setVisibility(View.VISIBLE);
            }else
                tv.setVisibility(View.INVISIBLE);
        }
    }

}
