package com.adfin.numobile.module.warga.model;
// Created by Siti on 11/4/2016.

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;

import static com.google.android.gms.internal.zzid.runOnUiThread;

interface Point {
    @GET("/NUMobile/getusers.php")
    void get(Callback<Warga.CallbackWarga> callback);
}

public class Warga {

    private static Context mContext;
    private Point point;
    private String mUrl = "http://www.terpusat.com";
    private static List<DataWarga> result = new ArrayList<>();

    public static Warga with(Context context){
        mContext = context;
        return new Warga();
    }

    public Warga init(String url){
        mUrl = url;
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(mUrl)
                .build();

        point = adapter.create(Point.class);

        return this;
    }

    public List<DataWarga> get() {
        point.get(
                new Callback<CallbackWarga>()
                {
                    @Override
                    public void success(CallbackWarga warga, Response response) {
                        List<DataWarga> loc = new ArrayList<>();
                        loc = warga.getDataWarga();

                        final List<DataWarga> finalLoc = loc;
                        runOnUiThread(new Runnable() {
                            public void run() {
                                result = finalLoc;
                                Toast.makeText(mContext, "Data Berhasil Diambil", Toast.LENGTH_LONG).show();
                            }
                        });

                        final String[] namaWarga = new String[loc.size()];
                        final String[] imageWarga = new String[loc.size()];

                        for (int i = 0; i < result.size(); i++) {
                            Log.e("Kalo Dari Model Nama", result.get(i).getnama());
                            Log.e("Kalo Dari Model username", result.get(i).getusername());
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
                }
        );
        return result;
    }

    class CallbackWarga
    {
        private List<DataWarga> DataWarga = new ArrayList<>();

        List<DataWarga> getDataWarga() {
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
