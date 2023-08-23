package com.example.mtci.azadmedicinecompany;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllCartRecordList extends AppCompatActivity {
    SwipeMenuListView listView;
    ArrayList<Product> arrayList;
    TextView txt_c, txt_all_total, txt_all_total_label;
    CartListViewAdapter adapter;
    Button btn_send;

    ProgressDialog progressDialog;

    LinearLayout linearLayout;

    int[] order_details_id;

    int c_code;
    int order_no;
    double total = 0;
    SqliteDataBase sqliteDataBase;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        if(item.getItemId() == R.id.add_new){
            Intent intent = new Intent(AllCartRecordList.this, AddProduct.class);
            intent.putExtra("customer_name", getIntent().getStringExtra("c_name").toString());
            intent.putExtra("customer_code",getIntent().getIntExtra("c_code", 0));
            intent.putExtra("order_no",order_no);
            intent.putExtra("check_exsitng",true);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list_view);

        //        Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);


        listView = (SwipeMenuListView) findViewById(R.id.cartListView);
        txt_c = (TextView) findViewById(R.id.txt_c);
        txt_all_total = (TextView) findViewById(R.id.txt_all_total);
        txt_all_total_label = (TextView) findViewById(R.id.txt_all_total_label);
        btn_send = (Button) findViewById(R.id.btn_Save_order);
        btn_send.setText("Send Order");

        linearLayout = (LinearLayout) findViewById(R.id.lin_total_btn_save);
        linearLayout.setVisibility(View.VISIBLE);

        txt_c.setText("Customer Name: " + getIntent().getStringExtra("c_name").toString());

        sqliteDataBase = new SqliteDataBase(this);
        order_no = getIntent().getIntExtra("order_no", 0);




        btn_send.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                btn_send.setEnabled(false);
                ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (null != activeNetwork) {

                    if ((activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                            && activeNetwork.isConnected()) {


                        sendData();

                    }
                }else{
                    Toast.makeText(AllCartRecordList.this,"No Network Connection",Toast.LENGTH_SHORT).show();
                    btn_send.setEnabled(true);
                }

            }
        });


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

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // edit
                        Intent intent = new Intent(AllCartRecordList.this,cartListViewItemEdit.class);
                        intent.putExtra("check",true);
                        intent.putExtra("order_id",order_details_id[position]);
                        intent.putExtra("p_code",arrayList.get(position).getP_code());
                        intent.putExtra("p_name",arrayList.get(position).getProduct_name());
                        intent.putExtra("p_quantity",arrayList.get(position).getProduct_quantity());
                        intent.putExtra("p_per_item",arrayList.get(position).getProduct_per_item_price());
                        intent.putExtra("p_total",arrayList.get(position).getProduct_total());

                        startActivity(intent);

                        break;
                    case 1:
                        // delete
                        final int p = position;
                        AlertDialog.Builder builder = new AlertDialog.Builder(AllCartRecordList.this);
                        builder.setIcon(R.drawable.warning_icon);
                        builder.setTitle("Warning!");
                        builder.setMessage("Are you sure to delete this item");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sqliteDataBase.deletDataFromOrderDetailTbl(order_details_id[p]);
                                recreate();
                            }
                        });
                        builder.setNegativeButton("Cancel", null);
                        builder.show();


                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (!(order_no == 0)) {

            Pair<int[], ArrayList<Product>> pair = sqliteDataBase.getOrderdRecord(order_no);
            arrayList = pair.second;
            order_details_id = pair.first;
        }

        if (arrayList.size() == 0) {
            TextView textView = (TextView) findViewById(R.id.txt_error_msg2);
            LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.lin_list_customer);
            linearLayout2.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            sqliteDataBase.deletDataFromOrderMainTbl(order_no);
        } else {
            adapter = new CartListViewAdapter(this, arrayList);
            listView.setAdapter(adapter);
            total = 0;
            for (int i = 0; i < arrayList.size(); i++) {
                total += arrayList.get(i).getProduct_total();
            }
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            txt_all_total.setText(String.valueOf(twoDForm.format(total)));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.allrecordlistmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    void sendData(){
        String strings = "http://azadmedicinecompany.pk/orderspanel/app.php";
        StringRequest request = new StringRequest(Request.Method.POST, strings, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean error = jsonObject.getBoolean("error");
                    String msg = jsonObject.getString("msg");
                    if (error) {
                        progressDialog.dismiss();
                        Toast.makeText(AllCartRecordList.this, msg.toString(), Toast.LENGTH_LONG).show();

                    } else {
                        sqliteDataBase.updateStatus(order_no);
                        finish();
                        Toast.makeText(AllCartRecordList.this, msg.toString(), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressDialog.dismiss();
                btn_send.setEnabled(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                btn_send.setEnabled(true);
                progressDialog.dismiss();
                Toast.makeText(AllCartRecordList.this, "Connection Problem", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("size", String.valueOf(arrayList.size()));
                params.put("order_date", String.valueOf(getIntent().getStringExtra("order_date")));
                params.put("c_code", String.valueOf(getIntent().getIntExtra("c_code", 0)));

                for (int i = 0; i < arrayList.size(); i++) {
                    params.put("p_code" + i, String.valueOf(arrayList.get(i).getP_code()));
                    params.put("order_details_quantity" + i, String.valueOf(arrayList.get(i).getProduct_quantity()));
                }
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AllCartRecordList.this);
        requestQueue.add(request);
        progressDialog = new ProgressDialog(AllCartRecordList.this);
        progressDialog.setTitle("Sending Order");
        progressDialog.setMessage("Please wait a while...");
        progressDialog.show();


    }




}











