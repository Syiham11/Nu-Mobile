package com.adfin.numobile.activity.kearsipan;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.adfin.numobile.R;
import com.adfin.numobile.activity.kegiatan.KegiatanMenuActivity;
import com.adfin.numobile.activity.kegiatan.PeristiwaBoardcast;

public class KearsipanSuratKeluar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kearsipan_surat_keluar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_kearsipan, menu);
        return true;
    }

}
