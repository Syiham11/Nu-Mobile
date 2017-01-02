package com.adfin.numobile.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adfin.numobile.R;

// Created by Siti on 12/4/2016.

class ViewHolderAll extends RecyclerView.ViewHolder{
    TextView tv0, tv1,tv2; //deklarasi textview
    ImageView imageView;  //deklarasi imageview

    ViewHolderAll(View itemView) {
        super(itemView);

        tv0=(TextView) itemView.findViewById(R.id.idData);
        tv1=(TextView) itemView.findViewById(R.id.id_name);
        tv2=(TextView) itemView.findViewById(R.id.id_lain);
        imageView= (ImageView) itemView.findViewById(R.id.id_icon);
    }
}
