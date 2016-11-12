package com.adfin.numobile.modelSQLite;

/**
 * Created by Siti on 9/28/2016.
 */
public class ProvinsiData {
    int _id_provinsi, _kode_provinsi;
    String _nama_provinsi;

    //construktor kosong
    public ProvinsiData()
    {

    }

    //constructor dengan id
    public ProvinsiData(int id_provinsi, int kode_provinsi, String nama_provinsi){
        this._id_provinsi = id_provinsi;
        this._kode_provinsi = kode_provinsi;
        this._nama_provinsi = nama_provinsi;
    }

    //constructor dengan tanpa id
    public ProvinsiData(int kode_provinsi, String nama_provinsi){
        this._kode_provinsi = kode_provinsi;
        this._nama_provinsi = nama_provinsi;
    }

    public int getId_Provinsi(){
        return this._id_provinsi;
    }
    public void setId_Provinsi(int idProvinsi){
        this._id_provinsi = idProvinsi;
    }

    public int getKode_Provinsi(){
        return this._kode_provinsi;
    }
    public void setKode_Provinsi(int kode_provinsi){
        this._kode_provinsi = kode_provinsi;
    }

    public String getNama_Provinsi(){
        return this._nama_provinsi;
    }
    public void setNama_Provinsi(String nama_provinsi){
        this._nama_provinsi = nama_provinsi;
    }

    @Override
    public String toString() {
        return "UserInfo [ID Provinsi = "+_id_provinsi+", Kode Provinsi = "+_kode_provinsi+", Nama Provinsi = "+_nama_provinsi+"]";

    }

}
