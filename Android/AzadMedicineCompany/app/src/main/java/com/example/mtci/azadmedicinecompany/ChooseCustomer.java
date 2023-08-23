package com.example.mtci.azadmedicinecompany;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChooseCustomer extends AppCompatActivity {

    //    Reference view variable
    AutoCompleteTextView edtChooseCustomer;
    Button btn_chooseCustomer;

    List<P_Auto_Complet> p_autos;

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
        setContentView(R.layout.activity_choose_customer);
        //        Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Choose Customer");
        actionBar.setDisplayHomeAsUpEnabled(true);


//        initallize view variables
        btn_chooseCustomer = (Button)findViewById(R.id.btn_chooseCustomer);
        edtChooseCustomer = (AutoCompleteTextView)findViewById(R.id.edt_chooseCustomer);

        String customerArea = getIntent().getStringExtra("customer_area");
        SqliteDataBase db = new SqliteDataBase(this);
        Cursor cursor = db.getCustomer(customerArea);
        p_autos = new ArrayList<>();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            int c_code = cursor.getInt(0);
            String name = cursor.getString(1);
            P_Auto_Complet PAutoComplet = new P_Auto_Complet(name,c_code);
            p_autos.add(PAutoComplet);
            cursor.moveToNext();
        }
        AutoCompletProductAdapter adapter = new AutoCompletProductAdapter(this,p_autos);
        edtChooseCustomer.setThreshold(1);
        edtChooseCustomer.setAdapter(adapter);
        btn_chooseCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c_name = edtChooseCustomer.getText().toString();
                if(c_name.isEmpty()){
                    Toast.makeText(ChooseCustomer.this,"Please Enter The Customer Name",Toast.LENGTH_LONG).show();
                }else {
                    boolean c = true;
                    for(int i=0; i<p_autos.size(); i++){
                        if(c_name.equals(p_autos.get(i).getName())) {
                            c = false;
                            Intent intent = new Intent(ChooseCustomer.this, AddProduct.class);
                            intent.putExtra("customer_name", c_name);
                            intent.putExtra("customer_code", AutoCompletProductAdapter.code);
                            finish();
                            startActivity(intent);

                        }
                    }
                    if(c){
                        Toast.makeText(ChooseCustomer.this,"Please Enter The Correct Customer",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });




    }
}
