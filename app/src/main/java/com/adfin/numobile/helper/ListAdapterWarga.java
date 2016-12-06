package com.adfin.numobile.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adfin.numobile.R;
import com.squareup.picasso.Picasso;

/**
 * Created by prakasa on 22/11/16.
 */

public class ListAdapterWarga extends  RecyclerView.Adapter<WargaViewHolder> {

    //deklarasi variable context

    private final Context context;

    String [] imag={};
    String [] name={};
    String [] lain={};
    // menampilkan list item dalam bentuk text dengan tipe data string dengan variable name

    LayoutInflater inflater;

    public ListAdapterWarga(Context context, String[] imag, String[] name, String[] lain) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.imag = imag;
        this.name = name;
        this.lain = lain;
    }

    @Override
    public WargaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.list_content, parent, false);

        WargaViewHolder viewHolder=new WargaViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WargaViewHolder holder, int position) {
        holder.tv1.setText(this.name[position]);
        holder.tv1.setOnClickListener(clickListener);
        holder.tv1.setTag(holder);
        holder.tv2.setText(this.lain[position]);
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);
        if(this.imag[position] != null && !this.imag[position].equals("null"))
            Picasso.with(this.context)
                    .load("http://www.terpusat.com/NUMobile/" + this.imag[position])
                    .resize(70, 70)
                    .into(holder.imageView);
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //member aksi saat cardview diklik berdasarkan posisi tertentu
            WargaViewHolder vholder = (WargaViewHolder) v.getTag();

            int position = vholder.getPosition();

            Toast.makeText(context, "Menu ini berada di posisi " + position, Toast.LENGTH_LONG).show();
        }
    };



    @Override
    public int getItemCount() {
        return this.name.length;
    }
}
