package com.adfin.numobile.helper;
// Created by prakasa on 18/12/16.

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.google.android.gms.internal.zzid.runOnUiThread;

public class Uploader {
    private String mUrl;
    private HashMap mMap;
    private String mMsg;
    private Boolean error = false;
    private static Context mContext;
    private static Class mRedirect;

    @Nullable
    public static Uploader with(Context context, Class redirect){
        mContext = context;
        mRedirect = redirect;
        return new Uploader();
    }

    public Uploader load(String url){
        mUrl = url;
        return this;
    }

    public Uploader data(HashMap map){
        mMap = map;
        return this;
    }

    public Uploader message(String msg){
        mMsg = msg;
        return this;
    }

    public Uploader upload() {
        class backgroundUpload extends AsyncTask<String, Void, String> {
            /*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);*/

            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(mContext, "", mMsg, true);
            }

            @Override
            protected String doInBackground(String... params) {
                try{
                    URL url = new URL(params[0]);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.setRequestMethod("POST");

                    connection.setRequestProperty("Connection", "Keep-Alive");
                    String boundary = "*****";
                    connection.setRequestProperty("Content-Type",
                            "multipart/form-data; boundary=" + boundary);

                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    String twoHyphens = "--";
                    String lineEnd = "\r\n";
                    outputStream.writeBytes(twoHyphens + boundary + lineEnd);

                    Set set = mMap.entrySet();
                    for (Object aSet : set) {
                        Map.Entry me = (Map.Entry) aSet;

                        outputStream.writeBytes("Content-Disposition: form-data; name=\"" + me.getKey().toString() + "\"" + lineEnd);
                        outputStream.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
                        outputStream.writeBytes("Content-Length: " + me.getValue().toString().length() + lineEnd);
                        outputStream.writeBytes(lineEnd);
                        outputStream.writeBytes(me.getValue().toString() + lineEnd);
                        outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                    }

                    outputStream.writeBytes(lineEnd);
                    outputStream.writeBytes(twoHyphens + boundary + twoHyphens
                            + lineEnd);

                    final int serverResponseCode = connection.getResponseCode();

                    if (serverResponseCode == 200) error = true;
                    else return "server";

                    outputStream.flush();
                    connection.getInputStream();
                    java.io.InputStream is = connection.getInputStream();

                    int ch;
                    StringBuilder b;
                    b = new StringBuilder();
                    while (-1 != (ch = is.read())) {
                        b.append((char) ch);
                    }


                    outputStream.close();

                } catch (Exception ex){
                    return "error";
                }

                if(error)
                    return "success";
                return "error";
            }

            @Override
            protected void onPostExecute(final String s) {
                super.onPostExecute(s);
                runOnUiThread(new Runnable() {
                    public void run() {
                        progressDialog.dismiss();

                        if(s.equals("error"))
                            Toast.makeText(mContext, "Upload Gagal, Silahkan Coba Kembali",
                                    Toast.LENGTH_SHORT).show();
                        else if(s.equals("server"))
                            Toast.makeText(mContext, "Terjadi Kesalahan Server, Hubungi Administrator",
                                    Toast.LENGTH_SHORT).show();
                        else if(s.equals("success")){
                            Intent intent = new Intent(mContext, mRedirect);
                            mContext.startActivity(intent);
                        }
                    }
                });
            }
        }

        new backgroundUpload().execute(mUrl);

        return this;
    }

    public Boolean finish(){
        return error;
    }
}
