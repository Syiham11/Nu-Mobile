package com.adfin.numobile.activity.kegiatan;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.adfin.numobile.activity.Config;
import com.adfin.numobile.activity.UploadActivity;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PeristiwaLaporan extends AppCompatActivity {

    ImageView imageView;
    EditText deskripsi;
    TextView textError, textLocation;
    ProgressBar progressBar;

    String pathImage = "";

    protected String mLatitudeLabel;
    protected String mLongitudeLabel;

    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    final Context context = this;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_PICK_IMAGE_REQUEST_CODE = 200;
    private static final String IMAGE_DIRECTORY_NAME = "NU Mobile";
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_PICK = 2;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peristiwa_laporan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        deskripsi = (EditText) findViewById(R.id.editDescription);
        textError = (TextView) findViewById(R.id.errorinfo);
        textLocation = (TextView) findViewById(R.id.locationinfo);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSpinner);
        textError.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        imageView = (ImageView) findViewById(R.id.gambar);

        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (  ( !deskripsi.getText().toString().equals("")) && ( !pathImage.equals(""))) {
                    progressBar.setVisibility(View.VISIBLE);
                    //saveData();
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

//    private void saveData(){
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 8;
//        final Bitmap bitmap = BitmapFactory.decodeFile(pathImage,
//                options);
//        //saveImage(bitmap);
//        String foto = App.encodeTobase64(bitmap);
//
//        String tanggal = new SimpleDateFormat("yyyy-MM-dd H:m:s").format(new Date());
//        Photo photo = new
//                Photo(null, String.valueOf(session.getId()), deskripsi.getText().toString(),
//                foto, mLatitudeLabel, mLongitudeLabel,
//                tanggal, "", "", "0");
//
//        handler.addPhoto(photo);
//
//        textError.setText("");
//        textError.setVisibility(View.GONE);
//        progressBar.setVisibility(View.GONE);
//        Intent returnIntent = new Intent();
//        setResult(1, returnIntent);
//        finish();
//
//    }

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
                    //browseImage();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // successfully captured the image
                // launching upload activity
                launchUploadActivity(true);


            } else if (resultCode == RESULT_CANCELED) {

                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();

            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }

    private void launchUploadActivity(boolean isImage){
        Intent i = new Intent(PeristiwaLaporan.this, UploadActivity.class);
        i.putExtra("filePath", fileUri.getPath());
        i.putExtra("isImage", isImage);
        startActivity(i);
    }


}
