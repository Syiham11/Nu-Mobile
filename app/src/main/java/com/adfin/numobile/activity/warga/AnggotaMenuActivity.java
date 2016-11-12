package com.adfin.numobile.activity.warga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.adfin.numobile.R;
import com.adfin.numobile.activity.GlobalClass;

public class AnggotaMenuActivity extends AppCompatActivity {

    String username;
    Button btnlihat, btninput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggota_menu);

//        Intent i = getIntent();
//        username = i.getStringExtra("id_warga");

        btninput = (Button) findViewById(R.id.btn_input_anggota);
        btnlihat = (Button) findViewById(R.id.btn_daftar_anggota);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        //pertama kali set kosong semua inputan yg ada di form 1
        globalVariable.setUsername("");
        globalVariable.setNamaLengkap("");
        globalVariable.setNoKtp("");
        globalVariable.setTempatLahir("");
        globalVariable.setTanggalLahir("");
        globalVariable.setJenisKelamin("Pria");
        globalVariable.setStatusPerkawinan("Kawin");
        globalVariable.setAlamat("");
        globalVariable.setProvinsi("ACEH");
        globalVariable.setKabupaten("Kabupaten Simeulue");
        globalVariable.setKecamatan("Teupah Barat");
        globalVariable.setDesa("LEUBANG HULU");
        globalVariable.setKodePos("");
        globalVariable.setTelepon("");
        globalVariable.setHandphone("");
        globalVariable.setEmail("");
        globalVariable.setFacebook("");
        globalVariable.setTwitter("");
        globalVariable.setInstagram("");
        globalVariable.setPathh("");


        btninput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnggotaMenuActivity.this, FormAnggota1Activity.class);
                startActivity(intent);
            }
        });


        btnlihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnggotaMenuActivity.this, AnggotaLihatActivity.class);
                //intent.putExtra("id_user",username);
                startActivity(intent);
                //finish();

            }
        });
    }
}
