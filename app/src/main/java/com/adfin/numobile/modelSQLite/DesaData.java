package com.adfin.numobile.modelSQLite;

/**
 * Created by Siti on 9/28/2016.
 */
public class DesaData {
    int _id_desa,_kode_provinsi, _kode_kabkot, _kode_kecamatan, _kode_desa;
    String _nama_desa;

    public DesaData(){

    }

    public DesaData(int id_desa, int kode_provinsi, int kode_kabkot, int kode_kecamatan, int kode_desa, String nama_desa){
        this._id_desa = id_desa;
        this._kode_provinsi = kode_provinsi;
        this._kode_kabkot = kode_kabkot;
        this._kode_kecamatan = kode_kecamatan;
        this._kode_desa = kode_desa;
        this._nama_desa = nama_desa;
    }


    public DesaData(int kode_provinsi, int kode_kabkot, int kode_kecamatan, int kode_desa, String nama_desa){
        this._kode_provinsi = kode_provinsi;
        this._kode_kabkot = kode_kabkot;
        this._kode_kecamatan = kode_kecamatan;
        this._kode_desa = kode_desa;
        this._nama_desa = nama_desa;
    }

    public int getId_Desa(){
        return this._id_desa;
    }
    public void setId_Desa(int id_desa){
        this._id_desa = id_desa;
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

    public int getKode_Desa(){
        return this._kode_desa;
    }
    public void setKode_Desa(int kode_desa){
        this._kode_desa = kode_desa;
    }

    public String getNama_Desa(){
        return this._nama_desa;
    }
    public void setNama_Desa(String nama_desa){
        this._nama_desa = nama_desa;
    }

    @Override
    public String toString() {
        return "UserInfo [ID Desa = "+_id_desa+", Kode Provinsi = "+_kode_provinsi+", Kode Kabkot = "+_kode_kabkot+", Kpde Kecamatan = "+_kode_kecamatan+"," +
                "Kode Desa = "+_kode_desa+", Nama Desa = "+_nama_desa+"]";

    }
}
