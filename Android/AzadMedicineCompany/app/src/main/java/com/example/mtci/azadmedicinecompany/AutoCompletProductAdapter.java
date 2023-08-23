package com.example.mtci.azadmedicinecompany;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AttaUllah on 5/7/2018.
 */

public class AutoCompletProductAdapter extends ArrayAdapter<P_Auto_Complet> {
    public static int code;
    private List<P_Auto_Complet> Prod_auto;
    public AutoCompletProductAdapter(@NonNull Context context, @NonNull List<P_Auto_Complet> product_auto_complet) {
        super(context, 0, product_auto_complet);
        Prod_auto = new ArrayList<>(product_auto_complet);
    }


    @NonNull
    @Override
    public Filter getFilter() {
        return productFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.product_tv_autocomplete,parent,false
            );
        }

        TextView txt_p_name = convertView.findViewById(R.id.txt_p_name);
        TextView txt_p_code = convertView.findViewById(R.id.txt_p_code);

        P_Auto_Complet PAutoComplet = getItem(position);
        if(PAutoComplet != null){
            txt_p_name.setText(PAutoComplet.getName());
            txt_p_code.setText(String.valueOf(PAutoComplet.getCode()));
        }

        return convertView;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<P_Auto_Complet> suggestion = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                suggestion.addAll(Prod_auto);
            }else{
                String filterPattren = charSequence.toString().toLowerCase().trim();

                for(P_Auto_Complet p : Prod_auto){
                    if(p.getName().toLowerCase().contains(filterPattren)){
                        suggestion.add(p);
                    }
                    if(String.valueOf(p.getCode()).toLowerCase().contains(filterPattren)){
                        suggestion.add(p);
                    }
                }
            }

            results.values = suggestion;
            results.count = suggestion.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            code = ((P_Auto_Complet) resultValue).getCode();
            return ((P_Auto_Complet) resultValue).getName();
        }
    };
}
