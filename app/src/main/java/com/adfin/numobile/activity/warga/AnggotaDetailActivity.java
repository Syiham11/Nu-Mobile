package com.adfin.numobile.activity.warga;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.activity.MainActivity;
import com.adfin.numobile.model.CDataDetail;
import com.adfin.numobile.model.DataWarga;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AnggotaDetailActivity extends AppCompatActivity {

    final Context context = this;

    private String mKey, mVal, mId;

    ImageView photo;
    TextView name, bio, addr, email, phone;

    private List<DataWarga> lstdatawarga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_content);

        photo = (ImageView) findViewById(R.id.user_profile_photo);
        name = (TextView) findViewById(R.id.user_profile_name);
        bio = (TextView) findViewById(R.id.user_profile_short_bio);
        addr = (TextView) findViewById(R.id.user_profile_address);
        email = (TextView) findViewById(R.id.user_profile_email);
        phone = (TextView) findViewById(R.id.user_profile_telp);

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
                    public void success(CDataDetail cdatawarga, Response response) {
                        lstdatawarga = cdatawarga.getDataWarga();

                        if(lstdatawarga.size() != 1)
                            Toast.makeText(context, "Gagal mengambil data", Toast.LENGTH_LONG).show();
                        else{
                            Picasso.with(context).load(lstdatawarga.get(0).getphoto()).resize(100, 100).into(photo);
                            name.setText(lstdatawarga.get(0).getnama());
                            bio.setText(stripHtml(lstdatawarga.get(0).getkemampuan()));
                            addr.setText("Alamat : " + lstdatawarga.get(0).getalamat());
                            email.setText("Email : " + lstdatawarga.get(0).getemail());
                            phone.setText("Telp : " + lstdatawarga.get(0).gethp());
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
