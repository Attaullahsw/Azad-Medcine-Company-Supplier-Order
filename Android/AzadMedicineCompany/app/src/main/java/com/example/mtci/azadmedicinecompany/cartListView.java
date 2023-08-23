package com.example.mtci.azadmedicinecompany;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.mtci.azadmedicinecompany.AddProduct.deleteIds;
import static com.example.mtci.azadmedicinecompany.AddProduct.products;
import static com.example.mtci.azadmedicinecompany.ChooseProduct.product_list;

public class cartListView extends AppCompatActivity {
    SwipeMenuListView listView;
    //ArrayList<Product> arrayList;
    TextView txt_c,txt_all_total,txt_all_total_label;
    CartListViewAdapter adapter;
    Button btn_save;

    LinearLayout linearLayout;

    int c_code;
    double total = 0;

    SqliteDataBase sqliteDataBase;

    SharedPreferences logPre;
    SharedPreferences.Editor preEditor;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list_view);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("CartList");
        actionBar.setDisplayHomeAsUpEnabled(true);

        c_code = getIntent().getIntExtra("customer_code",0);

        sqliteDataBase = new SqliteDataBase(this);

        listView = (SwipeMenuListView)findViewById(R.id.cartListView);
        txt_c = (TextView)findViewById(R.id.txt_c);
        txt_all_total = (TextView)findViewById(R.id.txt_all_total);
        txt_all_total_label = (TextView)findViewById(R.id.txt_all_total_label);
        btn_save = (Button) findViewById(R.id.btn_Save_order);
        txt_c.setText("Customer Name: "+getIntent().getStringExtra("customer_name"));
        linearLayout = (LinearLayout)findViewById(R.id.lin_total_btn_save);
        if(products.size() != 0){
            adapter = new CartListViewAdapter(this,products);
            listView.setAdapter(adapter);}



        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                editItem.setBackground(R.color.colorAccent);
                // set item width
                editItem.setWidth(170);
                // set item title
                // set item title fontsize
                editItem.setTitleSize(18);
                // set item title font color
                editItem.setTitleColor(Color.WHITE);
                //set icon
                editItem.setIcon(R.drawable.edit_icon);
                // add to menu
                menu.addMenuItem(editItem);


                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(R.color.red);
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.delete_icon);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        listView.setMenuCreator(creator);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_save.setEnabled(false);
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);

                if(products.size() !=0) {
                    ProgressDialog progressDialog = new ProgressDialog(cartListView.this);
                    progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setTitle("Saving Order");
                    progressDialog.setMessage("Please wait....");
                    progressDialog.show();
                    if(getIntent().getBooleanExtra("check_exsitng",false) == true){
                     int order_no =  getIntent().getIntExtra("order_no",0);
                     if(order_no !=0){
                         sqliteDataBase.insertNewOrderDetailTble(order_no, products);
                         Toast.makeText(cartListView.this, "Succesfully Added", Toast.LENGTH_SHORT).show();
                         progressDialog.dismiss();
                         Intent intent = new Intent(cartListView.this,AllCartRecordList.class);
                         intent.putExtra("order_no",order_no);
                         intent.putExtra("c_name", getIntent().getStringExtra("customer_name").toString());
                         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                         startActivity(intent);
                     }
                    }else if (sqliteDataBase.insertOrderMainTble(c_code, formattedDate) >= 0) {
                        sqliteDataBase.insertOrderDetailTble(c_code, products);
                        Toast.makeText(cartListView.this, "Succesfully Added", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent intent = new Intent(cartListView.this,ChooseArea.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    progressDialog.dismiss();
                }else{
                    btn_save.setEnabled(true);
                    Toast.makeText(cartListView.this,"Please Enter The orders or order",Toast.LENGTH_LONG).show();

                }

            }
        });


        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // edit
                        Intent intent = new Intent(cartListView.this,cartListViewItemEdit.class);
                        intent.putExtra("index", Integer.toString(position));
                        startActivity(intent);

                        break;
                    case 1:
                        // delete
                        delete(position);

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


    }





    void delete(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(cartListView.this);
        builder.setIcon(R.drawable.warning_icon);
        builder.setTitle("Warning!");
        builder.setMessage("Are you sure to delete this item");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteIds.add(products.get(position).getP_code());
                products.remove(position);
                recreate();

            }
        });

        builder.setNegativeButton("Cancel",null);
        builder.show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();



        if (products.size() == 0) {
            TextView textView = (TextView) findViewById(R.id.txt_error_msg2);
            LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.lin_list_customer);
            linearLayout2.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);


        } else {
            total = 0;
            for (int i = 0; i < products.size(); i++) {
                total += products.get(i).getProduct_total();
            }
            adapter = new CartListViewAdapter(this, products);
            listView.setAdapter(adapter);
            linearLayout.setVisibility(View.VISIBLE);
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            txt_all_total.setText(String.valueOf(twoDForm.format(total)));
        }


    }
}


