package com.adfin.numobile.activity.warga;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.helper.ListAdapterAll;
import com.adfin.numobile.model.CDataWarga;
import com.adfin.numobile.model.DataWarga;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AnggotaLihatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private List<DataWarga> lstdatawarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggota_lihat);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnggotaLihatActivity.this, FormAnggota1Activity.class);
                startActivityForResult(i, 1);
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);

        getDataWarga();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataWarga();
            }
        });
    }

    private void getDataWarga() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://numobile.id") //Setting the Root URL
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
                        lstdatawarga = new ArrayList<>();

                        lstdatawarga = cdatawarga.getDataWarga();

                        // Helper
                        String[] helper = {"warga","id_warga"};

                        final String[] idWarga = new String[lstdatawarga.size()];
                        final String[] namaWarga = new String[lstdatawarga.size()];
                        final String[] alamatWarga = new String[lstdatawarga.size()];
                        final String[] imageWarga = new String[lstdatawarga.size()];

                        for (int i = 0; i < lstdatawarga.size(); i++) {
                            idWarga[i] = lstdatawarga.get(i).getid_warga();
                            namaWarga[i] = lstdatawarga.get(i).getnama();
                            alamatWarga[i] = lstdatawarga.get(i).getalamat();
                            imageWarga[i] = lstdatawarga.get(i).getphoto();
                        }

                        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);

                        ListAdapterAll adapter=new ListAdapterAll(AnggotaLihatActivity.this,
                                imageWarga, namaWarga, alamatWarga, idWarga,
                                helper, AnggotaDetailActivity.class);

                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        final String merror = error.getMessage();

                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(AnggotaLihatActivity.this, merror + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
        );
    }
}
