package com.adfin.numobile.modelSQLite;

/**
 * Created by Siti on 9/28/2016.
 */
public class KabkotData {
    int _id_kabkot, _kode_provinsi, _kode_kabkot;
    String _nama_kabkot;

    public KabkotData(){

    }

    public  KabkotData(int id_kabkot, int kode_provinsi, int kode_kabkot, String nama_kabkot){
        this._id_kabkot = id_kabkot;
        this._kode_provinsi = kode_provinsi;
        this._kode_kabkot = kode_kabkot;
        this._nama_kabkot = nama_kabkot;
    }

    public  KabkotData(int kode_provinsi, int kode_kabkot, String nama_kabkot){
        this._kode_provinsi = kode_provinsi;
        this._kode_kabkot = kode_kabkot;
        this._nama_kabkot = nama_kabkot;
    }

    public int getId_Kabkot(){
        return this._kode_kabkot;
    }
    public void setId_Kabkot(int id_kabkot){
        this._id_kabkot = id_kabkot;
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

    public String getNama_Kabkot(){
        return this._nama_kabkot;
    }
    public void setNama_Kabkot(String nama_kabkot){
        this._nama_kabkot = nama_kabkot;
    }

    @Override
    public String toString() {
        return "UserInfo [ID Kabkot = "+_id_kabkot+", Kode Provinsi = "+_kode_provinsi+", Kode Kabkot = "+_kode_kabkot+", Nama Kabkot = "+_nama_kabkot+"]";

    }
}
