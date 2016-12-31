package com.adfin.numobile.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.helper.Nengkene;
import com.adfin.numobile.helper.Session;
import com.adfin.numobile.model.DataWarga;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    Nengkene location;

    Button buttonLogin;
    EditText editUsername, editPassword;
    ProgressBar progressBar;
    TextView textError;

    String username, password;

    public static final String ROOT_URL = "http://ciptakarya.pu.go.id/setditjen/kkntematik/";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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


        location = Nengkene.with(getApplicationContext()).start().get();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editUsername.getText().toString().equalsIgnoreCase("")) {
                    AlertDialog.Builder ad = new AlertDialog.Builder(LoginActivity.this);
                    ad.setTitle("Peringatan");
                    ad.setMessage("Username tidak boleh kosong");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    ad.setIcon(android.R.drawable.ic_dialog_alert);
                    ad.show();
                } else if (editPassword.getText().toString().equalsIgnoreCase("")) {
                    AlertDialog.Builder ad = new AlertDialog.Builder(LoginActivity.this);
                    ad.setTitle("Peringatan");
                    ad.setMessage("Password tidak boleh kosong");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    ad.setIcon(android.R.drawable.ic_dialog_alert);
                    ad.show();
                } else {
                    username = editUsername.getText().toString();
                    password = editPassword.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    ceksudahdaftar();
                }
            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void ceksudahdaftar() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://numobile.id") //Setting the Root URL
                .build(); //Finally building the adapter

        ModulAPI api = adapter.create(ModulAPI.class);

        api.ceksudahdaftar(
                username,
                password,
                new Callback<DataWarga>()

                {
                    @Override
                    public void success(DataWarga datawarga, Response response) {
                        if (datawarga.getusername() == null) {
                            Toast.makeText(LoginActivity.this, "User tidak terdaftar", Toast.LENGTH_LONG).show();
                        } else {
                            Session.with(getApplicationContext()).load("user_nu").set("id_warga", datawarga.getid_warga());
                            Session.with(getApplicationContext()).load("user_nu").set("username", datawarga.getusername());
                            Session.with(getApplicationContext()).load("user_nu").set("nama", datawarga.getusername());
                            Session.with(getApplicationContext()).load("user_nu").set("email", datawarga.getemail());

                            updateLocation();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(LoginActivity.this, "Kesalahan Koneksi Data" + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );
    }


    private void updateLocation() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        String latitude = String.valueOf(location.latitude());
        String longitude = String.valueOf(location.longitude());

        Session.with(getApplicationContext()).load("user_nu").set("latitude", latitude);
        Session.with(getApplicationContext()).load("user_nu").set("longitude", longitude);

        api.simpanLatLong(
                Session.with(getApplicationContext()).load("user_nu").get("username"),
                Session.with(getApplicationContext()).load("user_nu").get("latitude"),
                Session.with(getApplicationContext()).load("user_nu").get("longitude"),

                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader = null;

                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(LoginActivity.this, "Kesalahan Koneksi Data", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}


