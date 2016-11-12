package com.adfin.numobile.activity.promosi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adfin.numobile.R;

public class PromosiMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promosi_menu);

        Button btnPenawaran = (Button)findViewById(R.id.btnPenawaran);
        Button btnPermintaan = (Button)findViewById(R.id.btnPermintaan);
        Button btnPerusahaan = (Button)findViewById(R.id.btnPerusahaan);

        btnPenawaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromosiMenuActivity.this, PromosiPenawaran.class);
                startActivity(intent);
            }
        });

        btnPermintaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromosiMenuActivity.this, PromosiPermintaan.class);
                startActivity(intent);
            }
        });

        btnPerusahaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromosiMenuActivity.this, PromosiPerusahaan.class);
                startActivity(intent);
            }
        });

    }
}
