package com.example.mtci.azadmedicinecompany;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AttaUllah on 5/9/2018.
 */

public class AllCartRecordAdapter extends ArrayAdapter<AllRecordDataHolderClass> {
    private final Context context;
    private final ArrayList<AllRecordDataHolderClass> list;

    public AllCartRecordAdapter(@NonNull Context context, ArrayList<AllRecordDataHolderClass> list) {
        super(context,R.layout.activity_all_cart_record,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View allCartView = inflater.inflate(R.layout.all_cart_record,parent,false);

        TextView txt_c_name = (TextView)allCartView.findViewById(R.id.txt_all_record_c_name);
        TextView txt_o_number = (TextView)allCartView.findViewById(R.id.txt_order_number);
        TextView txt_order_date = (TextView)allCartView.findViewById(R.id.txt_order_date);

        txt_c_name.setText(list.get(position).getC_name().toString());
        txt_o_number.setText(String.valueOf(list.get(position).i));
        txt_order_date.setText(list.get(position).getOrder_date().toString());

        return allCartView;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
