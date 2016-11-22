package com.adfin.numobile.activity.warga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.activity.GlobalClass;
import com.adfin.numobile.helper.ListAdapterWarga;
import com.adfin.numobile.model.CDataPesantren;
import com.adfin.numobile.model.CDataWarga;
import com.adfin.numobile.model.DataPesantren;
import com.adfin.numobile.model.DataWarga;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AnggotaLihatActivity extends AppCompatActivity {

    //ListView listView;
    RecyclerView recyclerView;

    String  strid_warga;
    String  strnama;
    String  strtlp;

    private List<DataWarga> lstdatawarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggota_lihat);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);

        //final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        getDataWarga();
    }

    private void getDataWarga() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://www.terpusat.com") //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataDaftar yang ada di api
        api.getDataWarga(
                new Callback<CDataWarga>()
                {
                    @Override
                    public void success(CDataWarga cdatawarga, Response response) {
                        lstdatawarga = new ArrayList<DataWarga>();

                        lstdatawarga = cdatawarga.getDataWarga();

                        final String[] namaWarga = new String[lstdatawarga.size()];
                        final String[] alamatWarga = new String[lstdatawarga.size()];
                        final String[] imageWarga = new String[lstdatawarga.size()];

                        for (int i = 0; i < lstdatawarga.size(); i++) {
                            namaWarga[i] = lstdatawarga.get(i).getnama();
                            alamatWarga[i] = lstdatawarga.get(i).getalamat();
                            imageWarga[i] = lstdatawarga.get(i).getusername();
                        }

                        ListAdapterWarga adapter=new ListAdapterWarga(AnggotaLihatActivity.this, imageWarga, namaWarga, alamatWarga);
                        //membuat adapter baru untuk reyclerview
                        recyclerView.setAdapter(adapter);
                        //menset nilai dari adapter
                        recyclerView.setHasFixedSize(true);
                        //menset setukuran
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(AnggotaLihatActivity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
