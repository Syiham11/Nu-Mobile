package com.adfin.numobile.module.warga.controller;
// Created by prakasa on 19/12/16.

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.adfin.numobile.App;
import com.adfin.numobile.R;
import com.adfin.numobile.module.warga.model.Warga;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Home extends App {

    TextView text;

    List<Warga.DataWarga> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContext(this);

        setContentView(R.layout.content_kosong);

        text = (TextView) findViewById(R.id.textKosong);

        updateList();
    }

    private void updateList() {

        api().get(new Callback<Warga.CallbackWarga>()
        {
            @Override
            public void success(Warga.CallbackWarga warga, Response response) {
                result = warga.getDataWarga();

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(mContext, "Data Berhasil Diambil", Toast.LENGTH_LONG).show();
                    }
                });

                /*final String[] namaWarga = new String[result.size()];
                final String[] imageWarga = new String[result.size()];*/

                for (int i = 0; i < result.size(); i++) {
                    Log.e("Nama", result.get(i).getnama());
                    Log.e("Username", result.get(i).getusername());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(mContext, "Koneksi tidak stabil", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
