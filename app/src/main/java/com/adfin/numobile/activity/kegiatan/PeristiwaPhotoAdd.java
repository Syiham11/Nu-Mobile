package com.adfin.numobile.activity.kegiatan;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adfin.numobile.R;
import com.adfin.numobile.helper.GPSTracker;
import com.adfin.numobile.helper.Uploader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class PeristiwaPhotoAdd extends AppCompatActivity {

    ImageView imageView;
    EditText deskripsi;
    Button btnsave;
    TextView textError, textLocation;
    ProgressBar progressBar;

    String pathImage = "";
    Bitmap encImg;
    private double latit, longit = 0;
    final Context context = this;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_PICK_IMAGE_REQUEST_CODE = 200;
    private static final String IMAGE_DIRECTORY_NAME = "NU";
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Uri fileUri;

    HashMap data = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peristiwa_photo_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        deskripsi = (EditText) findViewById(R.id.editDescription);
        textError = (TextView) findViewById(R.id.errorinfo);
        textLocation = (TextView) findViewById(R.id.locationinfo);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSpinner);
        textError.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        GPSTracker gps = new GPSTracker(this);

        latit = gps.getLatitude(); longit = gps.getLongitude();

        imageView = (ImageView) findViewById(R.id.gambar);

        btnsave = (Button) findViewById(R.id.buttonSave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (  ( !deskripsi.getText().toString().equals("")) && ( !pathImage.equals(""))) {
                    //progressBar.setVisibility(View.VISIBLE);

                    data.put("token", "token");
                    data.put("id_warga", "1");
                    data.put("deskripsi", deskripsi.getText().toString());
                    data.put("path", getStringImage(encImg));
                    data.put("latitude", String.valueOf(latit));
                    data.put("langitude", String.valueOf(longit));
                    data.put("type", "1");

                    Uploader.with(PeristiwaPhotoAdd.this)
                            .load("http://www.terpusat.com/NUMobile/simpanperistiwa.php")
                            .data(data)
                            .message("Mohon Tunggu ....")
                            .upload()
                            .finish();
                }else if (  ( deskripsi.getText().toString().equals("")) && ( !pathImage.equals(""))) {
                    textError.setVisibility(View.VISIBLE);
                    textError.setText("Masukan deskripsi kegiatan!");
                }else if (  ( !deskripsi.getText().toString().equals("")) && ( pathImage.equals(""))) {
                    textError.setVisibility(View.VISIBLE);
                    textError.setText("Masukan photo kegiatan!");
                }else{
                    textError.setVisibility(View.VISIBLE);
                    textError.setText("Masukan deskripsi kegiatan dan photo kegiatan!");
                }
            }
        });
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    private void browseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/jpg");
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
                encImg = BitmapFactory.decodeFile(selectedImagePath, options);
                imageView.setImageBitmap(encImg);
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
            imageView.setVisibility(View.VISIBLE);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            encImg = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);
            pathImage = fileUri.getPath();

            imageView.setImageBitmap(encImg);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

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

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(2, returnIntent);
        finish();
    }

}
