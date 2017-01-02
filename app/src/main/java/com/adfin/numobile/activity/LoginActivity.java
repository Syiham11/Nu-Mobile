package com.adfin.numobile.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if( Session.with(getApplicationContext()).load("user_nu").get("username") != null ){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            location = Nengkene.with(this).start().get();
        }

        setContentView(R.layout.activity_login);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSpinner);
        textError = (TextView) findViewById(R.id.errorinfo);
        progressBar.setVisibility(View.GONE);
        textError.setVisibility(View.GONE);

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

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
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
}


