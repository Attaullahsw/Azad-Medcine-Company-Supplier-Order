package com.example.mtci.azadmedicinecompany;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChooseArea extends AppCompatActivity {

    //    Reference view variable
    ProgressDialog progressDialog;
    AutoCompleteTextView edtChooseArea;
    Button btn_chooseArea,btn_allRecord;
    String[] name;
    SqliteDataBase sqliteDataBase;

    SharedPreferences logPre;
    SharedPreferences.Editor preEditor;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        if(item.getItemId() == R.id.refresh){
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (null != activeNetwork) {

                if((activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                        && activeNetwork.isConnected()) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ChooseArea.this);
                    builder.setIcon(R.drawable.warning_icon);
                    builder.setTitle("Warning!");
                    builder.setMessage("Are you sure to Refresh Data");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData();
                        }
                    });
                    builder.setNegativeButton("Cancel", null);
                    builder.show();

                }
            }
            else
                Toast.makeText(getApplicationContext(), "No internet Connectivity", Toast.LENGTH_LONG).show();

        }
        if(item.getItemId() == R.id.logOut1) {
            logPre = getSharedPreferences("Login",MODE_PRIVATE);
            preEditor = logPre.edit();

            preEditor.remove("user");
            preEditor.remove("pass");
            preEditor.apply();
            preEditor.commit();
            Intent intent = new Intent(ChooseArea.this, LoginActivity.class);
            finish();
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_area);

        //        Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Choose Area");
        actionBar.setDisplayHomeAsUpEnabled(true);


//        initallize view variables
        btn_chooseArea = (Button)findViewById(R.id.btn_chooseArea);
        edtChooseArea = (AutoCompleteTextView)findViewById(R.id.edt_chooseArea);
        btn_allRecord = (Button)findViewById(R.id.btn_AllData);

        sqliteDataBase = new SqliteDataBase(this);


        name = sqliteDataBase.getArea();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChooseArea.this,android.R.layout.simple_dropdown_item_1line,name);
        edtChooseArea.setThreshold(1);
        edtChooseArea.setAdapter(adapter);
        btn_chooseArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a_name = edtChooseArea.getText().toString();;
                if(a_name.isEmpty()){
                    Toast.makeText(ChooseArea.this,"Please Enter The Area",Toast.LENGTH_LONG).show();
                }else {
                    boolean c = true;
                    for(int i=0; i<name.length; i++){
                        if(a_name.equals(name[i])){
                            c = false;
                            edtChooseArea.setText("");
                            Intent intent = new Intent(ChooseArea.this, ChooseCustomer.class);
                            intent.putExtra("customer_area", a_name);
                            startActivity(intent);
                        }
                    }
                    if(c){
                        Toast.makeText(ChooseArea.this,"Please Enter The Correct Area",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn_allRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseArea.this, AllCartRecord.class);
                startActivity(intent);
            }
        });
    }




    public void getData(){
        String strings = "http://azadmedicinecompany.pk/orderspanel/alldata.php";
        StringRequest request = new StringRequest(Request.Method.POST, strings, new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean error = jsonObject.getBoolean("error");
                    if(error){
                        progressDialog.dismiss();
                        Toast.makeText(ChooseArea.this,jsonObject.getString("msg").toString(),Toast.LENGTH_LONG).show();
                    }else {
                        JSONArray jsonArray = jsonObject.getJSONArray("customer_tbl");
                        int[] codes = new int[jsonArray.length()];

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject product = jsonArray.getJSONObject(i);
                            codes[i] = Integer.parseInt(product.getString("c_code"));
                            int c_code = Integer.parseInt(product.getString("c_code"));
                            String c_name = product.getString("c_name");
                            String c_address = product.getString("c_address");
                            String  c_contact_no = product.getString("c_contact_no");
                            String c_area = product.getString("c_area");

                            ContentValues values = new ContentValues();
                            values.put("c_code", c_code);
                            values.put("c_name", c_name);
                            values.put("c_address", c_address);
                            values.put("c_contact_no", c_contact_no);
                            values.put("c_area", c_area);
                            sqliteDataBase.getCustomerFromMySql(values);
                        }

                        jsonArray = null;

                        jsonArray = jsonObject.getJSONArray("product_tbl");
                        int[] codes2 = new int[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject product = jsonArray.getJSONObject(i);
                            codes2[i] = Integer.parseInt(product.getString("p_code"));
                            int p_code = Integer.parseInt(product.getString("p_code"));
                            String p_description = product.getString("p_description");
                            Double p_tp = Double.parseDouble(product.getString("p_tp"));

                            ContentValues values = new ContentValues();
                            values.put("p_code", p_code);
                            values.put("p_description", p_description);
                            values.put("p_tp", p_tp);
                            sqliteDataBase.getProductFromMySql(values);
                        }

                        sqliteDataBase.delete(codes,codes2);
                        progressDialog.dismiss();
                        Toast.makeText(ChooseArea.this,jsonObject.getString("msg").toString(),Toast.LENGTH_LONG).show();
                        name = sqliteDataBase.getArea();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChooseArea.this,android.R.layout.simple_dropdown_item_1line,name);
                        edtChooseArea.setAdapter(adapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ChooseArea.this,"Connection Problem",Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();



                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ChooseArea.this);
        requestQueue.add(request);
        progressDialog = new ProgressDialog(ChooseArea.this);
        progressDialog.setTitle("Fetching Data");
        progressDialog.setMessage("Please wait a while...");
        progressDialog.show();

    }

}
