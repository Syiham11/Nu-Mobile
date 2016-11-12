package com.adfin.numobile.activity.warga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.adfin.numobile.R;

public class AnggotaLihatActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggota_lihat);

        listView = (ListView) findViewById(R.id.list);



    }
}
