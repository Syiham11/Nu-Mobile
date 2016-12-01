package com.adfin.numobile.activity.warga;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.adfin.numobile.R;
import com.adfin.numobile.activity.GlobalClass;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class FormAnggota4Activity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    //public static final String ROOT_URL = "http://numobile.id/";
    EditText etIdWarga;
    Button btnsave;
    ImageView photo;

    static ProgressDialog progressDialog;

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

    static String pathImage;

    final Context context = this;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_PICK_IMAGE_REQUEST_CODE = 200;
    private static final int RC_SETTINGS_SCREEN = 125;
    private static boolean IS_GRANTED = false;
    private static final String IMAGE_DIRECTORY_NAME = "NU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if( !IS_GRANTED )
            permissionExternal();

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        if( globalVariable.getId() == null && globalVariable.getId().isEmpty() && globalVariable.getId().equals("null") ){
            Context context = FormAnggota4Activity.this;
            Intent intent = new Intent(context, AnggotaLihatActivity.class);
            (context).startActivity(intent);
            finish();
        }

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
                if ((pathImage.length() < 5)) showAlert("Anda belum memilih image");
                else if (pathImage.equals("null")) showAlert("Anda belum memilih image");
                else {
                    progressDialog = ProgressDialog.show(FormAnggota4Activity.this, "", "Please wait...", true);
                    new Thread(new Runnable() {
                        public void run() {
                            uploadFileToServer("http://www.terpusat.com/NUMobile/tambahanggota.php", strIdWarga);
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
        Bitmap thumbnail; ByteArrayOutputStream bytes;
        thumbnail = (Bitmap) data.getExtras().get("data");
        bytes = new ByteArrayOutputStream();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File imgFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), IMAGE_DIRECTORY_NAME);
        //noinspection ResultOfMethodCallIgnored
        imgFolder.mkdirs();

        File image = new File(imgFolder, "img_nu_" + timeStamp + ".jpg");

        FileOutputStream fo;
        try {
            //noinspection ResultOfMethodCallIgnored
            image.createNewFile();
            fo = new FileOutputStream(image);
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 20, fo);
            fo.write(bytes.toByteArray());
            fo.close();
            pathImage = image.getAbsolutePath();
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

    @AfterPermissionGranted(CAMERA_PICK_IMAGE_REQUEST_CODE)
    private void permissionExternal() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            IS_GRANTED = true;
        } else {
            EasyPermissions.requestPermissions(this, "This app needs access to your camera so you can take pictures.",
                    CAMERA_PICK_IMAGE_REQUEST_CODE, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
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

    public String uploadFileToServer(String targetUrl, String id_warga) {
        String response = "error";
        FileInputStream fileInputStream;

        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1024;

        try {
            Log.e("File Isi", pathImage);
            if(!pathImage.equals("null")) fileInputStream = new FileInputStream(new File(pathImage));
            else{
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(FormAnggota4Activity.this, "File bermasalah",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.dismiss();

                return response;
            }

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

            ArrayList<String> keyForm = new ArrayList<>();
            ArrayList<String> dataForm = new ArrayList<>();
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
                        + pathImage + "\"" + lineEnd;

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

                        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

                        globalVariable.setId(null);
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

            if(fileInputStream != null) fileInputStream.close();
            outputStream.flush();

            connection.getInputStream(); java.io.InputStream is = connection.getInputStream();

            int ch;
            StringBuilder b = new StringBuilder();
            while(-1 != (ch = is.read())){
                b.append( (char)ch );
            }


            outputStream.close();

        } catch (Exception ex) {
            // Exception handling
            response = "error";
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(FormAnggota4Activity.this, "Terjadi kesalahan koneksi",
                            Toast.LENGTH_SHORT).show();
                }
            });
            ex.printStackTrace();
        }
        progressDialog.dismiss();

        if( response.equals("true") ) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Context context = FormAnggota4Activity.this;
                    Intent intent = new Intent(context, AnggotaLihatActivity.class);
                    (context).startActivity(intent);
                    finish();
                }
            });
        }

        return response;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        IS_GRANTED = true;
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        IS_GRANTED = false;

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "This app may not work correctly without the requested permissions. Open the app settings screen to modify app permissions.")
                    .setTitle("Permissions Required")
                    .setPositiveButton("Settings")
                    .setNegativeButton("Settings dialog canceled", null /* click listener */)
                    .setRequestCode(RC_SETTINGS_SCREEN)
                    .build()
                    .show();
        }
    }
}
