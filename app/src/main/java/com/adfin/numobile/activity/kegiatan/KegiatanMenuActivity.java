package com.adfin.numobile.activity.kegiatan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.adfin.numobile.R;

public class KegiatanMenuActivity extends AppCompatActivity {

    Button  btnGalleryPeristiwa,
            btnLaporanPeristiwa,
            btnBroadcastPeristiwa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan_menu);

        btnGalleryPeristiwa = (Button) findViewById(R.id.btnGalleryPeristiwa);
        btnLaporanPeristiwa = (Button) findViewById(R.id.btnLaporanPeristiwa);
        btnBroadcastPeristiwa = (Button) findViewById(R.id.btnBroadcastPeristiwa);


        btnGalleryPeristiwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KegiatanMenuActivity.this, PeristiwaGallery.class);
                startActivity(i);

            }
        });

        btnLaporanPeristiwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KegiatanMenuActivity.this, PeristiwaMenuLaporan.class);
                startActivity(i);

            }
        });

        btnBroadcastPeristiwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KegiatanMenuActivity.this, PeristiwaBoardcast.class);
                startActivity(i);
            }
        });

    }
}
