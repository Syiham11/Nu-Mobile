package com.adfin.numobile.model;

/**
 * Created by Siti on 12/31/2016.
 */

public class DataKontak {

    private String id_kontak;
    private String nama_kontak;
    private String email_kontak;
    private String telp_kontak;
    private String alamat_kontak;

    public void setid_kontak(String id_kontak) {
        this.id_kontak = id_kontak;
    }
    public String getid_kontak() {
        return id_kontak;
    }

    public void setnama_kontak(String nama_kontak) {
        this.nama_kontak = nama_kontak;
    }
    public String getnama_kontak() {
        return nama_kontak;
    }

    public void setemail_kontak(String email_kontak) {
        this.email_kontak = email_kontak;
    }
    public String getemail_kontak() {
        return email_kontak;
    }

    public void settelp_kontak(String telp_kontak) {
        this.telp_kontak = telp_kontak;
    }
    public String gettelp_kontak() {
        return telp_kontak;
    }


    public void setalamat_kontak(String alamat_kontak) {
        this.alamat_kontak = alamat_kontak;
    }
    public String getalamat_kontak() {
        return alamat_kontak;
    }
    
}
