package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList item_ID, item_Name, item_Category, item_Price
            , item_Brand, item_PurchasedDate,
                        item_WarrantyDuration;
    Activity activity;
    int position;

    Animation translate_anim;

    CustomAdapter(Activity activity, Context context,
                  ArrayList item_ID, ArrayList item_Name,
                  ArrayList item_Category, ArrayList item_Price,

                 ArrayList item_Brand,
                 ArrayList item_PurchasedDate, ArrayList item_WarrantyDuration){

        this.activity = activity;
        this.context = context;
        this.item_ID = item_ID;
        this.item_Name = item_Name;
        this.item_Category = item_Category;
        this.item_Price = item_Price;
        this.item_Brand = item_Brand;
        this.item_PurchasedDate = item_PurchasedDate;
        this.item_WarrantyDuration = item_WarrantyDuration;



    }


    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {

        this.position = position;

        holder.tv_Item_ID.setText(String.valueOf(item_ID.get(position)));
        holder.tv_Item_Name.setText(String.valueOf(item_Name.get(position)));
        holder.tv_Item_Category.setText(String.valueOf(item_Category.get(position)));
        holder.tv_Item_Price.setText("$" + Double.parseDouble((String) item_Price.get(position)));
        //holder.et_Brand2.setText(String.valueOf(item_Brand.get(position)));
        //holder.et_PurchasedDate2.setText(String.valueOf(item_PurchasedDate.get(position)));
        //holder.et_WarrantyDuration2.setText(String.valueOf(item_WarrantyDuration.get(position)));
        holder.InventoryListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateItemDetails.class);
                intent.putExtra("id", String.valueOf(item_ID.get(position)));
                intent.putExtra("name", String.valueOf(item_Name.get(position)));
                intent.putExtra("category", String.valueOf(item_Category.get(position)));
                intent.putExtra("price", String.valueOf(item_Price.get(position)));
                intent.putExtra("brand", String.valueOf(item_Brand.get(position)));
                intent.putExtra("purchasedDate", String.valueOf(item_PurchasedDate.get(position)));
                intent.putExtra("warrantyDuration", String.valueOf(item_WarrantyDuration.get(position)));


                activity.startActivityForResult(intent, 1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return item_ID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Item_ID, tv_Item_Name, tv_Item_Category, tv_Item_Price;
        LinearLayout InventoryListLayout;

        EditText et_Brand2, et_PurchasedDate2, et_WarrantyDuration2;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            tv_Item_ID = itemView.findViewById(R.id.tv_Item_ID);
            tv_Item_Name = itemView.findViewById(R.id.tv_Item_Name);
            tv_Item_Category = itemView.findViewById(R.id.tv_Item_Category);
            tv_Item_Price = itemView.findViewById(R.id.tv_Item_Price);
            et_Brand2 = itemView.findViewById(R.id.et_Brand2);
            et_PurchasedDate2 = itemView.findViewById(R.id.et_PurchasedDate);
            et_WarrantyDuration2 = itemView.findViewById(R.id.et_WarrantyDuration2);
            InventoryListLayout = itemView.findViewById(R.id.InventoryListLayout);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            InventoryListLayout.setAnimation(translate_anim);


        }
    }
}
