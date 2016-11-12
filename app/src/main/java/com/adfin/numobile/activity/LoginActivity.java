package com.adfin.numobile.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.model.DataWarga;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;
    EditText editUsername, editPassword;
    ProgressBar progressBar;
    TextView textError;
    String strid_user, strusername2, strpassword2,username,password,
    strlatitude, strlongtitude, strflag, strstatusmember;
    GPSTracker gps;

    public static final String ROOT_URL = "http://ciptakarya.pu.go.id/setditjen/kkntematik/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSpinner);
        textError = (TextView) findViewById(R.id.errorinfo);
        progressBar.setVisibility(View.GONE);
        textError.setVisibility(View.GONE);

        gps = new GPSTracker(LoginActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            strlatitude = Double.toString(latitude);
            strlongtitude = Double.toString(longitude);

            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editUsername.getText().toString().equalsIgnoreCase("")){
                    AlertDialog.Builder ad= new AlertDialog.Builder(LoginActivity.this);
                    ad.setTitle("Peringatan");
                    ad .setMessage("Username tidak boleh kosong");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    ad .setIcon(android.R.drawable.ic_dialog_alert);
                    ad .show();
                }else if(editPassword.getText().toString().equalsIgnoreCase(""))
                {
                    AlertDialog.Builder ad= new AlertDialog.Builder(LoginActivity.this);
                    ad.setTitle("Peringatan");
                    ad .setMessage("Password tidak boleh kosong");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    ad .setIcon(android.R.drawable.ic_dialog_alert);
                    ad .show();
                }else {
                    username = editUsername.getText().toString();
                    password = editPassword.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    ceksudahdaftar();
                }
//                if (  ( !editUsername.getText().toString().equals("")) && ( !editPassword.getText().toString().equals("")) ) {
//                    username = editUsername.getText().toString();
//                    password = editPassword.getText().toString();
//                    progressBar.setVisibility(View.VISIBLE);
//                    ceksudahdaftar();
//                    return;
//                    //Toast.makeText(LoginActivity.this, "Username  "+editUsername.getText().toString(), Toast.LENGTH_LONG).show();
//
//                } else if ( ( !editUsername.getText().toString().equals("")) ) {
//                    textError.setVisibility(View.VISIBLE);
//                    textError.setText("Masukan kata sandi anda");
//                    return;
//                } else if ( ( !editPassword.getText().toString().equals("")) ) {
//                    textError.setVisibility(View.VISIBLE);
//                    textError.setText("Masukan nama pengguna anda");
//                    return;
//                } else {
//                    textError.setVisibility(View.VISIBLE);
//                    textError.setText("Masukan nama pengguna dan kata sandi anda");
//                    return;
//                }


            }
        });
    }

    private void ceksudahdaftar(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.ceksudahdaftar(
                username.toString(),
                password.toString(),
                new Callback<DataWarga>()

                {
                    @Override
                    public void success(DataWarga datawarga, Response response) {

                        strusername2 = datawarga.getusername();

                        //Toast.makeText(LoginActivity.this, "benar masuk "+strusername2.toString(), Toast.LENGTH_LONG).show();


                        // if (striddevice2.equals(etiddevice.getText().toString())) {
                        if (strusername2 == null) {
                            //if (striddevice2.equals(striddevice.toString())) {
                            Toast.makeText(LoginActivity.this, "Data Tidak Ada ", Toast.LENGTH_LONG).show();

                        } else {

                            strpassword2 = datawarga.getpassword();
//                            strlatitude = dataanggota.getlatitude();
//                            strlongtitude = dataanggota.getlongtitude();
                            //strflag = dataanggota.getflag();
                            //strstatusmember = dataanggota.getstatusmember();

                            updateLocation();
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            intent.putExtra("id_user",username);
//                            startActivity(intent);
//                            finish();
                        }


                        //
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(LoginActivity.this, "Kesalahan Koneksi Data"+toString(), Toast.LENGTH_LONG).show();

                    }
                }

        );


    }


    private void updateLocation(){
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        //Defining the method insertuser of our interface
        //insertdataanggota ada di ModulAPI
        api.simpanLatLong(
                //urutan harus sama dengan yang di model API
                username.toString(),
                strlatitude.toString(),
                strlongtitude.toString(),

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
                        //   btnsetting.setEnabled(true);

                        //save();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        //Toast.makeText(LoginActivity.this, "Data Berhasil Di Simpan", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(LoginActivity.this, "Kesalahan Koneksi Data", Toast.LENGTH_LONG).show();
                        //btnsetting.setEnabled(true);
                    }
                }
        );
    }

}


