package com.adfin.numobile.activity.warga;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.activity.AndroidMultiPartEntity;
import com.adfin.numobile.activity.Config;
import com.adfin.numobile.activity.GlobalClass;
import com.adfin.numobile.activity.UploadActivity;
import com.adfin.numobile.model.DataNoTerkahir;
import com.adfin.numobile.model.DataWarga;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FormAnggota4Activity extends AppCompatActivity {

    public static final String ROOT_URL = "http://numobile.id/";
    Button btnsave;
    ImageView photo;

    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    String strsubject, strkalimat, strphoto;

    String  strid_warga;
    String  strusername;
    String  strnama;
    String  strnoktp;
    String  strtempat_lahir;
    String  strtanggal_lahir;
    String  strjenis_kelamin;
    String  strstatus_perkawinan;
    String  stralamat;
    String  strprovinsi;
    String  strkabkot;
    String  strkecamatan;
    String  strdesa;
    String  strkode_pos;
    String  strtlp;
    String  strhp;
    String  stremail;
    String  strfb;
    String  strtwitter;
    String  strinstagram;
    String  strpathh;
    String  strpekerjaan;
    String  strinstansi;
    String  strjabatan;
    String  strpendapatan;
    String  strkemampuan;
    String  strorganisasi;
    String  strsd;
    String  strsmp;
    String  strsma;
    String  strd1;
    String  strd3;
    String  strs1;
    String  strs2;
    String  strs3;
    String  strYaTidakPesantren;
    String  strLamaPesantren;
    String  strPesantren1;
    String  strPesantren2;
    String  strInfaq;
    String  strJalur;
    String  strnominal_donasi;
    String  strInfaqWarga;
    String  strJalurWarga;
    String  strnominal_donasi_warga;
    String  strStatusMember;
    String  strLat;
    String  strLong;
    String  strFlag;



    String pathImage = "", nama;

    final Context context = this;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_PICK_IMAGE_REQUEST_CODE = 200;
    private static final String IMAGE_DIRECTORY_NAME = "NU";
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_PICK = 2;
    private Uri fileUri;
    private ProgressBar progressBar;
    private String filePath = null;
    long totalSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_anggota4);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        photo = (ImageView)findViewById(R.id.gambar);

        strnama = "";
        strdesa = "";
        strLat = "0";
        strLong = "0";
        strFlag = "0";


        strphoto = "";
        strphoto = strnama.toString()+strdesa.toString()+"'.png'";


        //FORM 1
        strusername = globalVariable.getUsername();
        strnama = globalVariable.getNamaLengkap();
        strnoktp = globalVariable.getNoKtp();
        strtempat_lahir = globalVariable.getTempatLahir();
        strtanggal_lahir = globalVariable.getTanggalLahir();
        strjenis_kelamin = globalVariable.getJenisKelamin();
        strstatus_perkawinan = globalVariable.getStatusPerkawinan();
        stralamat = globalVariable.getAlamat();
        strprovinsi = globalVariable.getProvinsi();
        strkabkot = globalVariable.getKabupaten();
        strkecamatan = globalVariable.getKecamatan();
        strdesa = globalVariable.getDesa();
        strkode_pos = globalVariable.getKodePos();
        strtlp = globalVariable.getTelepon();
        strhp = globalVariable.getHandphone();
        stremail = globalVariable.getEmail();
        strfb = globalVariable.getFacebook();
        strtwitter = globalVariable.getTwitter();
        strinstagram = globalVariable.getInstagram();
        strpathh = globalVariable.getPathh();

        //FORM 2
        strpekerjaan = globalVariable.getPekerjaan();
        strinstansi = globalVariable.getInstansi();
        strjabatan = globalVariable.getJabatan();
        strpendapatan = globalVariable.getPendapatan();
        strkemampuan = globalVariable.getKemampuan();
        strorganisasi = globalVariable.getIndukOrganisasi();
        strsd = globalVariable.getSD();
        strsmp = globalVariable.getSMP();
        strsma = globalVariable.getSMA();
        strd1 = globalVariable.getD1();
        strd3 = globalVariable.getD3();
        strs1 = globalVariable.getS1();
        strs2 = globalVariable.getS2();
        strs3 = globalVariable.getS3();

        //FORM 3
        strYaTidakPesantren = globalVariable.getYaTidakPesantren();
        strLamaPesantren = globalVariable.getLamaPesantren();
        strPesantren1 = globalVariable.getIdPesantren1();
        strPesantren2 = globalVariable.getIdPesantren2();
        strInfaq = globalVariable.getInfak();
        strJalur = globalVariable.getJalur();
        strnominal_donasi = globalVariable.getNominalDonasi();
        strInfaqWarga = globalVariable.getInfakWarga();
        strJalurWarga = globalVariable.getJalurWarga();
        strnominal_donasi_warga = globalVariable.getNominalDonasiWarga();
        strStatusMember = globalVariable.getIdStatusMember();

        btnsave = (Button) findViewById(R.id.buttonSave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                strusername = "usename";
//                strnama = "nama";
//                strnoktp = "no ktp";
//                strtempat_lahir = "tempat lahir";
//                strtanggal_lahir = "10/10/1992";
//                strjenis_kelamin = "jenis kelamin";
//                strstatus_perkawinan = "status perkawinan";
//                stralamat = "alamat";
//                strprovinsi = "provinsi";
//                strkabkot = "kabkot";
//                strkecamatan = "kecamata";
//                strdesa = "desa";
//                strkode_pos = "kode pos";
//                strtlp = "tlpn";
//                strhp = "hp";
//                stremail = "email";
//                strfb = "fb";
//                strtwitter = "twitter";
//                strinstagram = "instagram";
//                strpathh = "path";
//                strpekerjaan = "pekerjaan";
//                strinstansi = "instansi";
//                strjabatan = "jabatan";
//                strpendapatan = "pendapatan";
//                strkemampuan = "kemampuan";
//                strorganisasi = "organisasi";
//                strsd = "sd";
//                strsmp = "smp";
//                strsma = "sma";
//                strd1 = "d1";
//                strd3 = "d3";
//                strs1 = "s1";
//                strs2 = "s2";
//                strs3 = "s3";
//                strYaTidakPesantren = "ya tidak pesantren";
//                strLamaPesantren = "lama pesantren";
//                strPesantren1 = "pesantren 2";
//                strPesantren2 = "pesantren 1";
//                strInfaq = "infaq";
//                strJalur = "jalur";
//                strnominal_donasi = "nominal donasi";
//                strInfaqWarga = "infaq warga";
//                strJalurWarga = "jalur warga";
//                strnominal_donasi_warga = "donasi warga";
//                strStatusMember = "status member";

                ambildataidwarga();

            }
        });


    }

    private void ambildataidwarga(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.getDataIdWarga(
                new Callback<DataNoTerkahir>()

                {
                    @Override
                    public void success(DataNoTerkahir datanoterkhir, Response response) {

                        strid_warga = datanoterkhir.getid_warga();
                        saveDataInput();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(FormAnggota4Activity.this, "Kesalahan Koneksi Data" + toString(), Toast.LENGTH_LONG).show();

                    }
                }

        );


    }

    private void saveDataInput(){
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        //insertdataanggota ada di ModulAPI
        //urutan dan jumlah harus sama dengan yang di Model API
        /*api.insertdataanggotal(
                strusername.toString(),
                strnama.toString(),
                strnoktp.toString(),
                strtempat_lahir.toString(),
                strtanggal_lahir.toString(),
                strjenis_kelamin.toString(),
                strstatus_perkawinan.toString(),
                stralamat.toString(),
                strprovinsi.toString(),
                strkabkot.toString(),
                strkecamatan.toString(),
                strdesa.toString(),
                strkode_pos.toString(),
                strtlp.toString(),
                strhp.toString(),
                stremail.toString(),
                strfb.toString(),
                strtwitter.toString(),
                strinstagram.toString(),
                strpathh.toString(),
                strphoto,
                strkemampuan.toString(),
                strorganisasi.toString(),
                strStatusMember.toString(),
                strYaTidakPesantren.toString(),
                strLamaPesantren.toString(),
                strPesantren1.toString(),
                strPesantren2.toString(),
                strInfaq.toString(),
                strJalur.toString(),
                strnominal_donasi.toString(),
                strInfaqWarga.toString(),
                strJalurWarga.toString(),
                strnominal_donasi_warga.toString(),
                strLat.toString(),
                strLong.toString(),
                strFlag.toString(),
                strsd.toString(),
                strsmp.toString(),
                strsma.toString(),
                strd1.toString(),
                strd3.toString(),
                strs1.toString(),
                strs2.toString(),
                strs3.toString(),
                strpekerjaan.toString(),
                strinstansi.toString(),
                strjabatan.toString(),
                strpendapatan.toString(),

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;



                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //   btnsetting.setEnabled(true);

                        //save();

                        //untuk kirim email ke orang ngasih username
//                        strsubject = "contoh krim email";
//                        strkalimat = "username "+strusername+"\n";
//                        //Displaying the output as a toast
//                        new SendMailTask(AnggotaForm.this).execute("siti.sitisifa@gmail.com",
//                                "", stremail, strsubject, strkalimat);


//                        Intent intent = new Intent(getApplicationContext(), AnggotaLihatActivity.class);
//                        startActivity(intent);
//                        finish();


                        new UploadFileToServer().execute();


                        Toast.makeText(FormAnggota4Activity.this, "Data Berhasil Di Simpan", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(FormAnggota4Activity.this, "Kesalahan Koneksi Data" +error.getMessage(), Toast.LENGTH_LONG).show();
                        //btnsetting.setEnabled(true);
                    }
                }
        );*/
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
                    browseImage();
                    dialog.dismiss();
                }
            });

            Button captureButton = (Button) dialog.findViewById(R.id.buttonCapture);

            captureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    captureImage();
                    dialog.dismiss();
                }
            });

            dialog.show();
        }


        return super.onOptionsItemSelected(item);
    }



    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                Config.IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
