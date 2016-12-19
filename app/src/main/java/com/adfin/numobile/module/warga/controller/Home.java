package com.adfin.numobile.module.warga.controller;
// Created by prakasa on 19/12/16.

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.adfin.numobile.App;
import com.adfin.numobile.R;
import com.adfin.numobile.module.warga.model.Warga;

import java.util.List;


public class Home extends App {

    private List<Warga.DataWarga> dataWarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_kosong);

        dataWarga = Warga.with(getApplicationContext()).init("http://www.terpusat.com").get();

        TextView text = (TextView) findViewById(R.id.textKosong);

        text.setText( String.valueOf(dataWarga.size()) );

        /*for (int i = 0; i < dataWarga.size(); i++) {
            Log.e("Id Saya", dataWarga.get(i).getid_warga());
            Log.e("Nama Saya", dataWarga.get(i).getnama());
            Log.e("Username Saya", dataWarga.get(i).getusername());
        }*/
    }
}
