package com.adfin.numobile.persistence;

import android.content.Context;
import android.util.Log;

/**
 * Created by Siti on 9/15/2016.
 */
public class DBAdapter {

    /******************** if debug is set true then it will show all Logcat message ************/
    public static final boolean DEBUG = true;

    /******************** Logcat TAG ************/
    public static final String LOG_TAG = "DBAdapter";

    /******************** Table Fields ************/
    //PROVINSI//
    public static final String KEY_ID_PROVINSI = "_id_provinsi";
    public static final String KEY_KODE_PROVINSI = "prov_kode_provinsi";
    public static final String KEY_NAMA_PROVINSI = "prov_nama_provinsi";

    //KABKOT//
    public static final String KEY_ID_KABKOT = "_id_kabkot";
    public static final String KEY_KAB_KODE_PROVINSI = "kab_kode_provinsi";
    public static final String KEY_KAB_KODE_KABKOT = "kab_kode_kabkot";
    public static final String KEY_KAB_NAMA_KABKOT = "kab_nama_kabkot";

    //KECAMATAN//
    public static final String KEY_ID_KECAMATAN = "_id_kecamatan";
    public static final String KEY_KEC_KODE_PROVINSI = "kec_kode_provinsi";
    public static final String KEY_KEC_KODE_KABKOT = "kec_kode_kabkot";
    public static final String KEY_KEC_KODE_KECAMATAN = "kec_kode_kecamatan";
    public static final String KEY_KEC_NAMA_KECAMATAN = "kec_nama_kecamatan";

    //DESA//
    public static final String KEY_ID_DESA = "_id_desa";
    public static final String KEY_DES_KODE_PROVINSI = "des_kode_provinsi";
    public static final String KEY_DES_KODE_KABKOT = "des_kode_kabkot";
    public static final String KEY_DES_KODE_KECAMATAN = "des_kode_kecamatan";
    public static final String KEY_DES_KODE_DESA = "des_kode_desa";
    public static final String KEY_DES_NAMA_DESA = "des_nama_desa";

    //ANGGOTA//
    public static final String KEY_ID_ANGGOTA = "_id_anggota";
    public static final String KEY_DAFTAR_USERNAME = "daftar_username";
    public static final String KEY_DAFTAR_NAMA = "daftar_nama";//ini field
    public static final String KEY_DAFTAR_NOKTP = "daftar_noktp";
    public static final String KEY_DAFTAR_TEMPAT_LAHIR = "daftar_tempat_lahir";
    public static final String KEY_DAFTAR_TANGGAL_LAHIR = "daftar_tanggal_lahir";
    public static final String KEY_DAFTAR_JENIS_KELAMIN = "daftar_jenis_kelamin";
    public static final String KEY_DAFTAR_STATUS_PERKAWINAN = "daftar_status_perkawinan";
    public static final String KEY_DAFTAR_ALAMAT = "daftar_alamat";
    public static final String KEY_DAFTAR_PROVINSI = "daftar_provinsi";
    public static final String KEY_DAFTAR_KABKOT = "daftar_kabkot";
    public static final String KEY_DAFTAR_KECAMATAN = "daftar_kecamatan";
    public static final String KEY_DAFTAR_DESA = "daftar_desa";
    public static final String KEY_DAFTAR_KODE_POS = "daftar_kode_pos";
    public static final String KEY_DAFTAR_TLP = "daftar_tlp";
    public static final String KEY_DAFTAR_HP = "daftar_hp";
    public static final String KEY_DAFTAR_EMAIL = "daftar_email";
    public static final String KEY_DAFTAR_FB = "daftar_fb";
    public static final String KEY_DAFTAR_TWITTER = "daftar_twitter";
    public static final String KEY_DAFTAR_PEKERJAAN = "daftar_pekerjaan";
    public static final String KEY_DAFTAR_JABATAN = "daftar_jabatan";
    public static final String KEY_DAFTAR_PENDAPATAN = "daftar_pendapatan";
    public static final String KEY_DAFTAR_ORGANISASI = "daftar_organisasi";
    public static final String KEY_DAFTAR_SD = "daftar_sd";
    public static final String KEY_DAFTAR_SMP = "daftar_smp";
    public static final String KEY_DAFTAR_SMA = "daftar_sma";
    public static final String KEY_DAFTAR_S1 = "daftar_s1";
    public static final String KEY_DAFTAR_S2 = "daftar_s2";
    public static final String KEY_DAFTAR_S3 = "daftar_s3";
    public static final String KEY_DAFTAR_PESANTREN = "daftar_pesantren";
    public static final String KEY_DAFTAR_LAMA_PESANTREN = "daftar_lama_pesantren";
    public static final String KEY_DAFTAR_NAMA_PESANTREN = "daftar_nama_pesantren";
    public static final String KEY_DAFTAR_ALAMAT_PESANTREN = "daftar_alamat_pesantren";
    public static final String KEY_DAFTAR_PROVINSI_PESANTREN = "daftar_provinsi_pesantren";
    public static final String KEY_DAFTAR_KABKOT_PESANTREN = "daftar_kabkot_pesantren";
    public static final String KEY_DAFTAR_KECAMATAN_PESANTREN = "daftar_kecamatan_pesantren";
    public static final String KEY_DAFTAR_DESA_PESANTREN = "daftar_desa_pesantren";
    public static final String KEY_DAFTAR_PHOTO = "daftar_photo";


