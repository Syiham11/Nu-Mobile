package com.adfin.numobile.model;

/**
 * Created by prakasa on 17/12/16.
 */

public class DataPeristiwa {
    private String id_peristiwa;
    private String id_warga;
    private String deskripsi;
    private String path;
    private String latitude;
    private String langitude;
    private String created_at;
    private String type;

    public String getid_peristiwa() {
        return id_peristiwa;
    }

    public String getid_warga() {
        return id_warga;
    }

    public String getdeskripsi() {
        return deskripsi;
    }

    public String getpath() {
        return "http://numobile.id/NUMobile/" + path;
    }

    public String getlatitude() {
        return latitude;
    }

    public String getlangitude() {
        return langitude;
    }

    public String getcreated_at() {
        return created_at;
    }

    public String gettype() {
        return type;
    }
}
