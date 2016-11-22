package com.adfin.numobile;

import com.adfin.numobile.model.CDataDesa;
import com.adfin.numobile.model.CDataIndukOrganisasi;
import com.adfin.numobile.model.CDataInstansi;
import com.adfin.numobile.model.CDataJenisPekerjaan;
import com.adfin.numobile.model.CDataKabupten;
import com.adfin.numobile.model.CDataKecamatan;
import com.adfin.numobile.model.CDataPekerjaan;
import com.adfin.numobile.model.CDataPendapatan;
import com.adfin.numobile.model.CDataPesantren;
import com.adfin.numobile.model.CDataProvinsi;
import com.adfin.numobile.model.CDataWarga;
import com.adfin.numobile.model.DataNoTerkahir;
import com.adfin.numobile.model.DataWarga;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Siti on 9/9/2016.
 */
public interface ModulAPI {


    @GET("/NUMobile/ceksudahdaftar.php") // Belum
    public void ceksudahdaftar(
            @Query("vsusername") String vsusername,
            @Query("vspassword") String vspassword,
            Callback<DataWarga> callback);

    @GET("/NUMobile/cekusername.php") // belum
    public void cekusername(
            @Query("vsusername") String vsusername,
            Callback<DataWarga> callback);

    @GET("/NUMobile/ambilid_warga.php") // belum
    public void  getDataIdWarga(Callback<DataNoTerkahir> callback);

    @GET("/NUMobile/getusers.php") // Sudah
    public void  getDataWarga(Callback<CDataWarga> callback);

    @GET("/NUMobile/ambildataprovinsi.php") // udah
    public void  getDataProvinsi(Callback<CDataProvinsi> callback);

    @GET("/NUMobile/ambildatakabupaten.php") // udah
    public void  getDataKabupaten(
            @Query("vskodeprovinsi") String vskodeprovinsi,
            Callback<CDataKabupten> callback);

    @GET("/NUMobile/ambildatakecamatan.php") // udah
    public void  getDataKecamatan(
            @Query("vskodeprovinsi") String vskodeprovinsi,
            @Query("vskodekabkot") String vskodekabkot,
            Callback<CDataKecamatan> callback);

    @GET("/NUMobile/ambildatadesa.php") // udah
    public void  getDataDesa(
            @Query("vskodeprovinsi") String vskodeprovinsi,
            @Query("vskodekabkot") String vskodekabkot,
            @Query("vskodekecamatan") String vskodekecamatan,
            Callback<CDataDesa> callback);

    @GET("/NUMobile/ambildataspinnerjenispekerjaan.php") // Udah
    public void  getDataJenisPekerjaan(Callback<CDataJenisPekerjaan> callback);

    @GET("/NUMobile/ambildataspinnerinstansi.php") // Udah
    public void  getDataInstansi(Callback<CDataInstansi> callback);

    @GET("/NUMobile/ambildataspinnerpendapatan.php") // Udah
    public void  getDataPendapatan(Callback<CDataPendapatan> callback);

    @GET("/NUMobile/ambildataspinnerindukorganisasi.php") // Udah
    public void  getDataIndukOrganisasi(Callback<CDataIndukOrganisasi> callback);

    @GET("/NUMobile/ambildataspinnerpesantren.php") // Udah
    public void  getDataPesantren(Callback<CDataPesantren> callback);

    @FormUrlEncoded
    @POST("/NUMobile/tambahanggota.php")
    public void insertdataanggota(
            @Field("token") String token,
            @Field("vsusername") String vsusername,
            @Field("vsnama") String vsnama,
            @Field("vsnoktp") String vsnoktp,
            @Field("vstempat_lahir") String vstempat_lahir,
            @Field("vstanggal_lahir") String vstanggal_lahir,
            @Field("vsjenis_kelamin") String vsjenis_kelamin,
            @Field("vsstatus_perkawinan") String vsstatus_perkawinan,
            @Field("vsalamat") String vsalamat,
            @Field("vsprovinsi") String vsprovinsi,
            @Field("vskabkot") String vskabkot,//10
            @Field("vskecamatan") String vskecamatan,
            @Field("vsdesa") String vsdesa,
            @Field("vskode_pos") String vskode_pos,
            @Field("vstlp") String vstlp,
            @Field("vshp") String vshp,
            @Field("vsemail") String vsemail,
            @Field("vsfb") String vsfb,
            @Field("vstwitter") String vstwitter,
            @Field("vsinstagram") String vsinstagram,
            @Field("vspath") String vspathh,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/NUMobile/tambahanggota.php")//jumlah dan susunan antara yg ini harus sama dengan yang di php
    public void insertdataanggotal(
            @Field("token") String token,
            @Field("id_warga") String vid_warga,
            @Field("vsid_jenis_pekerjaan") String vsnama_pekerjaan,
            @Field("belomkepakegajelascom_id_instansi") String vsid_instansi,
            @Field("vsjabatan") String vsjabatan,
            @Field("vsid_pendapatan") String vsid_pendapatan,
            @Field("vskemampuan") String vskemampuan,
            @Field("vsid_induk_organisasi") String vsid_induk_organisasi,
            @Field("xssekolah") String xssekolah,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/NUMobile/tambahanggota.php")//jumlah dan susunan antara yg ini harus sama dengan yang di php
    public void insertdataanggota2(
            @Field("token") String token,
            @Field("id_warga") String vid_warga,
            @Field("vsya_tidak_pesantren") String vsya_tidak_pesantren,
            @Field("vslama_pesantren") String vslama_pesantren,
            @Field("vsid_pesantren1") String vsid_pesantren1,
            @Field("vsid_pesantren2") String vsid_pesantren2,
            @Field("vsinfak_organisasi") String vsinfak_organisasi,
            @Field("vsdonasi_organisasi") String vsdonasi_organisasi,
            @Field("vsnominal_organisasi") String vsnominal_organisasi,
            @Field("vsinfak_warga") String vsinfak_warga,
            @Field("vsdonasi_warga") String vsdonasi_warga,
            @Field("vsnominal_warga") String vsnominal_warga,
            @Field("vsstatus_keanggotaan") String vsstatus_keanggotaan,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/NUMobile/simpanLatLong.php") // udah
    public void simpanLatLong(
            @Field("vsusername") String vsusername,
            @Field("vslatitude") String vslatitude,
            @Field("vslongtitude") String vslongtitude,
            Callback<Response> callback);



}


