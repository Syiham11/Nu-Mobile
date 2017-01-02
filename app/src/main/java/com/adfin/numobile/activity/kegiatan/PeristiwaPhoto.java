package com.adfin.numobile.activity.kegiatan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.activity.MainActivity;
import com.adfin.numobile.helper.ListAdapterAll;
import com.adfin.numobile.model.CDataPeristiwa;
import com.adfin.numobile.model.DataPeristiwa;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PeristiwaPhoto extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private List<DataPeristiwa> listDataPeristiwa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peristiwa_photo);

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataPeristiwa();
            }
        });

        getDataPeristiwa();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PeristiwaPhotoAdd.class);
                startActivity(i);
            }
        });
    }

    private void getDataPeristiwa() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://www.terpusat.com") //Setting the Root URL
                .build(); //Finally building the adapter

        ModulAPI api = adapter.create(ModulAPI.class);

        api.getDataPeristiwa("1",
                new Callback<CDataPeristiwa>()
                {
                    @Override
                    public void success(CDataPeristiwa cdataperistiwa, Response response) {
                        listDataPeristiwa = cdataperistiwa.getDataPeristiwa();

                        // Helper
                        String[] helper = {"peristiwa","id_peristiwa"};

                        final String[] id = new String[listDataPeristiwa.size()];
                        final String[] name = new String[listDataPeristiwa.size()];
                        final String[] other = new String[listDataPeristiwa.size()];
                        final String[] thumb = new String[listDataPeristiwa.size()];

                        for (int i = 0; i < listDataPeristiwa.size(); i++) {
                            id[i] = listDataPeristiwa.get(i).getid_peristiwa();
                            name[i] = listDataPeristiwa.get(i).getdeskripsi();
                            other[i] = listDataPeristiwa.get(i).getcreated_at();
                            thumb[i] = listDataPeristiwa.get(i).getpath();
                        }

                        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                        ListAdapterAll adapter = new ListAdapterAll(PeristiwaPhoto.this, thumb, name, other,
                                id, helper, MainActivity.class);
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
                                Toast.makeText(PeristiwaPhoto.this, merror + " Terjadi Kesalahan Koneksi ", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
        );
    }
}