package com.adfin.numobile.activity.pengurus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adfin.numobile.R;

public class PengurusMenuActivity extends AppCompatActivity {

    Button btn_struktur_organisasi, btn_pengurus_besar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengurus_menu);

        btn_struktur_organisasi = (Button)findViewById(R.id.btn_struktur_organisasi);
        btn_pengurus_besar = (Button)findViewById(R.id.btn_pengurus_besar);

        btn_struktur_organisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengurusMenuActivity.this, PengurusOrganisasi.class);

                startActivity(intent);
            }
        });

        btn_pengurus_besar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengurusMenuActivity.this, PengurusBesarActivity.class);

                startActivity(intent);
            }
        });

    }
}
