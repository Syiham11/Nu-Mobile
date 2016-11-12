package com.adfin.numobile.model;

/**
 * Created by Siti on 9/16/2016.
 */
public class DataKecamatan {

    private String id_kecamatan;
    private String kode_provinsi;
    private String kode_kabkot;
    private String kode_kecamatan;
    private String nama_kecamatan;

    public void setid_kecamatan(String id_kecamatan) {
        this.id_kecamatan = id_kecamatan;
    }
    public String getid_kecamatan() {
        return id_kecamatan;
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

    public void setkode_kecamatan(String kode_kecamatan) {
        this.kode_kecamatan = kode_kecamatan;
    }
    public String getkode_kecamatan() {
        return kode_kecamatan;
    }

    public void setnama_kecamatan(String id_kecamatan) {
        this.nama_kecamatan = nama_kecamatan;
    }
    public String getnama_kecamatan() {
        return nama_kecamatan;
    }
}
