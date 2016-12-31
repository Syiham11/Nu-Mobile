package com.adfin.numobile.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.adfin.numobile.R;
import com.adfin.numobile.activity.desa.DesaMenuActivity;
import com.adfin.numobile.activity.doa.DoaMenuActivity;
import com.adfin.numobile.activity.dompet.DompetMenuActivity;
import com.adfin.numobile.activity.kearsipan.KearsipanSuratKeluar;
import com.adfin.numobile.activity.kegiatan.PeristiwaBoardcast;
import com.adfin.numobile.activity.kelautan.KelautanMenuActivity;
import com.adfin.numobile.activity.kesehatan.KesehatanMenuActivity;
import com.adfin.numobile.activity.konsultasi.KonsultasiMenuActivity;
import com.adfin.numobile.activity.pasar.PasarMenuActivity;
import com.adfin.numobile.activity.pelayanan.PelayananMenuActivity;
import com.adfin.numobile.activity.pendidikan.PendidikanMenuActivity;
import com.adfin.numobile.activity.pengurus.PengurusMenuActivity;
import com.adfin.numobile.activity.pertanian.PertanianMenuActivity;
import com.adfin.numobile.activity.promosi.PromosiMenuActivity;
import com.adfin.numobile.activity.warga.AnggotaLihatActivity;
import com.adfin.numobile.activity.warung.WarungMenuActivity;
import com.adfin.numobile.helper.Nengkene;
import com.adfin.numobile.helper.Session;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    Nengkene location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionExternal();

        location = Nengkene.with(getApplicationContext()).start().get();

        Log.e(
                "Nama User Login",
                Session.with(getApplicationContext()).load("user_nu").get("nama")
        );

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
                Intent intent = new Intent(MainActivity.this, AnggotaLihatActivity.class);

                startActivity(intent);
            }
        });

        btnArsip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KearsipanSuratKeluar.class);

                startActivity(intent);
            }
        });

        btnKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PeristiwaBoardcast.class);

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

    @AfterPermissionGranted(200)
    private void permissionExternal() {
        String[] perms = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        if ( ! EasyPermissions.hasPermissions(this, perms) ) {
            EasyPermissions.requestPermissions(this, "This app needs access to your camera so you can take pictures.",
                    200, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "This app may not work correctly without the requested permissions. Open the app settings screen to modify app permissions.")
                    .setTitle("Permissions Required")
                    .setPositiveButton("Settings")
                    .setNegativeButton("Settings dialog canceled", null)
                    .setRequestCode(500)
                    .build()
                    .show();
        }

    }
}
