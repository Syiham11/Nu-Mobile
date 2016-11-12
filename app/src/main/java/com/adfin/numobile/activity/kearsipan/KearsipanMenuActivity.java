package com.adfin.numobile.activity.kearsipan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adfin.numobile.R;

public class KearsipanMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kearsipan_menu);

        Button btn_SuratMasuk =(Button)findViewById(R.id.btn_surat_masuk);
        Button btn_SuratKeluar =(Button)findViewById(R.id.btn_surat_keluar);
        Button btn_SuratDisposisi =(Button)findViewById(R.id.btn_surat_disposisi);
        Button btn_Chatting =(Button)findViewById(R.id.btn_chatting);

        btn_SuratMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KearsipanMenuActivity.this, KearsipanSuratMasuk.class);

                startActivity(intent);
            }
        });

        btn_SuratKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KearsipanMenuActivity.this, KearsipanSuratKeluar.class);

                startActivity(intent);
            }
        });

        btn_SuratDisposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KearsipanMenuActivity.this, KearsipanSuratDisposisi.class);

                startActivity(intent);
            }
        });

        btn_Chatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KearsipanMenuActivity.this, KearsipanChatting.class);

                startActivity(intent);
            }
        });

    }
}
