package com.adfin.numobile;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by aldieemaulana on 10/01/2016
 */
public class App extends Application {
    private static final String CANARO_EXTRA_BOLD_PATH = "fonts/canaro_extra_bold.otf";
    private static final String RALEWAY_REGULAR_PATH = "fonts/ralewayregular.ttf";
    public static Typeface canaroExtraBold;

    private static final String CANARO_LIGHT_PATH = "fonts/canaro_light.otf";
    public static Typeface canaroLight;
    public static String API = "http://kkntematik-ciptakarya.com/api/api";
    public static String URL = "http://kkntematik-ciptakarya.com/api";
    public static String URI = "http://kkntematik-ciptakarya.com";

    public static final String EXTRA_DATE = "com.artonano.kkntematik.date";
    public static final String EXTRA_URL = "com.artonano.kkntematik.url";
    public static final String EXTRA_DES = "com.artonano.kkntematik.description";
    private static final String RALEWAY_LIGHT_PATH = "fonts/ralewaylight.ttf";

    public static Typeface ralewayRegular;
    public static Typeface ralewayLight;

    @Override
    public void onCreate() {
        super.onCreate();
        initTypeface();
    }

    private void initTypeface() {
        canaroExtraBold = Typeface.createFromAsset(getAssets(), CANARO_EXTRA_BOLD_PATH);
        canaroLight = Typeface.createFromAsset(getAssets(), CANARO_LIGHT_PATH);
        ralewayRegular = Typeface.createFromAsset(getAssets(), RALEWAY_REGULAR_PATH);
        ralewayLight = Typeface.createFromAsset(getAssets(), RALEWAY_LIGHT_PATH);

    }

    public static String encodeTobase64(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }


    public static Bitmap decodeBase64(String b64)
    {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }
}
