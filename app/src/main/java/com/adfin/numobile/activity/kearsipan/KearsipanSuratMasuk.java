package com.adfin.numobile.activity.kearsipan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.helper.Session;
import com.adfin.numobile.helper.Uploader;
import com.adfin.numobile.model.CDataWarga;
import com.adfin.numobile.model.DataWarga;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KearsipanSuratMasuk extends AppCompatActivity {

    EditText subject;
    Spinner spTo;
    ImageView photo;

    static String pathImage;
    Bitmap encImg;
    final Context context = this;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_PICK_IMAGE_REQUEST_CODE = 200;
    private static final String IMAGE_DIRECTORY_NAME = "NU";

    private List<DataWarga> lsdatawarga;
    HashMap data = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kearsipan_surat_masuk);

        spTo = (Spinner) findViewById(R.id.editDari);
        subject = (EditText) findViewById(R.id.editSubjek);
        photo = (ImageView)findViewById(R.id.gambar);

        __spTo();

        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( (subject != null && !subject.getText().toString().equals("")) && (pathImage!= null && !pathImage.equals("")) ) {
                    data.put("diteruskan_kepada", Session.with(getApplicationContext()).load("user_nu").get("id_warga"));
                    data.put("image", getStringImage(encImg));
                    data.put("isi_surat", subject.getText().toString());

                    Uploader.with(KearsipanSuratMasuk.this, KearsipanSuratKeluar.class)
                            .load("http://numobile.id/NUMobile/simpansurat.php")
                            .data(data)
                            .message("Mohon Tunggu ....")
                            .upload()
                            .finish();
                }else if ( (subject == null) && (pathImage != null && !pathImage.equals("")) ) {
                    AlertDialog.Builder ad = new AlertDialog.Builder(KearsipanSuratMasuk.this);
                    ad.setTitle("Peringatan");
                    ad.setMessage("Subjek Pesan Tidak Boleh Kosong");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    ad.setIcon(android.R.drawable.ic_dialog_alert);
                    ad.show();
                }else if ( (subject != null && !subject.getText().toString().equals("")) && (pathImage == null) ) {
                    AlertDialog.Builder ad = new AlertDialog.Builder(KearsipanSuratMasuk.this);
                    ad.setTitle("Peringatan");
                    ad.setMessage("Image belum dipilih");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    ad.setIcon(android.R.drawable.ic_dialog_alert);
                    ad.show();
                }else{
                    AlertDialog.Builder ad = new AlertDialog.Builder(KearsipanSuratMasuk.this);
                    ad.setTitle("Peringatan");
                    ad.setMessage("Form tidak valid");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    ad.setIcon(android.R.drawable.ic_dialog_alert);
                    ad.show();
                }
            }
        });

    }

    private String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_new) {
            // custom dialog
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_photo);
            dialog.setTitle("");

            Button browseButton = (Button) dialog.findViewById(R.id.buttonBrowse);

            browseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    browseImage(); dialog.dismiss();
                }
            });

            Button captureButton = (Button) dialog.findViewById(R.id.buttonCapture);

            captureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    captureImage(); dialog.dismiss();
                }
            });

            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    private void browseImage() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(
                Intent.createChooser(intent, "Select File"),
                CAMERA_PICK_IMAGE_REQUEST_CODE);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);
        pathImage = selectedImagePath;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        final int REQUIRED_SIZE = 300;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        encImg = BitmapFactory.decodeFile(selectedImagePath, options);

        photo.setImageBitmap(encImg);
    }

    private void onCaptureImageResult(Intent data) {
        ByteArrayOutputStream bytes;
        encImg = (Bitmap) data.getExtras().get("data");
        bytes = new ByteArrayOutputStream();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File imgFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), IMAGE_DIRECTORY_NAME);
        imgFolder.mkdirs();

        File image = new File(imgFolder, "img_nu_" + timeStamp + ".jpg");

        FileOutputStream fo;
        try {
            image.createNewFile();
            fo = new FileOutputStream(image);
            encImg.compress(Bitmap.CompressFormat.JPEG, 100, fo);
            fo.write(bytes.toByteArray());
            fo.close();
            pathImage = image.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        photo.setImageBitmap(encImg);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                onCaptureImageResult(data);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }else if (requestCode == CAMERA_PICK_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                onSelectFromGalleryResult(data);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),
                        "User cancelled image picker", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to pick image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(2, returnIntent);
        finish();
    }

    private void __spTo() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://numobile.id") //Setting the Root URL
                .build(); //Finally building the adapter

        ModulAPI api = adapter.create(ModulAPI.class);

        api.getDataWarga(
                new Callback<CDataWarga>()
                {
                    @Override
                    public void success(CDataWarga cdatawarga, Response response) {
                        lsdatawarga = new ArrayList<>();

                        lsdatawarga = cdatawarga.getDataWarga();
                        final String[] idWarga = new String[lsdatawarga.size()];
                        final String[] namaWarga = new String[lsdatawarga.size()];
                        final String[] emailWarga = new String[lsdatawarga.size()];

                        for (int i = 0; i < lsdatawarga.size(); i++) {
                            idWarga[i] = lsdatawarga.get(i).getid_warga();
                            namaWarga[i] = lsdatawarga.get(i).getnama();
                            emailWarga[i] = lsdatawarga.get(i).getemail();

                        }
                        ArrayAdapter adapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, namaWarga);
                        spTo.setAdapter(adapter);

                        int setSelect = 0;

                        spTo.setSelection(setSelect);

                        spTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                data.put("id_pengirim", idWarga[position].toString());
                            }

                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        final String merror = error.getMessage();

                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(context, merror + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

        );


    }
}