    /******************** Database Name ************/
    public static final String DATABASE_NAME = "DB_sqllite";

    /******************** Database Version (Increase one if want to also upgrade your database) ************/
    public static final int DATABASE_VERSION = 1;// started at 1

    //Table ANGGOTA //
    public static final String USER_TABLE = "tbl_anggota";

    /******************** Set all table with comma seperated like USER_TABLE,ABC_TABLE ************/
    private static final String[] ALL_TABLES = { USER_TABLE };

    /** Create table syntax */
    private static final String USER_CREATE = "create table tbl_anggota(_id_anggota integer primary key autoincrement, daftar_username text not null, daftar_nama text not null, daftar_noktp text not null,daftar_tempat_lahir text not null," +
            "daftar_tanggal_lahir text not null,daftar_jenis_kelamin text not null,daftar_status_perkawinan text not null,daftar_alamat text not null,daftar_provinsi text not null,daftar_kabkot text not null,daftar_kecamatan text not null,daftar_desa text not null," +
            "daftar_kode_pos text not null,daftar_tlp text not null,daftar_hp text not null,daftar_email text not null,daftar_fb text not null,daftar_twitter text not null,daftar_pekerjaan text not null," +
            "daftar_jabatan text not null,daftar_pendapatan text not null,daftar_organisasi text not null,daftar_sd text not null,daftar_smp text not null,daftar_sma text not null," +
            "daftar_s1 text not null,daftar_s2 text not null,daftar_s3 text not null,daftar_pesantren text not null,daftar_lama_pesantren text not null,daftar_nama_pesantren text not null,daftar_alamat_pesantren text not null," +
            "daftar_provinsi_pesantren text not null,daftar_kabkot_pesantren text not null,daftar_kecamatan_pesantren text not null,daftar_desa_pesantren text not null,daftar_photo text not null);";


    //Table PROVINSI //
    public static final String PROVINSI_TABLE = "tbl_provinsi";
    /******************** Set all table with comma seperated like PROVINSI_TABLE,ABC_TABLE ************/
    private static final String[] ALL_TABLES_PROVINSI = { PROVINSI_TABLE };
    /** Create table syntax */
    private static final String PROVINSI_CREATE = "create table tbl_provinsi(_id_provinsi integer primary key autoincrement, prov_kode_provinsi text not null, prov_nama_provinsi text not null);";


    //Table KABKOT //
    public static final String KABKOT_TABLE = "tbl_kabkot";
    /******************** Set all table with comma seperated like KABKOT_TABLE,ABC_TABLE ************/
    private static final String[] ALL_TABLES_KABKOT = { KABKOT_TABLE };
    /** Create table syntax */
    private static final String KABKOT_CREATE = "create table tbl_kabkot(_id_kabkot integer primary key autoincrement, kab_kode_provinsi text not null, kab_kode_kabkot text not null, kab_nama_kabkot text not null);";


    //Table KECAMATAN //
    public static final String KECAMATAN_TABLE = "tbl_kecamatan";
    /******************** Set all table with comma seperated like KECAMATAN_TABLE,ABC_TABLE ************/
    private static final String[] ALL_TABLES_KECAMATAN = { KECAMATAN_TABLE };
    /** Create table syntax */
    private static final String KECAMATAN_CREATE = "create table tbl_kecamatan(_id_kecamatan integer primary key autoincrement, kec_kode_provinsi text not null, kec_kode_kabkot text not null, kec_kode_kecamatan text not null, kec_nama_kecamatan text not null);";


    //Table DESA //
    public static final String DESA_TABLE = "tbl_desa";
    /******************** Set all table with comma seperated like DESA_TABLE,ABC_TABLE ************/
    private static final String[] ALL_TABLES_DESA = { DESA_TABLE };
    /** Create table syntax */
    private static final String DESA_CREATE = "create table tbl_desa(_id_desa integer primary key autoincrement, des_kode_provinsi text not null, des_kode_kabkot text not null, des_kode_kecamatan text not null, des_kode_desa text not null, des_nama_desa text not null);";



}
