//package com.example.mtci.azadmedicinecompany;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.text.InputType;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.ArrayAdapter;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.SectionIndexer;
//import android.widget.TextView;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
//import static com.example.mtci.azadmedicinecompany.AddProduct.cheekCart;
//import static com.example.mtci.azadmedicinecompany.AddProduct.products;
//import static com.example.mtci.azadmedicinecompany.AddProduct.tv;
//
//
//public class ProductListViewAdapter extends ArrayAdapter<AllProductDataHolderClass> implements SectionIndexer {
//    private final Context context;
//    private ArrayList<AllProductDataHolderClass> list;
//    private char name = '9';
//    SqliteDataBase sqliteDataBase;
//    ArrayList<Integer> section;
//
//    public ProductListViewAdapter(@NonNull Context context, ArrayList<AllProductDataHolderClass> list) {
//        super(context,R.layout.add_product_list ,list);
//        this.context = context;
//        this.list = list;
//        sqliteDataBase = new SqliteDataBase(context);
//    }
//
//    @NonNull
//    @Override
//    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View conView = inflater.inflate(R.layout.add_product_list,parent,false);
//
//
//        char pname = list.get(position).getP_name().toLowerCase().charAt(0);
//
//        TextView textView = (TextView) conView.findViewById(R.id.separator);
//        TextView textView2 = (TextView) conView.findViewById(R.id.product_desc);
//        TextView textViewCode = (TextView) conView.findViewById(R.id.product_code);
//        final CheckBox checkBox = (CheckBox) conView.findViewById(R.id.chb_product);
//        if(pname != name){
//            name = pname;
//            textView.setText(String.valueOf(name).toUpperCase().toString());
//        }else {
//            textView.setVisibility(View.GONE);
//        }
//        checkBox.setChecked(list.get(position).isCheck());
//        conView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkBox.setChecked(true);
//            }
//        });
//
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                list.get(position).setCheck(compoundButton.isChecked());
//                if(checkBox.isChecked()){
//
//                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
//                    alertDialog.setCancelable(false);
//                    alertDialog.setTitle("Quantity");
//                    final EditText input = new EditText(context);
//                    input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                        @Override
//                        public void onFocusChange(View v, boolean hasFocus) {
//                            input.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                                    imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
//                                }
//                            });
//                        }
//                    });
//                    input.requestFocus();
//                    input.setSingleLine();
//
//                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
//                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                            LinearLayout.LayoutParams.MATCH_PARENT,
//                            LinearLayout.LayoutParams.MATCH_PARENT);
//                    input.setLayoutParams(lp);
//                    alertDialog.setView(input);
//
//
//                    alertDialog.setPositiveButton("Add",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    int q = Integer.parseInt(input.getText().toString());
//                                    Double per =Double.parseDouble(sqliteDataBase.getProductPrice(list.get(position).getP_code()).toString());
//                                    DecimalFormat twoDForm = new DecimalFormat("#.##");
//                                    Double total = Double.valueOf(twoDForm.format((q*per)));
//                                    Product product = new Product(list.get(position).getP_code(),list.get(position).getP_name(),q,
//                                            per,total);
//                                    products.add(product);
//                                    cheekCart = true;
//                                    //                set the cart Badge
//                                    tv.setText(String.valueOf(products.size()));
//                                    tv.setVisibility(View.VISIBLE);
//
//                                }
//                            });
//
//                    alertDialog.setNegativeButton("Cancel",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                    checkBox.setChecked(false);
//                                }
//                            });
//
//                    alertDialog.show();
//                    conView.setClickable(false);
//                    checkBox.setClickable(false);
//                }else {
//                    conView.setClickable(true);
//                    checkBox.setClickable(true);
//                }
//            }
//        });
//        textView2.setText(list.get(position).getP_name().toString());
//        textViewCode.setText(String.valueOf(list.get(position).getP_code()));
//        checkBox.setChecked(list.get(position).isCheck());
//        return conView;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return getCount();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//
//    public Object[] getSections() {
//        String[] chars = new String[SideSelector.ALPHABET.length];
//        for (int i = 0; i < SideSelector.ALPHABET.length; i++) {
//            chars[i] = String.valueOf(SideSelector.ALPHABET[i]);
//        }
//        return chars;
//    }
//
//    public int getPositionForSection(int i) {
//
//        return (int) (getCount() * ((float)i/(float)getSections().length));
//    }
//
//    public int getSectionForPosition(int i) {
//        return 0;
//    }
//
//}
