package com.adfin.numobile.activity.pelayanan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adfin.numobile.R;

public class PelayananMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelayanan_menu);

        Button btnLibrary = (Button)findViewById(R.id.btnLibrary);
        Button btnChannel = (Button)findViewById(R.id.btnChannel);
        Button btnNetwork = (Button)findViewById(R.id.btnNetwork);


        btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PelayananMenuActivity.this, PelayananLibrary.class);

                startActivity(intent);
            }
        });


        btnChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PelayananMenuActivity.this, PelayananChannel.class);

                startActivity(intent);
            }
        });


        btnNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PelayananMenuActivity.this, PelayananNetwork.class);

                startActivity(intent);
            }
        });
    }
}
