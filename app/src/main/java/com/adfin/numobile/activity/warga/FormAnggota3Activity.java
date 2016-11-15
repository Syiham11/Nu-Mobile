package com.adfin.numobile.activity.warga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.adfin.numobile.model.CDataDesa;
import com.adfin.numobile.model.CDataKabupten;
import com.adfin.numobile.model.CDataKecamatan;
import com.adfin.numobile.model.CDataPesantren;
import com.adfin.numobile.model.CDataProvinsi;
import com.adfin.numobile.model.DataDesa;
import com.adfin.numobile.model.DataKabupaten;
import com.adfin.numobile.model.DataKecamatan;
import com.adfin.numobile.model.DataPesantren;
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

public class FormAnggota3Activity extends AppCompatActivity {

    public static final String ROOT_URL = "http://numobile.id";

    EditText etIdWarga;
    Spinner spYaTidakPesantren;
    EditText etLamaPesantren;
    Spinner spPesantren1;
    Spinner spPesantren2;
    Spinner spInfaq;
    Spinner spJalur;
    EditText etnominal_donasi;
    Spinner spInfaqWarga;
    Spinner spJalurWarga;
    EditText etnominal_donasi_warga;
    Spinner spStatusMember;
    Button btnBack2;
    Button btnNext3;

    String  strIdWarga;
    String  strYaTidakPesantren;
    String  strLamaPesantren;
    String  strPesantren1;
    String  strPesantren2;
    String  strInfaq;
    String  strJalur;
    String  strnominal_donasi;
    String  strInfaqWarga;
    String  strJalurWarga;
    String  strnominal_donasi_warga;
    String  strStatusMember;

    private List<DataPesantren> lstdatapresantren;

    String stridpresantren1;
    String strnamapesantren1;
    String stridpresantren2;
    String strnamapesantren2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_anggota3);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        etIdWarga = (EditText) findViewById(R.id.idWarga);
        strIdWarga = globalVariable.getId();
        etIdWarga.setText(strIdWarga);

        spYaTidakPesantren =(Spinner) findViewById(R.id.spYaTidakPesantren);
        etLamaPesantren = (EditText) findViewById(R.id.etLamaPesantren);
        spPesantren1 =(Spinner) findViewById(R.id.spPesantren1);
        spPesantren2 =(Spinner) findViewById(R.id.spPesantren2);
        spInfaq = (Spinner) findViewById(R.id.spInfaq);
        spJalur = (Spinner) findViewById(R.id.spJalur);
        etnominal_donasi = (EditText) findViewById(R.id.etnominal_donasi);
        spInfaqWarga = (Spinner) findViewById(R.id.spInfaqWarga);
        spJalurWarga = (Spinner) findViewById(R.id.spJalurWarga);
        etnominal_donasi_warga = (EditText) findViewById(R.id.etnominal_donasi_warga);
        spStatusMember = (Spinner) findViewById(R.id.spStatusMember);



        subDataPesantren1();
        subDataPesantren2();

        btnNext3 = (Button) findViewById(R.id.btnNext3);
        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (spPesantren.getSelectedItem().toString().length() ==0){
//                    Toast.makeText(getApplicationContext(), "Pesantren Belum Dipilih" , Toast.LENGTH_LONG).show();
//                    spPesantren.requestFocus();
//                    return;
//                }


                globalVariable.setYaTidakPesantren(spYaTidakPesantren.getSelectedItem().toString());
                globalVariable.setLamaPesantren(etLamaPesantren.getText().toString());
                globalVariable.setIdPesantren1(spPesantren1.getSelectedItem().toString());
                globalVariable.setIdPesantren2(spPesantren2.getSelectedItem().toString());
                globalVariable.setInfak(spInfaq.getSelectedItem().toString());
                globalVariable.setJalur(spJalur.getSelectedItem().toString());
                globalVariable.setNominalDonasi(etnominal_donasi.getText().toString());
                globalVariable.setInfakWarga(spInfaqWarga.getSelectedItem().toString());
                globalVariable.setJalur(spJalurWarga.getSelectedItem().toString());
                globalVariable.setNominalDonasiWarga(etnominal_donasi_warga.getText().toString());
                globalVariable.setIdStatusMember(spStatusMember.getSelectedItem().toString());

                strYaTidakPesantren = globalVariable.getYaTidakPesantren();
                strLamaPesantren = globalVariable.getLamaPesantren();
                strPesantren1 = globalVariable.getIdPesantren1();
                strPesantren2 = globalVariable.getIdPesantren2();
                strInfaq = globalVariable.getInfak();
                strJalur = globalVariable.getJalur();
                strnominal_donasi = globalVariable.getNominalDonasi();
                strInfaqWarga = globalVariable.getInfakWarga();
                strJalurWarga = globalVariable.getJalurWarga();
                strnominal_donasi_warga = globalVariable.getNominalDonasiWarga();
                strStatusMember = globalVariable.getIdStatusMember();

                saveDataInput();

            }
        });

        btnBack2 = (Button) findViewById(R.id.btnBack2);
        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(FormAnggota3Activity.this, FormAnggota2Activity.class);
