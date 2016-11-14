package com.adfin.numobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.adfin.numobile.R;
import com.adfin.numobile.activity.desa.DesaMenuActivity;
import com.adfin.numobile.activity.doa.DoaMenuActivity;
import com.adfin.numobile.activity.dompet.DompetMenuActivity;
import com.adfin.numobile.activity.kearsipan.KearsipanMenuActivity;
import com.adfin.numobile.activity.kegiatan.KegiatanMenuActivity;
import com.adfin.numobile.activity.kelautan.KelautanMenuActivity;
import com.adfin.numobile.activity.kesehatan.KesehatanMenuActivity;
import com.adfin.numobile.activity.konsultasi.KonsultasiMenuActivity;
import com.adfin.numobile.activity.pasar.PasarMenuActivity;
import com.adfin.numobile.activity.pelayanan.PelayananMenuActivity;
import com.adfin.numobile.activity.pendidikan.PendidikanMenuActivity;
import com.adfin.numobile.activity.pengurus.PengurusMenuActivity;
import com.adfin.numobile.activity.pertanian.PertanianMenuActivity;
import com.adfin.numobile.activity.promosi.PromosiMenuActivity;
import com.adfin.numobile.activity.warga.AnggotaMenuActivity;
import com.adfin.numobile.activity.warung.WarungMenuActivity;

public class MainActivity extends AppCompatActivity {


    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent i = getIntent();
        // Receiving the Data
        //username = i.getStringExtra("id_user");

        //baris 1
        Button btnPengurus = (Button) findViewById(R.id.btn_pengurus);
        Button btnAnggota = (Button) findViewById(R.id.btn_anggota);
        Button btnArsip = (Button) findViewById(R.id.btn_arsip);
        Button btnKegiatan = (Button) findViewById(R.id.btn_kegiatan);
        //baris 2
        Button btnPelayanan = (Button) findViewById(R.id.btn_pelayanan);
        Button btnPromosi = (Button) findViewById(R.id.btn_promosi);
        Button btnWarung = (Button) findViewById(R.id.btn_warung);
        Button btnPasar = (Button) findViewById(R.id.btn_pasar);
        //baris 3
        Button btnDompet = (Button) findViewById(R.id.btn_dompet);
        Button btnKonsultasi = (Button) findViewById(R.id.btn_konsultasi);
        Button btnDoa = (Button) findViewById(R.id.btn_doa);
        Button btnDesa = (Button) findViewById(R.id.btn_desa);
        //baris 4
        Button btnKesehatan = (Button) findViewById(R.id.btn_kesehatan);
        Button btnPendidikan = (Button) findViewById(R.id.btn_pendidikan);
        Button btnPertanian = (Button) findViewById(R.id.btn_pertanian);
        Button btnKelautan = (Button) findViewById(R.id.btn_kelautan);


        //baris 1
        btnPengurus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PengurusMenuActivity.class);

                startActivity(intent);
            }
        });

        btnAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnggotaMenuActivity.class);

                startActivity(intent);
            }
        });

        btnArsip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KearsipanMenuActivity.class);

                startActivity(intent);
            }
        });

        btnKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KegiatanMenuActivity.class);

                startActivity(intent);
            }
        });


        //BARIS 2
        btnPelayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PelayananMenuActivity.class);

                startActivity(intent);
            }
        });

        btnPromosi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PromosiMenuActivity.class);

                startActivity(intent);
            }
        });

        btnWarung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WarungMenuActivity.class);

                startActivity(intent);
            }
        });

        btnPasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PasarMenuActivity.class);

                startActivity(intent);
            }
        });

        //BARIS 3
        btnDompet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DompetMenuActivity.class);

                startActivity(intent);
            }
        });

        btnKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KonsultasiMenuActivity.class);

                startActivity(intent);
            }
        });

        btnDoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DoaMenuActivity.class);

                startActivity(intent);
            }
        });

        btnDesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DesaMenuActivity.class);

                startActivity(intent);
            }
        });


        //BARIS 4
        btnKesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KesehatanMenuActivity.class);

                startActivity(intent);
            }
        });

        btnPendidikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PendidikanMenuActivity.class);

                startActivity(intent);
            }
        });

        btnPertanian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PertanianMenuActivity.class);

                startActivity(intent);
            }
        });

        btnKelautan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KelautanMenuActivity.class);

                startActivity(intent);
            }
        });
    }
}
