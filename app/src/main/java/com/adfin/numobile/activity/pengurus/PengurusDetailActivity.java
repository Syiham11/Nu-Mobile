package com.adfin.numobile.activity.pengurus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.activity.MainActivity;
import com.adfin.numobile.model.CDataDetail;
import com.adfin.numobile.model.DataSDM;
import com.adfin.numobile.model.DataWarga;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PengurusDetailActivity extends AppCompatActivity {

    final Context context = this;

    private String mKey, mVal, mId;

    ImageView photo;
    TextView name, addr, email, phone;

    private List<DataSDM> lstdatasdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengurus_detail);

        photo = (ImageView) findViewById(R.id.pengurus_photo);
        name = (TextView) findViewById(R.id.pengurus_name);
        addr = (TextView) findViewById(R.id.pengurus_address);
        email = (TextView) findViewById(R.id.pengurus_email);
        phone = (TextView) findViewById(R.id.pengurus_phone);

        mKey = getIntent().getStringExtra("key");
        mVal = getIntent().getStringExtra("val");
        mId = getIntent().getStringExtra("id");

        if(mKey == null && mVal == null && mId == null){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        __getDataDetail();
    }

    private void __getDataDetail() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://numobile.id") //Setting the Root URL
                .build(); //Finally building the adapter

        ModulAPI api = adapter.create(ModulAPI.class);

        api.getDetail(
                mKey,
                mVal,
                mId,
                new Callback<CDataDetail>(){
                    @Override
                    public void success(CDataDetail cdatasdm, Response response) {
                        lstdatasdm = cdatasdm.getDataSDM();

                        if(lstdatasdm.size() != 1)
                            Toast.makeText(context, "Gagal mengambil data", Toast.LENGTH_LONG).show();
                        else{
                            Picasso.with(context).load(lstdatasdm.get(0).getimage()).resize(200, 200).into(photo);
                            name.setText(lstdatasdm.get(0).getnama());
                            addr.setText("Alamat : " + lstdatasdm.get(0).getalamat());
                            email.setText("Email : " + lstdatasdm.get(0).getemail());
                            phone.setText("Telp : " + lstdatasdm.get(0).gettelp());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(context, "Gagal mengambil data", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }
}
