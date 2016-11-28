package com.adfin.numobile.activity.warga;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
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
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FormAnggota4Activity extends AppCompatActivity {

    public static final String ROOT_URL = "http://numobile.id/";
    EditText etIdWarga;
    Button btnsave;
    ImageView photo;

    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    String strsubject, strkalimat, strphoto = null;
    static ProgressDialog progressDialog = null;

    String  strIdWarga;
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



    String pathImage = null;

    final Context context = this;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_PICK_IMAGE_REQUEST_CODE = 200;
    private static boolean IS_GRANTED = false;
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

        ActivityCompat.requestPermissions(FormAnggota4Activity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                CAMERA_PICK_IMAGE_REQUEST_CODE);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        setContentView(R.layout.activity_form_anggota4);

        photo = (ImageView)findViewById(R.id.gambar);

        etIdWarga = (EditText) findViewById(R.id.idWarga);
        strIdWarga = globalVariable.getId();
        etIdWarga.setText(strIdWarga);

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
                if (pathImage == null) pathImage = "false";
                if( pathImage.length() < 5 || pathImage.equals("false") || pathImage.equals(null) ) {
                    showAlert("Anda belum memilih image");
                }else{
                    progressDialog = ProgressDialog.show(FormAnggota4Activity.this, "", "Please wait...", true);
                    new Thread(new Runnable() {
                        public void run() {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(FormAnggota4Activity.this, "Memulai Upload", Toast.LENGTH_LONG).show();
                                }
                            });
                            uploadFileToServer(pathImage, "http://www.terpusat.com/NUMobile/tambahanggota.php", strIdWarga);
                        }
                    }).start();
                }
            }
        });
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
                    if( IS_GRANTED )
                        browseImage();
                    else
                        showAlert("Tidak diizinkan memilih dari galeri");
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
        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 300;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);

        photo.setImageBitmap(bm);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File imgFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "NUMobile");
        imgFolder.mkdirs();

        File image = new File(imgFolder, "img_nu_" + timeStamp + ".jpg");

        FileOutputStream fo;
        try {
            image.createNewFile();
            fo = new FileOutputStream(image);
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, fo);
            fo.write(bytes.toByteArray());
            fo.close();
            pathImage = image.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        photo.setImageBitmap(thumbnail);
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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PICK_IMAGE_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    IS_GRANTED = true;
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    IS_GRANTED = false;
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
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

    public String uploadFileToServer(String filename, String targetUrl, String id_warga) {
        String response = "error";
        FileInputStream fileInputStream = null;

        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024;

        try {
            if(filename != "false")
                fileInputStream = new FileInputStream(new File(filename));

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Allow Inputs & Outputs
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setChunkedStreamingMode(1024);
            // Enable POST method
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + boundary);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);

            ArrayList<String> keyForm = new ArrayList<String>();
            ArrayList<String> dataForm = new ArrayList<String>();
            keyForm.add("id_warga");
            dataForm.add(String.valueOf(id_warga));
            keyForm.add("token");
            dataForm.add("form4");
            for (int i = 0; i < keyForm.size(); i++) {
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" + keyForm.get(i) + "\"" + lineEnd);
                outputStream.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
                outputStream.writeBytes("Content-Length: " + dataForm.get(i).length() + lineEnd);
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(dataForm.get(i) + lineEnd);
                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            }

            if (fileInputStream != null) {
                String connstr;
                connstr = "Content-Disposition: form-data; name=\"UploadFile\";filename=\""
                        + filename + "\"" + lineEnd;

                outputStream.writeBytes(connstr);
                outputStream.writeBytes(lineEnd);

                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // Read file
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                System.out.println("Image length " + bytesAvailable + "");
                try {
                    while (bytesRead > 0) {
                        try {
                            outputStream.write(buffer, 0, bufferSize);
                        } catch (OutOfMemoryError e) {
                            e.printStackTrace();
                            response = "outofmemoryerror";
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(FormAnggota4Activity.this, "File yang anda masukkan terlalu besar.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                            return response;
                        }
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response = "error";
                    return response;
                }
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(twoHyphens + boundary + twoHyphens
                        + lineEnd);
            }
            // Responses from the server (code and message)
            int serverResponseCode = connection.getResponseCode();

            if (serverResponseCode == 200){
                response = "true";
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(FormAnggota4Activity.this, "File sukses diupload.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                response = "false";
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(FormAnggota4Activity.this, "File gagal diupload. Silahkan hubungi administrator.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            fileInputStream.close(); outputStream.flush();

            connection.getInputStream(); java.io.InputStream is = connection.getInputStream();

            int ch;
            StringBuffer b = new StringBuffer();
            while( ( ch = is.read() ) != -1 ){
                b.append( (char)ch );
            }


            outputStream.close();
            outputStream = null;

        } catch (Exception ex) {
            // Exception handling
            response = "error";
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(FormAnggota4Activity.this, "Terjadi gangguan koneksi",
                            Toast.LENGTH_SHORT).show();
                }
            });
            ex.printStackTrace();
            Log.e("Ini Lhoh Error ", ex.getMessage());
        }
        progressDialog.dismiss();

        if( response.equals("true") ) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Context context = FormAnggota4Activity.this;
                    Intent intent = null;
                    intent = new Intent(context, AnggotaLihatActivity.class);
                    (context).startActivity(intent);
                }
            });
        }

        return response;
    }
}
