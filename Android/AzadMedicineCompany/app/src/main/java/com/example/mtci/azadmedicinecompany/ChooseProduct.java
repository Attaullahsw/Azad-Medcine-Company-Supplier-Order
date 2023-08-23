package com.example.mtci.azadmedicinecompany;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChooseProduct extends AppCompatActivity{

    //    Reference variabe
    boolean cheekCart = false;
    TextView tv;
    AutoCompleteTextView product_name;
    EditText product_quantity;
    TextView product_total;
    TextView product_per_item_price;

    SqliteDataBase sqliteDataBase;

    private List<P_Auto_Complet> p_autos;

    public static ArrayList<Product> product_list= null;
    Button btn_cart,btn_cancel;
    String c_name;
    int c_code;
    int p_code;
    double per_item;
    double total;
    int quantity;

    LinearLayout lin_total,lin_per_item;

    SharedPreferences logPre;
    SharedPreferences.Editor preEditor;


    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_product);

//        Action bar
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Product");

        sqliteDataBase = new SqliteDataBase(this);

        product_list= new ArrayList<Product>();

        logPre = getSharedPreferences("Login",MODE_PRIVATE);
        preEditor = logPre.edit();

        c_code = getIntent().getIntExtra("customer_code",0);
        if(c_code == 0){
            Toast.makeText(ChooseProduct.this,"Customer is not selected properly",Toast.LENGTH_LONG).show();
            finish();
        }
//        initiallize refernce variable
        lin_total = (LinearLayout)findViewById(R.id.lin_total);
        lin_per_item = (LinearLayout)findViewById(R.id.lin_per_item);
        product_name = findViewById(R.id.edt_productName);
        product_name.setFocusable(true);
        product_quantity = (EditText)findViewById(R.id.edt_productQuantity);
        product_per_item_price = (TextView) findViewById(R.id.txt_per_item_price);
        product_total = (TextView)findViewById(R.id.txt_product_total);
        btn_cart = (Button)findViewById(R.id.btn_addToCart);
        btn_cancel = (Button)findViewById(R.id.btn_cancelOrder);

        product_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                product_per_item_price.setText(String.valueOf(0.00));
                product_total.setText(String.valueOf(0.00));
            }
            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.toString().isEmpty()){
                    product_quantity.setEnabled(false);
                }

            }
        });

        product_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                p_code = AutoCompletProductAdapter.code;
                lin_per_item.setVisibility(View.VISIBLE);
                product_per_item_price.setText(sqliteDataBase.getProductPrice(p_code).toString());
                product_quantity.setEnabled(true);
                product_quantity.setText("");

            }
        });





        product_quantity.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((i == KeyEvent.KEYCODE_DPAD_CENTER) || (i == KeyEvent.KEYCODE_ENTER)){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    btn_cart.requestFocus();
                    return true;
                }
                return false;
            }
        });



        product_quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                lin_total.setVisibility(View.VISIBLE);
                DecimalFormat twoDForm = new DecimalFormat("#.##");
                double q =editable.toString().isEmpty()?0:Double.parseDouble(editable.toString());
                double total2 = Double.valueOf(q* Double.parseDouble(product_per_item_price.getText().toString()));
                product_total.setText(String.valueOf(twoDForm.format(total2)));
            }});



        Cursor cursor = sqliteDataBase.getProduct();
        p_autos = new ArrayList<>();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            int code = cursor.getInt(0);
            String name = cursor.getString(1);
            P_Auto_Complet PAutoComplet = new P_Auto_Complet(name,code);
            p_autos.add(PAutoComplet);
            cursor.moveToNext();
        }

        AutoCompletProductAdapter adapter = new AutoCompletProductAdapter(this,p_autos);


        product_name.setThreshold(1);
        product_name.setAdapter(adapter);
        //intillize Array list

        //Add to cart
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cheekCart = true;
                c_name= getIntent().getStringExtra("customer_name");
                String p_name = product_name.getText().toString();
                quantity = product_quantity.getText().toString().isEmpty()?0:Integer.parseInt(product_quantity.getText().toString());
                per_item = product_per_item_price.getText().toString().isEmpty()?0:Double.parseDouble(product_per_item_price.getText().toString());
                total = product_total.getText().toString().isEmpty()?0.00:Double.parseDouble(product_total.getText().toString());



                if(p_name.isEmpty()){
                    Toast.makeText(ChooseProduct.this,"Please Enter The Product Name",Toast.LENGTH_LONG).show();
                    product_name.requestFocus();
                }else if(quantity == 0){
                    Toast.makeText(ChooseProduct.this,"Please Enter The Product Quantity",Toast.LENGTH_LONG).show();
                    product_quantity.requestFocus();
                }else{
                    boolean c = true;
                    for(int i=0; i<p_autos.size(); i++){
                        if((p_autos.get(i).getCode()) == p_code) {
                            Product product = new Product(p_code,p_name,quantity,per_item,total);
                            product_list.add(product);
                            Toast.makeText(ChooseProduct.this,"Added Successfully",Toast.LENGTH_LONG).show();
//                set the cart Badge
                            tv.setText(String.valueOf(product_list.size()));
                            tv.setVisibility(View.VISIBLE);


//                Emty the field
                            product_name.setText("");
                            product_quantity.setText("");
                            product_per_item_price.setText("");
                            product_total.setText("");
                            lin_total.setVisibility(View.INVISIBLE);
                            lin_per_item.setVisibility(View.INVISIBLE);
                            product_name.requestFocus();

                            c = false;
                        }
                    }
                    if(c){
                        Toast.makeText(ChooseProduct.this,"Please Enter The Correct Product",Toast.LENGTH_LONG).show();
                    }


                }

            }
        });




        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChooseProduct.this);
                builder.setIcon(R.drawable.warning_icon);
                builder.setTitle("Warning!");
                builder.setMessage("Are you sure to Cancel");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                builder.setNegativeButton("Cancel",null);
                builder.show();

            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.cart);
        View itemChooser = item.getActionView();
        tv = (TextView)itemChooser.findViewById(R.id.actionbar_notifcation_textview);
        tv.setVisibility(View.INVISIBLE);
        itemChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseProduct.this,cartListView.class);
                intent.putExtra("customer_name",c_name);
                intent.putExtra("customer_code",c_code);
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
                preEditor.remove("user");
                preEditor.remove("pass");
                preEditor.apply();
                preEditor.commit();
                Intent intent = new Intent(ChooseProduct.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
//                set the cart Badge
        if(cheekCart){
            if(product_list.size() != 0){
                tv.setText(String.valueOf(product_list.size()));
                tv.setVisibility(View.VISIBLE);
            }else
                tv.setVisibility(View.INVISIBLE);
        }
    }



}
