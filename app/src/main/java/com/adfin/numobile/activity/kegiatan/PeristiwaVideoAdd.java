package com.adfin.numobile.activity.kegiatan;

import android.app.Dialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.adfin.numobile.R;
import com.adfin.numobile.helper.GPSTracker;
import com.adfin.numobile.helper.Uploader;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class PeristiwaVideoAdd extends AppCompatActivity {

    VideoView videoView;
    EditText deskripsi;
    TextView textError, textLocation;
    ProgressBar progressBar;

    String pathImage = "";
    static File file;
    private double latit, longit = 0;
    final Context context = this;


    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 100;
    private static final int CAMERA_PICK_IMAGE_REQUEST_CODE = 200;
    private static final String IMAGE_DIRECTORY_NAME = "NU";
    public static final int MEDIA_TYPE_VIDEO = 1;
    private Uri fileUri;

    HashMap data = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peristiwa_video_add);
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

        videoView = (VideoView) findViewById(R.id.gambar);

        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!deskripsi.getText().toString().equals("")) && (!pathImage.equals(""))) {
                    //progressBar.setVisibility(View.VISIBLE);

                    data.put("token", "token");
                    data.put("id_warga", "1");
                    data.put("deskripsi", deskripsi.getText().toString());
                    data.put("path", getStringImage());
                    data.put("latitude", String.valueOf(latit));
                    data.put("langitude", String.valueOf(longit));
                    data.put("type", "2");

                    Uploader.with(PeristiwaVideoAdd.this, PeristiwaVideo.class)
                            .load("http://www.terpusat.com/NUMobile/simpanperistiwa.php")
                            .data(data)
                            .message("Mohon Tunggu ....")
                            .upload()
                            .finish();

                } else if ((deskripsi.getText().toString().equals("")) && (!pathImage.equals(""))) {
                    textError.setVisibility(View.VISIBLE);
                    textError.setText("Masukan deskripsi kegiatan!");
                } else if ((!deskripsi.getText().toString().equals("")) && (pathImage.equals(""))) {
                    textError.setVisibility(View.VISIBLE);
                    textError.setText("Masukan video kegiatan!");
                } else {
                    textError.setVisibility(View.VISIBLE);
                    textError.setText("Masukan deskripsi kegiatan dan video kegiatan!");
                }
            }
        });
    }



    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);

        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 15);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 2000000);


        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }

    private void browseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("video/mp4");
        startActivityForResult(
                Intent.createChooser(intent, "Select File"),
                CAMERA_PICK_IMAGE_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                previewVideo();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
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
                file = new File(pathImage);
                videoView.setVideoPath(selectedImagePath);
                videoView.requestFocus();
                //videoView.start();
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

    private void previewVideo() {
        try {
            file = new File(fileUri.getPath());
            videoView.setVideoPath(fileUri.getPath());
            pathImage = fileUri.getPath();
            //videoView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /*
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        if (type == MEDIA_TYPE_VIDEO) {
            file = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return file;
    }

    private String getStringImage(){
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file.getAbsolutePath());
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);
            try {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    output64.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            output64.close();

            return output.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
            dialog.setContentView(R.layout.dialog_video);
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
                    recordVideo();
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
