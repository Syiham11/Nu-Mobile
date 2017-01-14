package com.adfin.numobile.helper;
// Created by prakasa on 14/01/17.

import com.adfin.numobile.module.warga.model.Warga;

import retrofit.Callback;
import retrofit.http.GET;

public interface Apis {

    @GET("/NUMobile/getusers.php")
    void get(Callback<Warga.CallbackWarga> callback);

}
