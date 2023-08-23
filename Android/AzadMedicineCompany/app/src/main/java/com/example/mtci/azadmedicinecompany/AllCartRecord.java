package com.example.mtci.azadmedicinecompany;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class AllCartRecord extends AppCompatActivity {
    ListView lv;
    AllCartRecordAdapter adapter;
    ArrayList<AllRecordDataHolderClass> list;
    SqliteDataBase sqliteDataBase;
    int total = 0;
    boolean check = true;
    Button btn_sended_order;
    TextView txt_error_msg;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            if(check) {
                finish();
            }else {
                recreate();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(check) {
            finish();
        }else {
            recreate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cart_record);

        //        Action bar
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("All Record");
        actionBar.setDisplayHomeAsUpEnabled(true);

        txt_error_msg = (TextView)findViewById(R.id.txt_error_msg);
        btn_sended_order = (Button)findViewById(R.id.btn_sended_order);

        sqliteDataBase = new SqliteDataBase(this);
        lv = (ListView) findViewById(R.id.allRecordListView);

        list = sqliteDataBase.getOrderMainTble(0);

        if(list.size() > 0) {
            adapter = new AllCartRecordAdapter(this, list);
            lv.setAdapter(adapter);
        }

        btn_sended_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = sqliteDataBase.getOrderMainTble(1);
                if(list.size()== 0){
                    Toast.makeText(AllCartRecord.this,"No Sended Record",Toast.LENGTH_SHORT).show();
                    list = sqliteDataBase.getOrderMainTble(0);
                }else {
                    lv.setVisibility(View.VISIBLE);
                    txt_error_msg.setVisibility(View.GONE);
                    btn_sended_order.setVisibility(View.GONE);

                    actionBar.setTitle("Sended Orders");
                    check = false;
                    adapter = new AllCartRecordAdapter(AllCartRecord.this,list);
                    lv.setAdapter(adapter);
                }

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int order_no = list.get(i).getOrder_no();
                Intent intent = new Intent(AllCartRecord.this,AllCartRecordList.class);
                intent.putExtra("order_no",order_no);
                intent.putExtra("order_date",list.get(i).getOrder_date());
                intent.putExtra("c_code",list.get(i).getC_code());
                intent.putExtra("c_name",list.get(i).getC_name());
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int index = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(AllCartRecord.this);
                builder.setIcon(R.drawable.warning_icon);
                builder.setTitle("Warning!");
                builder.setMessage("Are you sure to delete this item");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sqliteDataBase.deletDataFromOrderMainTbl(list.get(index).getOrder_no());
                        list.remove(index);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();


                return true;
            }
        });


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(check) {
            list = sqliteDataBase.getOrderMainTble(0);
        }else {
            list = sqliteDataBase.getOrderMainTble(1);
        }
        if(list.size()==0){

            txt_error_msg.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);
        }else {
            adapter = new AllCartRecordAdapter(this, list);
            lv.setAdapter(adapter);
        }

    }
}
