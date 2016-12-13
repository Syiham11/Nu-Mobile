package com.adfin.numobile.activity.kegiatan;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adfin.numobile.R;
import com.adfin.numobile.model.DataWarga;

import java.util.List;

public class PeristiwaBoardcast extends AppCompatActivity {

    List<DataWarga> wargaList;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peristiwa_boardcast);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PeristiwaBoardcast.this, KegiatanMenuActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }
}
