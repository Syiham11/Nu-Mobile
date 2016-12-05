package com.adfin.numobile.model;

/**
 * Created by Siti on 11/28/2016.
 */

public class DataSDM {

    private String id_sdm;
    private String nama;
    private String id_prov;
    private String id_kab;
    private String alamat;
    private String telp;
    private String email;
    private String npwp;
    private String no_ktp;
    private String image;
    private String status;
    private String id_type;
    private String file_resume;
    private String id_user;
    private String unit;

    public void setid_sdm (String id_sdm) {
        this.id_sdm = id_sdm;
    }
    public String getid_sdm() {
        return id_sdm;
    }

    public void setnama (String nama) {
        this.nama = nama;
    }
    public String getnama() {
        return nama;
    }

    public void setid_prov (String id_prov) {
        this.id_prov = id_prov;
    }
    public String getid_prov() {
        return id_prov;
    }

    public void setid_kab (String id_kab) {
        this.id_kab = id_kab;
    }
    public String getid_kab() {
        return id_kab;
    }

    public void setalamat (String alamat) {
        this.alamat = alamat;
    }
    public String getalamat() {
        return alamat;
    }

    public void settelp (String telp) {
        this.telp = telp;
    }
    public String gettelp() {
        return telp;
    }

    public void setemail (String email) {
        this.email = email;
    }
    public String getemail() {
        return email;
    }

    public void setnpwp (String npwp) {
        this.npwp = npwp;
    }
    public String getnpwp() {
        return npwp;
    }

    public void setno_ktp (String no_ktp) {
        this.no_ktp = no_ktp;
    }
    public String getno_ktp() {
        return no_ktp;
    }

    public void setimage (String image) {
        this.image = image;
    }
    public String getimage() {
        return "http://www.numobile.id/__asset/__images/member/" + image;
    }

    public void setstatus (String status) {
        this.status = status;
    }
    public String getstatus() {
        return status;
    }

    public void setid_type (String id_type) {
        this.id_type = id_type;
    }
    public String getid_type() {
        return id_type;
    }

    public void setfile_resume (String file_resume) {
        this.file_resume = file_resume;
    }
    public String getfile_resume() {
        return file_resume;
    }

    public void setid_user (String id_user) {
        this.id_user = id_user;
    }
    public String getid_user() {
        return id_user;
    }

    public void setunit (String unit) {
        this.unit = unit;
    }
    public String getunit() {
        return unit;
    }

}