//                Log.d(TAG, "Oops! Failed create "
//                        + Config.IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // if the result is capturing Image
//        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//
//                // successfully captured the image
//                // launching upload activity
//                launchUploadActivity(true);
//
//
//            } else if (resultCode == RESULT_CANCELED) {
//
//                // user cancelled Image capture
//                Toast.makeText(getApplicationContext(),
//                        "User cancelled image capture", Toast.LENGTH_SHORT)
//                        .show();
//
//            } else {
//                // failed to capture image
//                Toast.makeText(getApplicationContext(),
//                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
//                        .show();
//            }
//
//        }
//    }

    private void launchUploadActivity(boolean isImage){
        Intent i = new Intent(FormAnggota4Activity.this, UploadActivity.class);
        i.putExtra("filePath", fileUri.getPath());
        i.putExtra("isImage", isImage);
        startActivity(i);
    }

    private void browseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(
                Intent.createChooser(intent, "Select File"),
                CAMERA_PICK_IMAGE_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                previewCapturedImage();
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
                Uri selectedImageUri = data.getData();
                String[] projection = { MediaStore.MediaColumns.DATA };
                CursorLoader cursorLoader = new CursorLoader(this,selectedImageUri, projection, null, null,
                        null);
                Cursor cursor =cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                pathImage = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                photo.setImageBitmap(bm);
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

    private void previewCapturedImage() {
        try {
            photo.setVisibility(View.VISIBLE);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);
            pathImage = fileUri.getPath();

            photo.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void previewMedia(boolean isImage) {
        // Checking whether captured media is image or video
        if (isImage) {
            photo.setVisibility(View.VISIBLE);
            photo.setVisibility(View.GONE);
            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // down sizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

            photo.setImageBitmap(bitmap);
        } else {
            photo.setVisibility(View.GONE);
            photo.setVisibility(View.VISIBLE);
//            vidPreview.setVideoPath(filePath);
//            // start playing
//            vidPreview.start();
        }
    }

    /**
     * Uploading the file to server
     * */
    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            progressBar.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            // Making progress bar visible
            progressBar.setVisibility(View.VISIBLE);

            // updating progress bar value
            progressBar.setProgress(progress[0]);

            // updating percentage value
            //txtPercentage.setText(String.valueOf(progress[0]) + "%");
        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Config.FILE_UPLOAD_URL);

            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                File sourceFile = new File(filePath);

                // Adding file data to http body
                entity.addPart("image", new FileBody(sourceFile));

                // Extra parameters if you want to pass to server
                entity.addPart("website",
                        new StringBody("www.androidhive.info"));
                entity.addPart("email", new StringBody("abc@gmail.com"));

                totalSize = entity.getContentLength();
                httppost.setEntity(entity);

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            //Log.e(TAG, "Response from server: " + result);

            // showing the server response in an alert dialog
            showAlert(result);

            Intent intent = new Intent(FormAnggota4Activity.this, AnggotaLihatActivity.class);
            startActivity(intent);



            super.onPostExecute(result);
        }

    }

    /**
     * Method to show alert dialog
     * */
    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setTitle("Response from Servers")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // do nothing
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
