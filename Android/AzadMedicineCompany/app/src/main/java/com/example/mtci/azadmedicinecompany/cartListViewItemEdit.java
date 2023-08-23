package com.example.mtci.azadmedicinecompany;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.mtci.azadmedicinecompany.AddProduct.products;

public class cartListViewItemEdit extends AppCompatActivity {

    TextView tv;
    AutoCompleteTextView product_name;
    EditText product_quantity;
    TextView product_total,product_per_item_price;

    SqliteDataBase sqliteDataBase;

    LinearLayout lin_total,lin_per_item;

    ArrayList<P_Auto_Complet> p_autos;

    int index;
    Product temp;
    int p_code;
    double total;
    double perItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list_view_item_edit);
        product_name = (AutoCompleteTextView)findViewById(R.id.edt_productName);
        product_name.setFocusable(true);
        product_quantity = (EditText)findViewById(R.id.edt_productQuantity);
        product_per_item_price = (TextView) findViewById(R.id.txt_item_price);
        product_total = (TextView)findViewById(R.id.txt_product_total_edit);

        lin_per_item = (LinearLayout)findViewById(R.id.lin_per_item_edit);
        lin_total = (LinearLayout)findViewById(R.id.lin_total_edit);

        //        Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Item");

        sqliteDataBase = new SqliteDataBase(this);

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


        //index of the product item to edit
        //to check weather it is from cartlistview or AllcartRecordList
        if(getIntent().getBooleanExtra("check",false) == false) {
            index = Integer.parseInt(getIntent().getStringExtra("index"));
        }
        putData();

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


        product_quantity.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((i == KeyEvent.KEYCODE_DPAD_CENTER) || (i == KeyEvent.KEYCODE_ENTER)){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
                double total2 = Double.valueOf(twoDForm.format (q* Double.parseDouble(product_per_item_price.getText().toString())));
                product_total.setText(String.valueOf(total2));
            }});

        product_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                p_code = AutoCompletProductAdapter.code;
                product_per_item_price.setText(sqliteDataBase.getProductPrice(p_code).toString());
                product_quantity.setEnabled(true);
                product_quantity.setText("");

            }
        });




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_edit_menu, menu);
        return super.onCreateOptionsMenu(menu);}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.edit_done:
                update();
                finish();
                break;
            case R.id.edit_cancel:
                finish();
                break;
        }


        return super.onOptionsItemSelected(item);
    }


    void putData(){
        if(getIntent().getBooleanExtra("check",false) == false) {
            temp = products.get(index);
        }else {

            temp = new Product(getIntent().getIntExtra("p_code",0),getIntent().getStringExtra("p_name"),
                    getIntent().getIntExtra("p_quantity",0),getIntent().getDoubleExtra("p_per_item",0),
                    getIntent().getDoubleExtra("p_total",0));
        }
        //String Cname =temp.getC_name();
        p_code = temp.getP_code();
        String pname =temp.getProduct_name();
        int quantity = temp.getProduct_quantity();
        perItem = temp.getProduct_per_item_price();
        total = temp.getProduct_total();


        product_name.setText(pname);
        product_quantity.setText(String.valueOf(quantity));
        product_per_item_price.setText(String.valueOf(perItem));
        product_total.setText(String.valueOf(total));
    }

    void update() {
        String p_name = product_name.getText().toString();
        int quantity = product_quantity.getText().toString().isEmpty() ? 0 : Integer.parseInt(product_quantity.getText().toString());
        perItem =  product_per_item_price.getText().toString().isEmpty()? 0 : Double.parseDouble(product_per_item_price.getText().toString());
        total =  product_total.getText().toString().isEmpty()?0.00:Double.parseDouble(product_total.getText().toString());


        if (p_name.isEmpty()) {
            Toast.makeText(cartListViewItemEdit.this, "Please Enter The Product Name", Toast.LENGTH_LONG).show();
            product_name.requestFocus();
        } else if (quantity == 0) {
            Toast.makeText(cartListViewItemEdit.this, "Please Enter The Product Quantity", Toast.LENGTH_LONG).show();
            product_quantity.requestFocus();
        } else {
            //to check weather it is from cartlistview or AllcartRecordList
            if(getIntent().getBooleanExtra("check",false)){
                ContentValues values = new ContentValues();
                values.put("p_code",p_code);
                values.put("order_details_quantity",quantity);
                sqliteDataBase.updateRecordToOrderDetailTbl(values,getIntent().getIntExtra("order_id",0));
            }else {
                Product product = new Product(p_code, p_name, quantity, perItem, total);
                products.set(index,product);
            }







//                Emty the field
            product_name.setText("");
            product_quantity.setText("");
            product_per_item_price.setText("");
            product_name.requestFocus();

        }
    }
}

