package com.adfin.numobile.activity.doa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.activity.MainActivity;
import com.adfin.numobile.helper.ListAdapterAll;
import com.adfin.numobile.model.CDataDoa;
import com.adfin.numobile.model.DataDoa;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DoaMenuActivity extends AppCompatActivity {

    public static final String ROOT_URL = "http://numobile.id";

    //ListView listView;
    RecyclerView recyclerView;


    private List<DataDoa> lstdataDoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa_menu);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);

        getDataDoa();

    }

    private void getDataDoa() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        //getDataDaftar yang ada di api
        api.getDataDoa(
                new Callback<CDataDoa>()
                {
                    @Override
                    public void success(CDataDoa cdatadoa, Response response) {
                        lstdataDoa = new ArrayList<DataDoa>();

                        lstdataDoa = cdatadoa.getDataDoa();

                        // Helper
                        String[] helper = {"doa","id_doa"};

                        final String[] idDoa = new String[lstdataDoa.size()];
                        final String[] namaDoa = new String[lstdataDoa.size()];
                        final String[] headerDoa = new String[lstdataDoa.size()];;

                        for (int i = 0; i < lstdataDoa.size(); i++) {
                            idDoa[i] = lstdataDoa.get(i).getnama_doa();
                            namaDoa[i] = lstdataDoa.get(i).getnama_doa();
                            headerDoa[i] = lstdataDoa.get(i).getimage_header();

                        }

                        ListAdapterAll adapter=new ListAdapterAll(DoaMenuActivity.this, headerDoa, namaDoa, new String[0],
                                idDoa, helper, MainActivity.class);
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

                        Toast.makeText(DoaMenuActivity.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
