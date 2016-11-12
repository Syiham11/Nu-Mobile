package com.adfin.numobile;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class combodynamic extends AppCompatActivity {

    EditText editText;
  Integer vcek;
    Spinner spinnerKerja;
    String[] kerja = {
            "Pertanian",
            "Peternakan",
            "Lain-Lain",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combodynamic);

        vcek=0;

        editText = (EditText)findViewById(R.id.editText);
        spinnerKerja =(Spinner) findViewById(R.id.spinnerKerja);
        ArrayAdapter adaptertampilan = new ArrayAdapter<String>(combodynamic.this, R.layout.support_simple_spinner_dropdown_item, kerja);
        spinnerKerja.setAdapter(adaptertampilan);
        spinnerKerja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                editText.setText(spinnerKerja.getItemAtPosition(position).toString());

                // strstatusaktif = (String) spinnertampilan.getItemAtPosition(position);

                //if (vcek==0) {
                    //vcek=1;
//                    if (spinnerKerja.getItemAtPosition(position).toString().equals("Lain-Lain")) {
//                        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);
//
//                        // create the layout params that will be used to define how your
//                        // button will be displayed
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
//
//
//                        LinearLayout ll = new LinearLayout(getApplicationContext());
//                        //ll.setOrientation(LinearLayout.HORIZONTAL);
//
//                        // Create TextView
////                    TextView product = new TextView(this);
////                    product.setText(" Product"+j+"    ");
////                    ll.addView(product);
//                        // lm.setOrientation(LinearLayout.HORIZONTAL);
//
//                        // Create TextView
//                        EditText etpekerjaan = new EditText(getApplicationContext());
//                        etpekerjaan.setText("");
//                        ll.addView(etpekerjaan);
//                        lm.addView(ll);
//
//                        Toast.makeText(spinnerKerja.getContext(),
//                                "OnItemSelectedListener : " + spinnerKerja.getItemAtPosition(position).toString(),
//                                Toast.LENGTH_SHORT).show();
//                    } else {
//                        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);
//
//                        // create the layout params that will be used to define how your
//                        // button will be displayed
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
//
//
//                        LinearLayout ll = new LinearLayout(getApplicationContext());
//                        //ll.setOrientation(LinearLayout.HORIZONTAL);
//
//                        // Create TextView
////                    TextView product = new TextView(this);
////                    product.setText(" Product"+j+"    ");
////                    ll.addView(product);
//                        // lm.setOrientation(LinearLayout.HORIZONTAL);
//
//                        // Create TextView
//                        EditText etpekerjaan = new EditText(getApplicationContext());
//                        etpekerjaan.setTextColor(Color.BLACK);
//                        etpekerjaan.setText(spinnerKerja.getItemAtPosition(position).toString());
//                        ll.addView(etpekerjaan);
//                        lm.addView(ll);
//
//                    }
//                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        spinnerKerja.setAdapter(adapter);
//        spinnerKerja.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//                if (spinnerKerja.getItemAtPosition(position).toString().equals("Lain-Lain")) {
//                    final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);
//
//                    // create the layout params that will be used to define how your
//                    // button will be displayed
//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                            ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
//
//
//                    LinearLayout ll = new LinearLayout(getApplicationContext());
//                    //ll.setOrientation(LinearLayout.HORIZONTAL);
//
//                    // Create TextView
////                    TextView product = new TextView(this);
////                    product.setText(" Product"+j+"    ");
////                    ll.addView(product);
//                    // lm.setOrientation(LinearLayout.HORIZONTAL);
//
//                    // Create TextView
//                    TextView product = new TextView(getApplicationContext());
//                    product.setText(" test");
//                    ll.addView(product);
//                    lm.addView(ll);
//
//                    Toast.makeText(spinnerKerja.getContext(),
//                            "OnItemSelectedListener : " + spinnerKerja.getItemAtPosition(position).toString(),
//                            Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });


       //spinnerKerja.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }
}
