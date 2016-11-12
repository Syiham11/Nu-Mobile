package com.adfin.numobile.modelSQLite;

/**
 * Created by Siti on 9/28/2016.
 */
public class KecamatanData {
    int _id_kecamatan, _kode_provinsi, _kode_kabkot, _kode_kecamatan;
    String _nama_kecamatan;

    public  KecamatanData(){

    }

    public  KecamatanData ( int id_kecamatan, int kode_provinsi, int kode_kabkot, int kode_kecamatan, String nama_kecamatan){
        this._id_kecamatan = id_kecamatan;
        this._kode_provinsi = kode_provinsi;
        this._kode_kabkot = kode_kabkot;
        this._kode_kecamatan = kode_kecamatan;
        this._nama_kecamatan = nama_kecamatan;
    }

    public  KecamatanData ( int kode_provinsi, int kode_kabkot, int kode_kecamatan, String nama_kecamatan){
        this._kode_provinsi = kode_provinsi;
        this._kode_kabkot = kode_kabkot;
        this._kode_kecamatan = kode_kecamatan;
        this._nama_kecamatan = nama_kecamatan;
    }

    public int getId_Kecamatan(){
        return this._id_kecamatan;
    }
    public void setId_Kecamatan(int id_kecamatan){
        this._id_kecamatan = id_kecamatan;
    }

    public int getKode_Provinsi(){
        return this._kode_provinsi;
    }
    public void setKode_Provinsi(int kode_provinsi){
        this._kode_provinsi = kode_provinsi;
    }

    public int getKode_Kabkot(){
        return this._kode_kabkot;
    }
    public void setKode_Kabkot(int kode_kabkot){
        this._kode_kabkot = kode_kabkot;
    }

    public int getKode_Kecamatan(){
        return this._kode_kecamatan;
    }
    public void setKode_Kecamatan(int kode_kecamatan){
        this._kode_kecamatan = kode_kecamatan;
    }

    public String getNama_Kecamatan(){
        return this._nama_kecamatan;
    }
    public void setNama_Kecamatan(String nama_kecamatan){
        this._nama_kecamatan = nama_kecamatan;
    }

    @Override
    public String toString() {
        return "UserInfo [ID Kecamatan = "+_id_kecamatan+", Kode Provinsi = "+_kode_provinsi+", Kode Kabkot = "+_kode_kabkot+", Kpde Kecamatan = "+_kode_kecamatan+"," + "Nama Kecamatn = "+_nama_kecamatan+"]";

    }
}
