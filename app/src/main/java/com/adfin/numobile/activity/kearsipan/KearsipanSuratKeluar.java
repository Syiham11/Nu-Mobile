package com.adfin.numobile.activity.kearsipan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.helper.ListAdapterAll;
import com.adfin.numobile.helper.Session;
import com.adfin.numobile.model.CDataSuratMasuk;
import com.adfin.numobile.model.DataSuratMasuk;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KearsipanSuratKeluar extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private List<DataSuratMasuk> listsuratmasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kearsipan_surat_keluar);

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);

        getDataSuratMasuk();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataSuratMasuk();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_kearsipan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_chatting:
                intent = new Intent(KearsipanSuratKeluar.this, KearsipanChatting.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.action_surat:
                intent = new Intent(KearsipanSuratKeluar.this, KearsipanSuratMasuk.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getDataSuratMasuk() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://numobile.id") //Setting the Root URL
                .build(); //Finally building the adapter

        ModulAPI api = adapter.create(ModulAPI.class);

        api.getDataSuratMasuk(
                Session.with(getApplicationContext()).load("user_nu").get("id_warga"),
                new Callback<CDataSuratMasuk>()
                {
                    @Override
                    public void success(CDataSuratMasuk cdatasuratmasuk, Response response) {
                        listsuratmasuk = new ArrayList<>();

                        listsuratmasuk = cdatasuratmasuk.getDataSuratMasuk();

                        final String[] tanggal = new String[listsuratmasuk.size()];
                        final String[] thumb = new String[listsuratmasuk.size()];

                        for (int i = 0; i < listsuratmasuk.size(); i++) {
                            tanggal[i] = listsuratmasuk.get(i).gettanggal_surat();
                            thumb[i] = listsuratmasuk.get(i).getimage();
                        }

                        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
                        ListAdapterAll adapter=new ListAdapterAll(KearsipanSuratKeluar.this, thumb, tanggal, new String[0]);
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
                                Toast.makeText(KearsipanSuratKeluar.this, merror + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
        );
    }
}
