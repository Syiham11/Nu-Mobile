package com.adfin.numobile.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adfin.numobile.R;
import com.squareup.picasso.Picasso;

// Created by Siti on 12/4/2016.

public class ListAdapterAll extends  RecyclerView.Adapter<ViewHolderAll>{

    private final Context context;

    private String [] imag={};
    private String [] name={};
    private String [] lain={};

    private LayoutInflater inflater;

    public ListAdapterAll(Context context, String[] imag, String[] name, String[] lain) {
        inflater = LayoutInflater.from(context);
        this.context=context;
        this.imag = imag;
        this.name = name;
        this.lain = lain;
    }
    @Override
    public ViewHolderAll onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.list_content, parent, false);

        return new ViewHolderAll(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderAll holder, int position) {
        holder.tv1.setText(this.name[position]);
        holder.tv1.setOnClickListener(clickListener);
        holder.tv1.setTag(holder);
        if( this.lain.length > 0 )
            holder.tv2.setText(this.lain[position]);
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);
        if(this.imag[position] != null && !this.imag[position].equals("null")) {
            Picasso.with(this.context)
                    .load(this.imag[position])
                    .resize(70, 70)
                    .into(holder.imageView);
        }else{
            int[] imgArr = new int[] {R.drawable.numobile};

            Picasso.with(this.context)
                    .load(imgArr[0])
                    .placeholder(R.drawable.numobile)
                    .into(holder.imageView);
        }
    }

    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ViewHolderAll vholder = (ViewHolderAll) v.getTag();
            int position = vholder.getAdapterPosition();
            Toast.makeText(context, "Menu ini berada di posisi " + position, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public int getItemCount() {
        return this.name.length;
    }
}


