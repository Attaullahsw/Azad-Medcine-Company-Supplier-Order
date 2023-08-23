package com.example.mtci.azadmedicinecompany;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by AttaUllah on 4/5/2018.
 */

public class CartListViewAdapter extends ArrayAdapter<Product>
{
    private final Context context;
    private final ArrayList<Product> list;

    public CartListViewAdapter(@NonNull Context context, ArrayList<Product> list) {
        super(context, R.layout.cart_layout,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cartView = inflater.inflate(R.layout.cart_layout,parent,false);

        TextView txtProductName = (TextView)cartView.findViewById(R.id.txt_productName);
        TextView txtProductQuantity = (TextView)cartView.findViewById(R.id.txt_quantity);

        DecimalFormat twoDForm = new DecimalFormat("#.##");
        Product p =getItem(position);
        cartView.setTag(p);
        txtProductName.setText(p.getProduct_name());
        txtProductQuantity.setText(String.valueOf(p.getProduct_quantity()));

        return cartView;
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

