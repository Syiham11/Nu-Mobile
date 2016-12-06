package com.adfin.numobile.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adfin.numobile.R;

/**
 * Created by Siti on 12/4/2016.
 */

public class ViewHolderAll extends RecyclerView.ViewHolder{
    TextView tv1,tv2; //deklarasi textview
    ImageView imageView;  //deklarasi imageview

    public ViewHolderAll(View itemView) {
        super(itemView);

        tv1= (TextView) itemView.findViewById(R.id.id_name);
        tv2= (TextView) itemView.findViewById(R.id.id_lain);
        imageView= (ImageView) itemView.findViewById(R.id.id_icon);
    }
}
