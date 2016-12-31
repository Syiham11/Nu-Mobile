package com.adfin.numobile.activity.kearsipan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.adfin.numobile.R;
import com.adfin.numobile.helper.Session;

public class KearsipanSuratKeluar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kearsipan_surat_keluar);

        Log.e(
                "Nama User Login",
                Session.with(getApplicationContext()).load("user_nu").get("nama")
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_kearsipan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_chatting:
                intent = new Intent(KearsipanSuratKeluar.this, KearsipanChatting.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.action_surat:
                intent = new Intent(KearsipanSuratKeluar.this, KearsipanSuratMasuk.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
