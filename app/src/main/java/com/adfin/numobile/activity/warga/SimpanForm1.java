package com.adfin.numobile.activity.warga;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.activity.GlobalClass;
import com.adfin.numobile.model.DataNoTerkahir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SimpanForm1 extends AppCompatActivity {

    public static final String ROOT_URL = "http://numobile.id/";
    Button btnsave;


    String  strid_warga;
    String  strusername;
    String  strnama;
    String  strnoktp;
    String  strtempat_lahir;
    String  strtanggal_lahir;
    String  strjenis_kelamin;
    String  strstatus_perkawinan;
    String  stralamat;
    String  strprovinsi;
    String  strkabkot;
    String  strkecamatan;
    String  strdesa;
    String  strkode_pos;
    String  strtlp;
    String  strhp;
    String  stremail;
    String  strfb;
    String  strtwitter;
    String  strinstagram;
    String  strpathh;


    final Context context = this;

    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpan_form1);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();


        //FORM 1
        strusername = globalVariable.getUsername();
        strnama = globalVariable.getNamaLengkap();
        strnoktp = globalVariable.getNoKtp();
        strtempat_lahir = globalVariable.getTempatLahir();
        strtanggal_lahir = globalVariable.getTanggalLahir();
        strjenis_kelamin = globalVariable.getJenisKelamin();
        strstatus_perkawinan = globalVariable.getStatusPerkawinan();
        stralamat = globalVariable.getAlamat();
        strprovinsi = globalVariable.getProvinsi();
        strkabkot = globalVariable.getKabupaten();
        strkecamatan = globalVariable.getKecamatan();
        strdesa = globalVariable.getDesa();
        strkode_pos = globalVariable.getKodePos();
        strtlp = globalVariable.getTelepon();
        strhp = globalVariable.getHandphone();
        stremail = globalVariable.getEmail();
        strfb = globalVariable.getFacebook();
        strtwitter = globalVariable.getTwitter();
        strinstagram = globalVariable.getInstagram();
        strpathh = globalVariable.getPathh();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ambildataidwarga();

            }
        });
    }


    private void ambildataidwarga(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.getDataIdWarga(
                new Callback<DataNoTerkahir>()

                {
                    @Override
                    public void success(DataNoTerkahir datanoterkhir, Response response) {

                        strid_warga = datanoterkhir.getid_warga();
                        saveDataInput();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(SimpanForm1.this, "Kesalahan Koneksi Data" + toString(), Toast.LENGTH_LONG).show();

                    }
                }

        );
    }

    private void saveDataInput(){
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        //insertdataanggota ada di ModulAPI
        //urutan dan jumlah harus sama dengan yang di Model API
        api.insertform1(
                strid_warga.toString(),
                strusername.toString(),
                strnama.toString(),
                strnoktp.toString(),
                strtempat_lahir.toString(),
                strtanggal_lahir.toString(),
                strjenis_kelamin.toString(),
                strstatus_perkawinan.toString(),
                stralamat.toString(),
                strprovinsi.toString(),
                strkabkot.toString(),
                strkecamatan.toString(),
                strdesa.toString(),
                strkode_pos.toString(),
                strtlp.toString(),
                strhp.toString(),
                stremail.toString(),
                strfb.toString(),
                strtwitter.toString(),
                strinstagram.toString(),
                strpathh.toString(),

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;



                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        Toast.makeText(SimpanForm1.this, "Data Berhasil Di Simpan", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(SimpanForm1.this, "Kesalahan Koneksi Data" +error.getMessage(), Toast.LENGTH_LONG).show();
                        //btnsetting.setEnabled(true);
                    }
                }
        );
    }

}
