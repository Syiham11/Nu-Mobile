package com.adfin.numobile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.adfin.numobile.helper.Apis;
import com.adfin.numobile.module.warga.model.Warga;

public abstract class App extends AppCompatActivity {

    protected static Context mContext;

    protected static final String BASEURL = "http://www.terpusat.com";

    protected void setContext(Context context) {
        mContext = context;
    }

    protected Apis api() {
        return Warga.with(mContext).init(BASEURL).create(Apis.class);
    }
}
