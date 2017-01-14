package com.adfin.numobile.module.login.controller;
// Created by prakasa on 14/01/17.

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.adfin.numobile.App;
import com.adfin.numobile.R;

public class Home extends App {

    Button btnLogin;
    EditText edtUsername, edtPassword;
    ProgressBar prgProgress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContext(this);

        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        edtUsername = (EditText) findViewById(R.id.editUsername);
        edtPassword = (EditText) findViewById(R.id.editPassword);
        prgProgress = (ProgressBar) findViewById(R.id.progressBarSpinner);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtUsername.getText().toString().equalsIgnoreCase("")){
                    AlertDialog.Builder ad= new AlertDialog.Builder(mContext);
                    ad.setTitle("Peringatan");
                    ad .setMessage("Username tidak boleh kosong");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    ad .setIcon(android.R.drawable.ic_dialog_alert);
                    ad .show();
                }else if(edtPassword.getText().toString().equalsIgnoreCase(""))
                {
                    AlertDialog.Builder ad= new AlertDialog.Builder(mContext);
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
                    String username = edtUsername.getText().toString();
                    String password = edtPassword.getText().toString();
                    prgProgress.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
