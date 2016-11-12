package com.adfin.numobile.model;

/**
 * Created by Siti on 9/15/2016.
 */
public class DataKabupaten {

    private String id_kabkot;
    private String kode_provinsi;
    private String kode_kabkot;
    private String nama_kabkot;

    public void setid_kabkot(String id_kabkot) {
        this.id_kabkot = id_kabkot;
    }
    public String getid_kabkot() {
        return id_kabkot;
    }

    public void setkode_provinsi(String kode_provinsi) {
        this.kode_provinsi = kode_provinsi;
    }
    public String getkode_provinsi() {
        return kode_provinsi;
    }

    public void setkode_kabkot(String kode_kabkot) {
        this.kode_kabkot = kode_kabkot;
    }
    public String getkode_kabkot() {
        return kode_kabkot;
    }

    public void setnama_kabkot(String nama_kabkot) {
        this.nama_kabkot = nama_kabkot;
    }
    public String getnama_kabkot() {
        return nama_kabkot;
    }
}
