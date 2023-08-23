package com.example.mtci.azadmedicinecompany;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import static com.example.mtci.azadmedicinecompany.AddProduct.cheekCart;
import static com.example.mtci.azadmedicinecompany.AddProduct.products;
import static com.example.mtci.azadmedicinecompany.AddProduct.tv;

public class ProductListViewAdapter2 extends ArrayAdapter<AllProductDataHolderClass> implements SectionIndexer {

    private final Context context;
    private ArrayList<AllProductDataHolderClass> list;
    HashMap<String,Integer> alphaIndexer;
    SqliteDataBase sqliteDataBase;
    String[] sections;
    char name = '9';

    public ProductListViewAdapter2(@NonNull Context context, ArrayList<AllProductDataHolderClass> list) {
        super(context,R.layout.add_product_list ,list);
        this.context = context;
        this.list = list;
        sqliteDataBase = new SqliteDataBase(context);
        alphaIndexer = new HashMap<String, Integer>();
        int size = list.size();
        for(int i=0; i< size; i++){
            String s = list.get(i).getP_name();
            String ch = s.substring(0,1);
            ch = ch.toUpperCase();
            if(!alphaIndexer.containsKey(ch))
                this.list.get(i).setSeperator(true);
                alphaIndexer.put(ch,i);
        }
        Set<String> sectionLetter = alphaIndexer.keySet();
        ArrayList<String> sectionList = new ArrayList<String>(sectionLetter);
        Collections.sort(sectionList);
        sections = new String[sectionList.size()];
        sectionList.toArray(sections);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View conView;
        final TextView textView2,textViewCode;
        final CheckBox checkBox;


        char pname = list.get(position).getP_name().toLowerCase().charAt(0);

            conView = inflater.inflate(R.layout.add_product_list,parent,false);

            textView2 = (TextView) conView.findViewById(R.id.product_desc);
            textViewCode = (TextView) conView.findViewById(R.id.product_code);
            checkBox = (CheckBox) conView.findViewById(R.id.chb_product);
            TextView textView = (TextView) conView.findViewById(R.id.separator);
        if(getItemViewType(position) == 0){
            name = pname;
            textView.setText(String.valueOf(name).toUpperCase().toString());
        }else {
            textView.setVisibility(View.GONE);
        }

        checkBox.setChecked(list.get(position).isCheck());
        conView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(true);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                list.get(position).setCheck(compoundButton.isChecked());
                if(checkBox.isChecked()){

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setCancelable(false);
                    alertDialog.setTitle("Quantity");
                    final EditText input = new EditText(context);
                    input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            input.post(new Runnable() {
                                @Override
                                public void run() {
                                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
                                }
                            });
                        }
                    });
                    input.requestFocus();
                    input.setSingleLine();

                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setLayoutParams(lp);
                    alertDialog.setView(input);


                    alertDialog.setPositiveButton("Add",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });

                    alertDialog.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    checkBox.setChecked(false);
                                }
                            });
                    final AlertDialog dialog = alertDialog.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(input.getText().toString().isEmpty()){
                                Toast.makeText(context,"Enter the quantity",Toast.LENGTH_SHORT).show();
                            }else {
                                int q = Integer.parseInt(input.getText().toString());
                                Double per = Double.parseDouble(sqliteDataBase.getProductPrice(list.get(position).getP_code()).toString());
                                DecimalFormat twoDForm = new DecimalFormat("#.##");
                                Double total = Double.valueOf(twoDForm.format((q * per)));
                                Product product = new Product(list.get(position).getP_code(), list.get(position).getP_name(), q,
                                        per, total);
                                products.add(product);
                                cheekCart = true;
                                //                set the cart Badge
                                tv.setText(String.valueOf(products.size()));
                                tv.setVisibility(View.VISIBLE);
                                dialog.dismiss();
                            }

                        }
                    });
                    conView.setClickable(false);
                    checkBox.setClickable(false);
                }else {
                    conView.setClickable(true);
                    checkBox.setClickable(true);
                }
            }
        });
        textView2.setText(list.get(position).getP_name().toString());
        textViewCode.setText(String.valueOf(list.get(position).getP_code()));
        checkBox.setChecked(list.get(position).isCheck());
        return conView;

    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int p) {
        if(list.get(p).isSeperator()){
            return 0;
        }
        return 1;


    }

    @Nullable
    @Override
    public AllProductDataHolderClass getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object[] getSections() {
        return sections;
    }

    @Override
    public int getPositionForSection(int i) {
        return alphaIndexer.get(sections[i]);
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }
}
