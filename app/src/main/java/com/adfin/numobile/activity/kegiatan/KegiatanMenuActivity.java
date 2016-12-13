package com.adfin.numobile.activity.kegiatan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.adfin.numobile.R;
import com.adfin.numobile.activity.pengurus.PengurusOrganisasi;

public class KegiatanMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan_menu);

        Button btnPhoto = (Button)findViewById(R.id.btnPhoto);
        Button btnVideo = (Button)findViewById(R.id.btnVideo);


        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KegiatanMenuActivity.this, PeristiwaPhoto.class);

                startActivity(intent);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KegiatanMenuActivity.this, PeristiwaVideo.class);

                startActivity(intent);
            }
        });


    }
}
