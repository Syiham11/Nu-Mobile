package com.adfin.numobile.module.warga.model;
// Created by Siti on 11/4/2016.

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

public class Warga {

    private RestAdapter adapter;
    private String mUrl;
    private static Context mContext;

    public static Warga with(Context context){
        mContext = context;
        return new Warga();
    }

    public RestAdapter init(String url){
        mUrl = url;
        adapter = new RestAdapter.Builder()
                .setEndpoint(mUrl)
                .build();

        //point = adapter.create(Point.class);

        return adapter;
    }

    public class CallbackWarga
    {
        private List<DataWarga> DataWarga = new ArrayList<>();

        public List<DataWarga> getDataWarga() {
            return DataWarga;
        }
    }

    public class DataWarga
    {
        private String id_warga;
        private String username;
        private String nama;
        private String photo;

        public void setid_warga(String id_warga) {
            this.id_warga = id_warga;
        }
        public String getid_warga() {
            return id_warga;
        }

        public void setusername(String username) {
            this.username = username;
        }
        public String getusername() {
            return username;
        }

        public void setnama(String nama) {
            this.nama = nama;
        }
        public String getnama() {
            return nama;
        }

        public void setphoto(String photo) {
            this.photo = photo;
        }
        public String getphoto() {
            return mUrl + "NUMobile/" + photo;
        }
    }
}
