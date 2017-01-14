package com.adfin.numobile.model;

/**
 * Created by Siti on 1/14/2017.
 */

public class DataMemberUserLevel {

    private String id_user_level;
    private String nama_level;
    private String keterangan_level;

    public void setid_user_level(String id_user_level) {
        this.id_user_level = id_user_level;
    }
    public String getid_user_level() {
        return id_user_level;
    }

    public void setnama_level(String nama_level) {
        this.nama_level = nama_level;
    }
    public String getnama_level() {
        return nama_level;
    }

    public void setketerangan_level(String keterangan_level) {
        this.keterangan_level = keterangan_level;
    }
    public String getketerangan_level() {
        return keterangan_level;
    }
}
