package com.adfin.numobile.activity.kegiatan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adfin.numobile.R;
import com.adfin.numobile.activity.pengurus.PengurusOrganisasi;

public class PeristiwaMenuLaporan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peristiwa_menu_laporan);

        Button btnPhoto = (Button)findViewById(R.id.btnPhoto);
        Button btnVideo = (Button)findViewById(R.id.btnVideo);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PeristiwaMenuLaporan.this, PengurusOrganisasi.class);

                startActivity(intent);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PeristiwaMenuLaporan.this, PengurusOrganisasi.class);

                startActivity(intent);
            }
        });

    }
}
