package com.adfin.numobile.activity.warga;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.activity.GlobalClass;
import com.adfin.numobile.model.CDataDesa;
import com.adfin.numobile.model.CDataKabupten;
import com.adfin.numobile.model.CDataKecamatan;
import com.adfin.numobile.model.CDataProvinsi;
import com.adfin.numobile.model.DataDesa;
import com.adfin.numobile.model.DataKabupaten;
import com.adfin.numobile.model.DataKecamatan;
import com.adfin.numobile.model.DataProvinsi;
import com.adfin.numobile.model.DataWarga;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FormAnggota1Activity extends AppCompatActivity {

    public static final String ROOT_URL = "http://numobile.id";
    EditText etUsername,
            etNamaLengkap,
            etNoKtp,
            etTempatLahir,
            etDate,
            etAlamat,
            etKodePos,
            etTelepon,
            etHandphone,
            etEmail,
            etFacebook,
            etTwitter,
            etInstagram,
            etPathh;
    Spinner spJenisKelamin,
            spStatusPerkawinan,
            spProvinsi,
            spKabupaten,
            spKecamatan,
            spDesa;
    String strusername,
            strnama,
            strnoktp,
            strtempat_lahir,
            strtanggal_lahir,
            strjenis_kelamin,
            strstatus_perkawinan,
            stralamat,
            strprovinsi,
            strkabkot,
            strkecamatan,
            strdesa,
            strkode_pos,
            strtlp,
            strhp,
            stremail,
            strfb,
            strtwitter,
            strinstagram,
            strpathh;
    String strusercek;
    Button btnNext1;
    private List<DataProvinsi> lstdataprovinsi;
    private List<DataKabupaten> lstdatakabupaten;
    private List<DataKecamatan> lstdatakecamatan;
    private List<DataDesa> lstdatadesa;



    int mYear,mMonth, mDay;
    static final int DATE_DIALOG_ID = 1;
    static final int TIME_DIALOG_ID = 0;
    private String[] arrMonth = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    String strnamakecamatan, stridprovinsi,strnamaprovinsi,  strkodedesa, strkodekabkot, strnamakabupaten, strkodekecamatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_anggota1);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        etUsername = (EditText) findViewById(R.id.etUsername);
        etNamaLengkap = (EditText) findViewById(R.id.etNamaLengkap);
        etNoKtp = (EditText) findViewById(R.id.etNoKtp);
        etTempatLahir = (EditText) findViewById(R.id.etTempatLahir);
        etDate = (EditText) findViewById(R.id.etDate);
        spJenisKelamin = (Spinner) findViewById(R.id.spJenisKelamin);
        spStatusPerkawinan = (Spinner) findViewById(R.id.spStatusPerkawinan);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        spProvinsi = (Spinner) findViewById(R.id.spProvinsi);
        spKabupaten = (Spinner) findViewById(R.id.spKabupaten);
        spKecamatan = (Spinner) findViewById(R.id.spKecamatan);
        spDesa = (Spinner) findViewById(R.id.spDesa);
        etKodePos = (EditText) findViewById(R.id.etKodePos);
        etTelepon = (EditText) findViewById(R.id.etTelepon);
        etHandphone = (EditText) findViewById(R.id.etHandphone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etFacebook = (EditText) findViewById(R.id.etFacebook);
        etTwitter = (EditText) findViewById(R.id.etTwitter);
        etInstagram = (EditText) findViewById(R.id.etInstagram);
        etPathh = (EditText) findViewById(R.id.etPathh);

        subDataProvinsi();
        //subDataKabupaten();

        ///// set Calender ////
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        etDate.setOnTouchListener(new OnTouchListener() {

            @Override

            public boolean onTouch(View arg0, MotionEvent arg1) {

                // TODO Auto-generated method stub

                showDialog(DATE_DIALOG_ID);

                return true;

            }

        });


        btnNext1 = (Button) findViewById(R.id.btnNext1);
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etUsername.getText().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Username Belum Diisi" , Toast.LENGTH_LONG).show();
                    etUsername.requestFocus();
                    return;
                }
                if (etNamaLengkap.getText().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Nama Lengkap Belum Diisi" , Toast.LENGTH_LONG).show();
                    etNamaLengkap.requestFocus();
                    return;
                }
                if (etNoKtp.getText().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "No KTP Belum Diisi" , Toast.LENGTH_LONG).show();
                    etNoKtp.requestFocus();
                    return;
                }
                if (etTempatLahir.getText().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Tempat Lahir Belum Diisi" , Toast.LENGTH_LONG).show();
                    etTempatLahir.requestFocus();
                    return;
                }
                if (etDate.getText().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Tanggal Lahir Belum Diisi" , Toast.LENGTH_LONG).show();
                    etDate.requestFocus();
                    return;
                }
                if (spJenisKelamin.getSelectedItem().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Jenis Kelamin Belum Dipilih" , Toast.LENGTH_LONG).show();
                    spJenisKelamin.requestFocus();
                    return;
                }
                if (spStatusPerkawinan.getSelectedItem().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Status Perkawinan Belum Diisi" , Toast.LENGTH_LONG).show();
                    spStatusPerkawinan.requestFocus();
                    return;
                }
                if (etAlamat.getText().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Alamat Belum Diisi" , Toast.LENGTH_LONG).show();
                    etAlamat.requestFocus();
                    return;
                }
                if (spProvinsi.getSelectedItem().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Provinsi Belum Dipilih" , Toast.LENGTH_LONG).show();
                    spProvinsi.requestFocus();
                    return;
                }
                if (spKabupaten.getSelectedItem().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Kabupaten Belum Dipilih" , Toast.LENGTH_LONG).show();
                    spKabupaten.requestFocus();
                    return;
                }
                if (spKecamatan.getSelectedItem().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Kecamatan Belum Dipilih" , Toast.LENGTH_LONG).show();
                    spKecamatan.requestFocus();
                    return;
                }
                if (spDesa.getSelectedItem().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Desa Belum Dipilih" , Toast.LENGTH_LONG).show();
                    spDesa.requestFocus();
                    return;
                }
                if (etHandphone.getText().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "No Handphone Belum Diisi" , Toast.LENGTH_LONG).show();
                    etHandphone.requestFocus();
                    return;
                }
                if (etEmail.getText().toString().length() ==0){
                    Toast.makeText(getApplicationContext(), "Email Belum Diisi" , Toast.LENGTH_LONG).show();
                    etEmail.requestFocus();
                    return;
                }

                strusercek = etUsername.getText().toString();
                //cekusername();

                globalVariable.setUsername(etUsername.getText().toString());
                globalVariable.setNamaLengkap(etNamaLengkap.getText().toString());
                globalVariable.setNoKtp(etNoKtp.getText().toString());
                globalVariable.setTempatLahir(etTempatLahir.getText().toString());
                globalVariable.setTanggalLahir(etDate.getText().toString());
                globalVariable.setJenisKelamin(spJenisKelamin.getSelectedItem().toString());
                globalVariable.setStatusPerkawinan(spStatusPerkawinan.getSelectedItem().toString());
                globalVariable.setAlamat(etAlamat.getText().toString());

                if (spProvinsi.getCount() == 0) {
                    globalVariable.setProvinsi("");
                } else {
                    globalVariable.setProvinsi(spProvinsi.getSelectedItem().toString());
                }


                if (spKabupaten.getCount() == 0) {
                    globalVariable.setKabupaten("");
                } else {
                    globalVariable.setKabupaten(spKabupaten.getSelectedItem().toString());
                }


                if (spKecamatan.getCount() == 0) {
                    globalVariable.setKecamatan("");
                } else {
                    globalVariable.setKecamatan(spKecamatan.getSelectedItem().toString());
                }

                if (spDesa.getCount() == 0) {
                    globalVariable.setDesa("");
                } else {
                    globalVariable.setDesa(spDesa.getSelectedItem().toString());
                }

                globalVariable.setKodePos(etKodePos.getText().toString());
                globalVariable.setTelepon(etTelepon.getText().toString());
                globalVariable.setHandphone(etHandphone.getText().toString());
                globalVariable.setEmail(etEmail.getText().toString());
                globalVariable.setFacebook(etFacebook.getText().toString());
                globalVariable.setTwitter(etTwitter.getText().toString());
                globalVariable.setInstagram(etInstagram.getText().toString());
                globalVariable.setPathh(etPathh.getText().toString());

                //kalau berhasil nnti ganti ke form 2
                //Intent intent = new Intent(getApplicationContext(), SimpanForm1.class);
                //startActivity(intent);
                //finish();

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

                saveDataInput();
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
        api.insertdataanggota(
                "form1",
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
                        StringBuilder sb = new StringBuilder();

                        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //An string to store output from the server
                            String output;
                            try {
                                while ((output = reader.readLine()) != null) {
                                    sb.append(output);
                                }

                                String returns = sb.toString();

                                Log.e("Bangkeeeee", returns);

                                globalVariable.setId(returns);

                                Toast.makeText(FormAnggota1Activity.this, "Data Berhasil Di Simpan", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(), FormAnggota2Activity.class);

                                startActivity(intent);

                                finish();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            //Log.e("Gagal ", result.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(FormAnggota1Activity.this, "Kesalahan Koneksi Data" +error.getMessage(), Toast.LENGTH_LONG).show();
                        //btnsetting.setEnabled(true);
                    }
                }
        );
    }

    private void cekusername(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.cekusername(
                strusercek.toString(),
                new Callback<DataWarga>()

                {
                    @Override
                    public void success(DataWarga datawarga, Response response) {

                        strusercek = datawarga.getusername();

                        //Toast.makeText(LoginActivity.this, "benar masuk "+strusername2.toString(), Toast.LENGTH_LONG).show();


                        // if (striddevice2.equals(etiddevice.getText().toString())) {
                        if (strusercek == null) {
                            //if (striddevice2.equals(striddevice.toString())) {
                            //semua yang diinput di set melalui global variabel, urutan harus sama dengan yang di form
                            //untuk menyimpan data yang sudah diinput ditampung di variabel
                            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

                            globalVariable.setUsername(etUsername.getText().toString());
                            globalVariable.setNamaLengkap(etNamaLengkap.getText().toString());
                            globalVariable.setNoKtp(etNoKtp.getText().toString());
                            globalVariable.setTempatLahir(etTempatLahir.getText().toString());
                            globalVariable.setTanggalLahir(etDate.getText().toString());
                            globalVariable.setJenisKelamin(spJenisKelamin.getSelectedItem().toString());
                            globalVariable.setStatusPerkawinan(spStatusPerkawinan.getSelectedItem().toString());
                            globalVariable.setAlamat(etAlamat.getText().toString());

                            if (spProvinsi.getCount() == 0) {
                                globalVariable.setProvinsi("");
                            } else {
                                globalVariable.setProvinsi(spProvinsi.getSelectedItem().toString());
                            }


                            if (spKabupaten.getCount() == 0) {
                                globalVariable.setKabupaten("");
                            } else {
                                globalVariable.setKabupaten(spKabupaten.getSelectedItem().toString());
                            }


                            if (spKecamatan.getCount() == 0) {
                                globalVariable.setKecamatan("");
                            } else {
                                globalVariable.setKecamatan(spKecamatan.getSelectedItem().toString());
                            }

                            if (spDesa.getCount() == 0) {
                                globalVariable.setDesa("");
                            } else {
                                globalVariable.setDesa(spDesa.getSelectedItem().toString());
                            }

                            globalVariable.setKodePos(etKodePos.getText().toString());
                            globalVariable.setTelepon(etTelepon.getText().toString());
                            globalVariable.setHandphone(etHandphone.getText().toString());
                            globalVariable.setEmail(etEmail.getText().toString());
                            globalVariable.setFacebook(etFacebook.getText().toString());
                            globalVariable.setTwitter(etTwitter.getText().toString());
                            globalVariable.setInstagram(etInstagram.getText().toString());
                            globalVariable.setPathh(etPathh.getText().toString());

                            //kalau berhasil nnti ganti ke form 2
                            Intent intent = new Intent(getApplicationContext(), SimpanForm1.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(FormAnggota1Activity.this, "Username sudah ada" + toString(), Toast.LENGTH_LONG).show();
                        }


                        //
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(FormAnggota1Activity.this, "Kesalahan Koneksi Data" + toString(), Toast.LENGTH_LONG).show();

                    }
                }

        );


    }

    private void subDataProvinsi() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataDaftar yang ada di api
        api.getDataProvinsi(
                new Callback<CDataProvinsi>()

                {
                    @Override
                    public void success(CDataProvinsi cdataprovinsi, Response response) {


                     lstdataprovinsi = new ArrayList<DataProvinsi>();
//
                        lstdataprovinsi = cdataprovinsi.getDataProvinsi();
                        final String[] tsidprovinsi = new String[lstdataprovinsi.size()];
                        final String[] tskodeprovinsi = new String[lstdataprovinsi.size()];
                        String[] tsnamaprovinsi = new String[lstdataprovinsi.size()];
//
                            //Toast.makeText(getApplicationContext(), " banyak user" + Integer.toString(lstdataprovinsi.size()), Toast.LENGTH_LONG).show();

                        for (int i = 0; i < lstdataprovinsi.size(); i++) {
                            //Storing names to string array
                            tsidprovinsi[i] = lstdataprovinsi.get(i).getid_provinsi().toString();//getnama hrs sama dgn yang di DataDaftar
                            tskodeprovinsi[i] = lstdataprovinsi.get(i).getkode_provinsi().toString();
                            tsnamaprovinsi[i] = lstdataprovinsi.get(i).getnama_provinsi().toString();

                        }
                        ArrayAdapter adapter = new ArrayAdapter<String>(FormAnggota1Activity.this, R.layout.support_simple_spinner_dropdown_item, tsnamaprovinsi);
                        spProvinsi.setAdapter(adapter);



                        stridprovinsi = tskodeprovinsi[0].toString();
                        //Toast.makeText(FormAnggota1Activity.this, stridprovinsi.toString() , Toast.LENGTH_LONG).show();

                        subDataKabupaten();

                               spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                       stridprovinsi = tskodeprovinsi[position].toString();;
                                       strnamaprovinsi = parent.getItemAtPosition(position).toString();
                                       //Toast.makeText(FormAnggota1Activity.this, stridprovinsi.toString() , Toast.LENGTH_LONG).show();
                                       subDataKabupaten();

                                   }

                                   public void onNothingSelected(AdapterView<?> parent) {

                                   }
                              });


                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(FormAnggota1Activity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                    }
                }

        );


    }

    private void subDataKabupaten() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataDaftar yang ada di api
        api.getDataKabupaten(
                stridprovinsi.toString(),
                //"11",
                new Callback<CDataKabupten>()

                {
                    @Override
                    public void success(CDataKabupten cdatakabupaten, Response response) {


                        lstdatakabupaten = new ArrayList<DataKabupaten>();
//
                        lstdatakabupaten = cdatakabupaten.getDataKabupaten();
                        final String[] vsidkabkot = new String[lstdatakabupaten.size()];
                        String[] vskodeprovinsi = new String[lstdatakabupaten.size()];
                        final String[] vskodekabkot = new String[lstdatakabupaten.size()];
                        String[] vsnamakabkot = new String[lstdatakabupaten.size()];
//
                        //Toast.makeText(getApplicationContext(), " banyak user" + Integer.toString(lstdataprovinsi.size()), Toast.LENGTH_LONG).show();

                        for (int i = 0; i < lstdatakabupaten.size(); i++) {
                            //Storing names to string array
                            vsidkabkot[i] = lstdatakabupaten.get(i).getid_kabkot().toString();//getnama hrs sama dgn yang di DataDaftar
                            vskodeprovinsi[i] = lstdatakabupaten.get(i).getkode_provinsi().toString();
                            vskodekabkot[i] = lstdatakabupaten.get(i).getkode_kabkot().toString();
                            vsnamakabkot[i] = lstdatakabupaten.get(i).getnama_kabkot().toString();
                            //Toast.makeText(FormAnggota1Activity.this,"Berhasil" , Toast.LENGTH_LONG).show();

                        }
                        ArrayAdapter adapter = new ArrayAdapter<String>(FormAnggota1Activity.this, R.layout.support_simple_spinner_dropdown_item, vsnamakabkot);
                        spKabupaten.setAdapter(adapter);


                        //Toast.makeText(FormAnggota1Activity.this, vsnamakabkot[0].toString() , Toast.LENGTH_LONG).show();

                        strkodekabkot = vskodekabkot[0].toString();
                        subDataKecamatan();

                        spKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                strkodekabkot = vskodekabkot[position].toString();
                                strnamakabupaten = parent.getItemAtPosition(position).toString();
                                //Toast.makeText(FormAnggota1Activity.this, strkodekabkot.toString() , Toast.LENGTH_LONG).show();
                                //Toast.makeText(FormAnggota1Activity.this, stridprovinsi.toString() , Toast.LENGTH_LONG).show();
                                subDataKecamatan();

                            }

                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(FormAnggota1Activity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                    }
                }

        );


    }

    private void subDataKecamatan() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataDaftar yang ada di api
        api.getDataKecamatan(
                stridprovinsi.toString(),
                //"11",
                strkodekabkot.toString(),
                //"1101",
                //"11",
                new Callback<CDataKecamatan>()

                {
                    @Override
                    public void success(CDataKecamatan cdatakecamatan, Response response) {


                        lstdatakecamatan = new ArrayList<DataKecamatan>();

                        lstdatakecamatan = cdatakecamatan.getDataKecamatan();
                        final String[] midkecamatan = new String[lstdatakecamatan.size()];
                        final String[] mkodeprovinsi = new String[lstdatakecamatan.size()];
                        final String[] mkodekabkot = new String[lstdatakecamatan.size()];
                        final String[] mkodekecamatan = new String[lstdatakecamatan.size()];
                        final String[] mnamakecamatan = new String[lstdatakecamatan.size()];
//

                        //Toast.makeText(getApplicationContext(), Integer.toString(lstdatakecamatan.size()), Toast.LENGTH_LONG).show();

                        for (int i = 0; i < lstdatakecamatan.size(); i++) {
                            //Storing names to string array
                            midkecamatan[i] = lstdatakecamatan.get(i).getid_kecamatan().toString();//getnama hrs sama dgn yang di DataDaftar
                            mkodeprovinsi[i] = lstdatakecamatan.get(i).getkode_provinsi().toString();
                            mkodekabkot[i] = lstdatakecamatan.get(i).getkode_kabkot().toString();
                            mkodekecamatan[i] = lstdatakecamatan.get(i).getkode_kecamatan().toString();
                            mnamakecamatan[i] = lstdatakecamatan.get(i).getnama_kecamatan().toString();
                            //Toast.makeText(FormAnggota1Activity.this,"Berhasil" , Toast.LENGTH_LONG).show();

                        }
                        ArrayAdapter adapter = new ArrayAdapter<String>(FormAnggota1Activity.this, R.layout.support_simple_spinner_dropdown_item, mnamakecamatan);
                        spKecamatan.setAdapter(adapter);


                        //Toast.makeText(FormAnggota1Activity.this, vsnamakabkot[0].toString() , Toast.LENGTH_LONG).show();

                        strkodekecamatan = mkodekecamatan[0].toString();
                        subDataDesa();

                        spKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                strkodekecamatan = mkodekecamatan[position].toString();
                                strnamakecamatan = parent.getItemAtPosition(position).toString();
//
                                subDataDesa();

                            }

                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(FormAnggota1Activity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                    }
                }

        );


    }

    private void subDataDesa() {
            //Here we will handle the http request to insert user to mysql db
            //Creating a RestAdapter

            //Toast.makeText(FormAnggota1Activity.this, "kode provinsi "+ stridprovinsi.toString() , Toast.LENGTH_LONG).show();
    //Toast.makeText(FormAnggota1Activity.this, "kode kabkot "+ strkodekabkot.toString() , Toast.LENGTH_LONG).show();
    //Toast.makeText(FormAnggota1Activity.this, "kode kecamatan "+ strkodekecamatan.toString() , Toast.LENGTH_LONG).show();

            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ROOT_URL) //Setting the Root URL
                    .build(); //Finally building the adapter

            //Creating object for our interface
            ModulAPI api = adapter.create(ModulAPI.class);
        //strkodekecamatan = "5678";
            //Calling method to get whether report
            //getDataDaftar yang ada di api
            api.getDataDesa(
                    stridprovinsi.toString(),
                    //"11",
                    strkodekabkot.toString(),
                    //"1101",
                    strkodekecamatan.toString(),
                    //"5678",
                    new Callback<CDataDesa>()

                    {
                        @Override
                        public void success(CDataDesa cdatadesa, Response response) {


                            lstdatadesa = new ArrayList<DataDesa>();

                            lstdatadesa = cdatadesa.getDataDesa();
                            final String[] niddesa = new String[lstdatadesa.size()];
                            final String[] nkodeprovinsi = new String[lstdatadesa.size()];
                            final String[] nkodekabkot = new String[lstdatadesa.size()];
                            final String[] nkodekecamatan = new String[lstdatadesa.size()];
                            final String[] nkodedesa = new String[lstdatadesa.size()];
                            final String[] nnamadesa = new String[lstdatadesa.size()];
    //
                            //Toast.makeText(getApplicationContext(), "Jumlah desa "+Integer.toString(lstdatadesa.size()), Toast.LENGTH_LONG).show();

                            if (lstdatadesa.size() > 0) {
                                for (int i = 0; i < lstdatadesa.size(); i++) {
                                    //Storing names to string array
                                    niddesa[i] = lstdatadesa.get(i).getid_desa().toString();//getnama hrs sama dgn yang di DataDaftar
                                    nkodeprovinsi[i] = lstdatadesa.get(i).getkode_provinsi().toString();
                                    nkodekabkot[i] = lstdatadesa.get(i).getkode_kabkot().toString();
                                    nkodekecamatan[i] = lstdatadesa.get(i).getkode_kecamatan().toString();
                                    nkodedesa[i] = lstdatadesa.get(i).getkode_desa().toString();
                                    nnamadesa[i] = lstdatadesa.get(i).getnama_desa().toString();
                                    //Toast.makeText(FormAnggota1Activity.this,"Berhasil" , Toast.LENGTH_LONG).show();

                                }
                                ArrayAdapter adapter = new ArrayAdapter<String>(FormAnggota1Activity.this, R.layout.support_simple_spinner_dropdown_item, nnamadesa);
                                spDesa.setAdapter(adapter);


                                //Toast.makeText(FormAnggota1Activity.this, vsnamakabkot[0].toString() , Toast.LENGTH_LONG).show();

                                strkodedesa = nkodedesa[0].toString();
                                //subDataDesa();

                                spDesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        strkodedesa = nkodedesa[position].toString();
                                        //strnamakecamatan = parent.getItemAtPosition(position).toString();
                                        //Toast.makeText(FormAnggota1Activity.this, stridprovinsi.toString() , Toast.LENGTH_LONG).show();
                                        //subDataDesa();

                                    }

                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                            }


                        }

                        @Override
                        public void failure(RetrofitError error) {

                            String merror = error.getMessage();

                            Toast.makeText(FormAnggota1Activity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                        }
                    }

            );


    }



    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id) {
            case DATE_DIALOG_ID:

                return new DatePickerDialog(

                        this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener()
            {


                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    String sdate = arrMonth[mMonth] + " " + LPad(mDay + "", "0", 2) + ", " + mYear;
                    etDate.setText(sdate);
                }
            };

    private static String LPad(String schar, String spad, int len) {
        String sret = schar;
        for (int i = sret.length(); i < len; i++) {
            sret = spad + sret;
        }
        return new String(sret);
    }



}



