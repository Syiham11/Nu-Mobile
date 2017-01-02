package com.adfin.numobile.model;
// Created by prakasa on 31/12/16.

public class DataSuratMasuk {

    private String id_surat_masuk,
            tanggal_surat,
            id_pengirim,
            diteruskan_kepada,
            image,
            isi_surat;

    public void setid_surat_masuk(String id_surat_masuk) {
        this.id_surat_masuk = id_surat_masuk;
    }

    public String getid_surat_masuk() {
        return id_surat_masuk;
    }

    public void settanggal_surat(String tanggal_surat) {
        this.tanggal_surat = tanggal_surat;
    }

    public String gettanggal_surat() {
        return tanggal_surat;
    }

    public void setid_pengirim(String id_pengirim) {
        this.id_pengirim = id_pengirim;
    }

    public String getid_pengirim() {
        return id_pengirim;
    }

    public void setditeruskan_kepada(String diteruskan_kepada) {
        this.diteruskan_kepada = diteruskan_kepada;
    }

    public String getditeruskan_kepada() {
        return diteruskan_kepada;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public String getimage() {
        if( !image.equals("null") && !image.equals("") && image != null )
            return "http://numobile.id/NUMobile/" + image;
        return null;
    }

    public void setisi_surat(String isi_surat) {
        this.isi_surat = isi_surat;
    }

    public String getisi_surat() {
        return isi_surat;
    }
}