//                intent.putExtra("id_user",username);
//                startActivity(intent);
//                finish();

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
        api.insertdataanggota2(
                "form3",
                strIdWarga,
                strYaTidakPesantren,
                strLamaPesantren,
                strPesantren1,
                strPesantren2,
                strInfaq,
                strJalur,
                strnominal_donasi,
                strInfaqWarga,
                strJalurWarga,
                strnominal_donasi_warga,
                strStatusMember,
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

                        /*Intent intent = new Intent(getApplicationContext(), FormAnggota4Activity.class);

                        startActivity(intent);

                        finish();*/

                        Toast.makeText(FormAnggota3Activity.this, "Data Berhasil Di Simpan", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(FormAnggota3Activity.this, "Kesalahan Koneksi Data" + error.getMessage(), Toast.LENGTH_LONG).show();
                        //btnsetting.setEnabled(true);
                    }
                }
        );
    }

    private void subDataPesantren1() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://www.terpusat.com") //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataDaftar yang ada di api
        api.getDataPesantren(
                new Callback<CDataPesantren>()

                {
                    @Override
                    public void success(CDataPesantren cdatapesantren, Response response) {
                        lstdatapresantren = new ArrayList<DataPesantren>();

                        lstdatapresantren = cdatapesantren.getDataPesantren();

                        Log.e("OPO IKI", String.valueOf(lstdatapresantren.size()));

                        final String[] tsidpesantren1 = new String[lstdatapresantren.size()];
                        final String[] tsnamapesantren1 = new String[lstdatapresantren.size()];
                        final String[] alamatpesantren1 = new String[lstdatapresantren.size()];
                        final String[] provinsi1 = new String[lstdatapresantren.size()];
                        final String[] kabkot1 = new String[lstdatapresantren.size()];
                        final String[] kecamatan1 = new String[lstdatapresantren.size()];
                        final String[] desa1 = new String[lstdatapresantren.size()];
                        final String[] negara1 = new String[lstdatapresantren.size()];

                        for (int i = 0; i < lstdatapresantren.size(); i++) {
                            //Storing names to string array
                            tsidpesantren1[i] = lstdatapresantren.get(i).getid_pesantren();//getnama hrs sama dgn yang di DataDaftar
                            tsnamapesantren1[i] = lstdatapresantren.get(i).getnama_pesantren();
                            alamatpesantren1[i] = lstdatapresantren.get(i).getalamat();
                            provinsi1[i] = lstdatapresantren.get(i).getprovinsi_pesantren();
                            kabkot1[i] = lstdatapresantren.get(i).getkabkot_pesantren();
                            kecamatan1[i] = lstdatapresantren.get(i).getkecamatan_pesantren();
                            desa1[i] = lstdatapresantren.get(i).getdesa_pesantren();
                            negara1[i] = lstdatapresantren.get(i).getnegara();
                        }

                        ArrayAdapter adapter = new ArrayAdapter<String>(FormAnggota3Activity.this, R.layout.support_simple_spinner_dropdown_item, tsnamapesantren1);
                        spPesantren1.setAdapter(adapter);

                        stridpresantren1 = tsidpesantren1[0].toString();

                        spPesantren1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                stridpresantren1 = tsidpesantren1[position].toString();
                                strnamapesantren1 = parent.getItemAtPosition(position).toString();
                                //Toast.makeText(FormAnggota1Activity.this, stridprovinsi.toString() , Toast.LENGTH_LONG).show();

                            }

                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(FormAnggota3Activity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                    }
                }

        );
    }


    private void subDataPesantren2() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://www.terpusat.com") //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataDaftar yang ada di api
        api.getDataPesantren(
                new Callback<CDataPesantren>()

                {
                    @Override
                    public void success(CDataPesantren cdatapesantren, Response response) {

                        lstdatapresantren = new ArrayList<DataPesantren>();

                        lstdatapresantren = cdatapesantren.getDataPesantren();

                        final String[] tsidpesantren2 = new String[lstdatapresantren.size()];
                        final String[] tsnamapesantren2 = new String[lstdatapresantren.size()];
                        final String[] alamatpesantren2 = new String[lstdatapresantren.size()];
                        final String[] provinsi2 = new String[lstdatapresantren.size()];
                        final String[] kabkot2 = new String[lstdatapresantren.size()];
                        final String[] kecamatan2 = new String[lstdatapresantren.size()];
                        final String[] desa2 = new String[lstdatapresantren.size()];
                        final String[] negara2 = new String[lstdatapresantren.size()];

                        for (int i = 0; i < lstdatapresantren.size(); i++) {
                            //Storing names to string array
                            tsidpesantren2[i] = lstdatapresantren.get(i).getid_pesantren();//getnama hrs sama dgn yang di DataDaftar
                            tsnamapesantren2[i] = lstdatapresantren.get(i).getnama_pesantren();
                            alamatpesantren2[i] = lstdatapresantren.get(i).getalamat();
                            provinsi2[i] = lstdatapresantren.get(i).getprovinsi_pesantren();
                            kabkot2[i] = lstdatapresantren.get(i).getkabkot_pesantren();
                            kecamatan2[i] = lstdatapresantren.get(i).getkecamatan_pesantren();
                            desa2[i] = lstdatapresantren.get(i).getdesa_pesantren();
                            negara2[i] = lstdatapresantren.get(i).getnegara();

                        }
                        ArrayAdapter adapter = new ArrayAdapter<String>(FormAnggota3Activity.this, R.layout.support_simple_spinner_dropdown_item, tsnamapesantren2);
                        spPesantren2.setAdapter(adapter);


                        stridpresantren2 = tsidpesantren2[0].toString();
                        //Toast.makeText(FormAnggota1Activity.this, stridprovinsi.toString() , Toast.LENGTH_LONG).show();


                        spPesantren2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                stridpresantren2 = tsidpesantren2[position].toString();
                                strnamapesantren2 = parent.getItemAtPosition(position).toString();
                                //Toast.makeText(FormAnggota1Activity.this, stridprovinsi.toString() , Toast.LENGTH_LONG).show();

                            }

                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(FormAnggota3Activity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                    }
                }

        );
    }


}



