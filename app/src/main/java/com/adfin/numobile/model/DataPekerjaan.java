package com.adfin.numobile.model;

/**
 * Created by Siti on 11/4/2016.
 */
public class DataPekerjaan {

    private String id_warga;
    private String nama_pekerjaan;
    private String nama_instansi;
    private String jabatan;
    private String jumlah_pendapatan;


    public void setid_warga(String id_warga) {
        this.id_warga = id_warga;
    }
    public String getid_warga() {
        return id_warga;
    }

    public void setnama_pekerjaan(String nama_pekerjaan) {
        this.nama_pekerjaan = nama_pekerjaan;
    }
    public String getnama_pekerjaan() {
        return nama_pekerjaan;
    }

    public void setnama_instansi(String nama_instansi) {
        this.nama_instansi = nama_instansi;
    }
    public String getnama_instansi() {
        return nama_instansi;
    }

    public void setjabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    public String getjabatan() {
        return jabatan;
    }

    public void setjumlah_pendapatan(String jumlah_pendapatan) {
        this.jumlah_pendapatan = jumlah_pendapatan;
    }
    public String getjumlah_pendapatan() {
        return jumlah_pendapatan;
    }
    
}
