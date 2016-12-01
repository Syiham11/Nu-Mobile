package com.adfin.numobile.activity.warga;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.activity.GlobalClass;
import com.adfin.numobile.model.CDataIndukOrganisasi;
import com.adfin.numobile.model.CDataInstansi;
import com.adfin.numobile.model.CDataJenisPekerjaan;
import com.adfin.numobile.model.CDataPekerjaan;
import com.adfin.numobile.model.CDataPendapatan;
import com.adfin.numobile.model.CDataProvinsi;
import com.adfin.numobile.model.DataIndukOrganisasi;
import com.adfin.numobile.model.DataInstansi;
import com.adfin.numobile.model.DataJenisPekerjaan;
import com.adfin.numobile.model.DataPekerjaan;
import com.adfin.numobile.model.DataPendapatan;
import com.adfin.numobile.model.DataProvinsi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FormAnggota2Activity extends AppCompatActivity {

    public static final String ROOT_URL = "http://numobile.id";

    EditText etIdWarga;
    Spinner spPekerjaan;
    Spinner spInstansi;
    EditText etJabatan;
    Spinner spPendapatan;
    EditText etKemampuan;
    Spinner spOrganisasi;
    EditText etSD;
    EditText etSMP;
    EditText etSMA;
    EditText etD1;
    EditText etD3;
    EditText etS1;
    EditText etS2;
    EditText etS3;

    String  strIdWarga;
    String  strpekerjaan;
    String  strinstansi;
    String  strjabatan;
    String  strpendapatan;
    String  strkemampuan;
    String  strorganisasi;
    String  strsd;
    String  strsmp;
    String  strsma;
    String  strd1;
    String  strd3;
    String  strs1;
    String  strs2;
    String  strs3;
    String  allSchool;

    private List<DataJenisPekerjaan> lstdatajenispekerjaan;
    private List<DataInstansi> lstdatainstansi;
    private List<DataPendapatan> lstdatapendapatan;
    private List<DataIndukOrganisasi> lstdataindukorganisasi;

    Button  btnBack1,
            btnNext2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_anggota2);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        etIdWarga = (EditText) findViewById(R.id.idWarga);
        strIdWarga = globalVariable.getId();
        etIdWarga.setText(strIdWarga);

        spPekerjaan = (Spinner) findViewById(R.id.spPekerjaan);
        spInstansi= (Spinner) findViewById(R.id.spInstansi);
        etJabatan = (EditText) findViewById(R.id.etJabatan);
        spPendapatan = (Spinner) findViewById(R.id.spPendapatan);
        etKemampuan = (EditText) findViewById(R.id.etKemampuan);
        spOrganisasi = (Spinner) findViewById(R.id.spOrganisasi);
        etSD = (EditText) findViewById(R.id.etSD);
        etSMP = (EditText) findViewById(R.id.etSMP);
        etSMA = (EditText) findViewById(R.id.etSMA);
        etD1 = (EditText) findViewById(R.id.etD1);
        etD3 = (EditText) findViewById(R.id.etD3);
        etS1 = (EditText) findViewById(R.id.etS1);
        etS2 = (EditText) findViewById(R.id.etS2);
        etS3 = (EditText) findViewById(R.id.etS3);



        subAmbilSpinnerJenisPekerjaan();
        subAmbilSpinnerInstansi();
        subAmbilSpinnerPendapatan();
        subAmbilSpinnerIndukOrganisasi();

        btnNext2 = (Button) findViewById(R.id.btnNext2);
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spPekerjaan.getSelectedItem().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Pekerjaan Belum Dipilih" , Toast.LENGTH_LONG).show();
                    spPekerjaan.requestFocus();
                    return;
                }
                if (spOrganisasi.getSelectedItem().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Organisasi Belum Dipilih" , Toast.LENGTH_LONG).show();
                    spOrganisasi.requestFocus();
                    return;
                }
                if (etSD.getText().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "SD Belum Diisi" , Toast.LENGTH_LONG).show();
                    spPekerjaan.requestFocus();
                    return;
                }

                globalVariable.setPekerjaan(spPekerjaan.getSelectedItem().toString());
                globalVariable.setInstansi(spInstansi.getSelectedItem().toString());
                globalVariable.setJabatan(etJabatan.getText().toString());
                globalVariable.setPendapatan(spPendapatan.getSelectedItem().toString());
                globalVariable.setKemampuan(etKemampuan.getText().toString());
                globalVariable.setIndukOrganisasi(spOrganisasi.getSelectedItem().toString());
                globalVariable.setSD(etSD.getText().toString());
                globalVariable.setSMP(etSMP.getText().toString());
                globalVariable.setSMA(etSMA.getText().toString());
                globalVariable.setD1(etD1.getText().toString());
                globalVariable.setD3(etD3.getText().toString());
                globalVariable.setS1(etS1.getText().toString());
                globalVariable.setS2(etS2.getText().toString());
                globalVariable.setS3(etS3.getText().toString());

                strpekerjaan = globalVariable.getPekerjaan();
                strinstansi = globalVariable.getInstansi();
                strjabatan = globalVariable.getJabatan();
                strpendapatan = globalVariable.getPendapatan();
                strkemampuan = globalVariable.getKemampuan();
                strorganisasi = globalVariable.getIndukOrganisasi();
                strsd = globalVariable.getSD();
                strsmp = globalVariable.getSMP();
                strsma = globalVariable.getSMA();
                strd1 = globalVariable.getD1();
                strd3 = globalVariable.getD3();
                strs1 = globalVariable.getS1();
                strs2 = globalVariable.getS2();
                strs3 = globalVariable.getS3();
                allSchool = strsd + "~~" + strsmp + "~~" + strsma + "~~" + strd1 + "~~" + strd3 + "~~" + strs1 + "~~" + strs2 + "~~" + strs3;

                saveDataInput();

            }
        });

        btnBack1 = (Button) findViewById(R.id.btnBack1);
        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormAnggota2Activity.this, FormAnggota1Activity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void saveDataInput(){
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://www.terpusat.com") //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        //insertdataanggota ada di ModulAPI
        //urutan dan jumlah harus sama dengan yang di Model API
        api.insertdataanggotal(
                "form2",
                strIdWarga,
                strpekerjaan,
                "suka-suka gw",
                strjabatan,
                strpendapatan,
                strkemampuan,
                strorganisasi,
                allSchool,
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

                        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                        globalVariable.setId(strIdWarga);

                        Intent intent = new Intent(getApplicationContext(), FormAnggota3Activity.class);
                        startActivity(intent);
                        finish();

                        Toast.makeText(FormAnggota2Activity.this, "Data Berhasil Di Simpan", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(FormAnggota2Activity.this, "Terjadi kesalahan koneksi", Toast.LENGTH_LONG).show();
                        //btnsetting.setEnabled(true);
                    }
                }
        );
    }



    private void subAmbilSpinnerJenisPekerjaan() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataDaftar yang ada di api
        api.getDataJenisPekerjaan(
                new Callback<CDataJenisPekerjaan>()

                {
                    @Override
                    public void success(CDataJenisPekerjaan cdatajenispekerjaan, Response response) {


                        lstdatajenispekerjaan = new ArrayList<DataJenisPekerjaan>();

                        lstdatajenispekerjaan = cdatajenispekerjaan.getDataJenisPekerjaan();
                        final String[] tsnamajenispekerjaan = new String[lstdatajenispekerjaan.size()];

                        for (int i = 0; i < lstdatajenispekerjaan.size(); i++) {
                            //Storing names to string array
                            tsnamajenispekerjaan[i] = lstdatajenispekerjaan.get(i).getnama_pekerjaan().toString();//getnama hrs sama dgn yang di DataDaftar

                        }
                        ArrayAdapter adapter = new ArrayAdapter<String>(FormAnggota2Activity.this, R.layout.support_simple_spinner_dropdown_item, tsnamajenispekerjaan);
                        spPekerjaan.setAdapter(adapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(FormAnggota2Activity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                    }
                }

        );


    }

    private void subAmbilSpinnerInstansi() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataPendapatan yang ada di api
        api.getDataInstansi(
                new Callback<CDataInstansi>()

                {
                    @Override
                    public void success(CDataInstansi cdatainstansi, Response response) {


                        lstdatainstansi = new ArrayList<DataInstansi>();
//
                        lstdatainstansi = cdatainstansi.getDataInstansi();
                        final String[] tsnamainstansi = new String[lstdatainstansi.size()];

                        for (int i = 0; i < lstdatainstansi.size(); i++) {
                            //Storing names to string array
                            tsnamainstansi[i] = lstdatainstansi.get(i).getnama_instansi().toString();//getjumlah_pendapatan hrs sama dgn yang di DataPendapatan

                        }
                        ArrayAdapter adapter = new ArrayAdapter<String>(FormAnggota2Activity.this, R.layout.support_simple_spinner_dropdown_item, tsnamainstansi);
                        spInstansi.setAdapter(adapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(FormAnggota2Activity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                    }
                }

        );


    }

    private void subAmbilSpinnerPendapatan() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataPendapatan yang ada di api
        api.getDataPendapatan(
                new Callback<CDataPendapatan>()

                {
                    @Override
                    public void success(CDataPendapatan cdatapendapatan, Response response) {


                        lstdatapendapatan = new ArrayList<DataPendapatan>();

                        lstdatapendapatan = cdatapendapatan.getDataPendapatan();
                        final String[] tsjumlahpendapatan = new String[lstdatapendapatan.size()];

                        for (int i = 0; i < lstdatapendapatan.size(); i++) {
                            //Storing names to string array
                            tsjumlahpendapatan[i] = lstdatapendapatan.get(i).getjumlah_pendapatan().toString();//getjumlah_pendapatan hrs sama dgn yang di DataPendapatan

                        }
                        ArrayAdapter adapter = new ArrayAdapter<String>(FormAnggota2Activity.this, R.layout.support_simple_spinner_dropdown_item, tsjumlahpendapatan);
                        spPendapatan.setAdapter(adapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(FormAnggota2Activity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                    }
                }

        );


    }

    private void subAmbilSpinnerIndukOrganisasi() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataPendapatan yang ada di api
        api.getDataIndukOrganisasi(
                new Callback<CDataIndukOrganisasi>()

                {
                    @Override
                    public void success(CDataIndukOrganisasi cdataindukorganisasi, Response response) {


                        lstdataindukorganisasi = new ArrayList<DataIndukOrganisasi>();

                        lstdataindukorganisasi = cdataindukorganisasi.getDataIndukOrganisasi();
                        final String[] tsnamaorganisasi = new String[lstdataindukorganisasi.size()];

                        for (int i = 0; i < lstdataindukorganisasi.size(); i++) {
                            //Storing names to string array
                            tsnamaorganisasi[i] = lstdataindukorganisasi.get(i).getnama_organisasi().toString();//getjumlah_pendapatan hrs sama dgn yang di DataPendapatan

                        }
                        ArrayAdapter adapter = new ArrayAdapter<String>(FormAnggota2Activity.this, R.layout.support_simple_spinner_dropdown_item, tsnamaorganisasi);
                        spOrganisasi.setAdapter(adapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        final String merror = error.getMessage();

                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(FormAnggota2Activity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();

                                Context context = FormAnggota2Activity.this;
                                Intent intent = new Intent(context, FormAnggota2Activity.class);
                                (context).startActivity(intent);
                            }
                        });
                    }
                }

        );


    }
}

