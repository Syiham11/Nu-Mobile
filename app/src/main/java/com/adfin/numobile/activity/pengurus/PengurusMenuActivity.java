package com.adfin.numobile.activity.pengurus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.activity.MainActivity;
import com.adfin.numobile.helper.ListAdapterAll;
import com.adfin.numobile.model.CDataSDM;
import com.adfin.numobile.model.DataSDM;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PengurusMenuActivity extends AppCompatActivity {

    public static final String ROOT_URL = "http://numobile.id";

    //ListView listView;
    RecyclerView recyclerView;


    private List<DataSDM> lstdataSDM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengurus_menu);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);

        //final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        getDataSDM();

    }

    private void getDataSDM() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataDaftar yang ada di api
        api.getDataSDM(
                new Callback<CDataSDM>()
                {
                    @Override
                    public void success(CDataSDM cdatasdm, Response response) {
                        lstdataSDM = new ArrayList<DataSDM>();

                        lstdataSDM = cdatasdm.getDataSDM();

                        // Helper
                        String[] helper = {"pengurus","id_sdm"};

                        final String[] idPejabat = new String[lstdataSDM.size()];
                        final String[] namaPejabat = new String[lstdataSDM.size()];
                        final String[] teleponPejabat = new String[lstdataSDM.size()];
                        final String[] imagePejabat = new String[lstdataSDM.size()];

                        for (int i = 0; i < lstdataSDM.size(); i++) {
                            idPejabat[i] = lstdataSDM.get(i).getid_sdm();
                            namaPejabat[i] = lstdataSDM.get(i).getnama();
                            teleponPejabat[i] = lstdataSDM.get(i).gettelp();
                            imagePejabat[i] = lstdataSDM.get(i).getimage();

                        }

                        ListAdapterAll adapter=new ListAdapterAll(PengurusMenuActivity.this, imagePejabat, namaPejabat, teleponPejabat,
                                idPejabat, helper, MainActivity.class);
                        //membuat adapter baru untuk reyclerview
                        recyclerView.setAdapter(adapter);
                        //menset nilai dari adapter
                        recyclerView.setHasFixedSize(true);
                        //menset setukuran
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

//                        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                                //  strnamagames = parent.getItemAtPosition(position).toString();
//                                //   strnilaigames = lstdatagamesnilai.get(position).gethargajual().toString();
//                                //   tvhargajual.setText("Rp " + strnilaigames);
//                                strnomorduel = datanomorduel[position].toString();
//
//                                //      Toast.makeText(getBaseContext(), strjenisredeem.toString(), Toast.LENGTH_LONG).show();
//
//                                Intent intent = new Intent(getApplicationContext(), BoardDuelActivity.class);
//                                intent.putExtra("kirimnamauser", strnamauser.toString());
//                                intent.putExtra("kirimemail", stremail.toString());
//                                intent.putExtra("kirimavatar", stravatar.toString());
//                                intent.putExtra("kirimpoint", strpoint.toString());
//                                intent.putExtra("kirimstatus", strstatus.toString());
//                                intent.putExtra("kirimhint", strhint.toString());
//                                intent.putExtra("kirimnomorduel", strnomorduel.toString());
//
//                                startActivity(intent);
//
//
//                            }
//                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(PengurusMenuActivity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
