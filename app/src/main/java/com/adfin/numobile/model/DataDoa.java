package com.adfin.numobile.model;

/**
 * Created by Siti on 11/30/2016.
 */

public class DataDoa {
    
    private String id;
    private String nama_doa;
    private String image_header;
    private String image_doa;


    public void setid(String id) {
        this.id = id;
    }
    public String getid() {
        return id;
    }

    public void setnama_doa(String nama_doa) {
        this.nama_doa = nama_doa;
    }
    public String getnama_doa() {
        return nama_doa;
    }

    public void setimage_header(String image_header) {
        this.image_header = image_header;
    }
    public String getimage_header() {
       return "http://www.numobile.id/__asset/__images/gambar_doa/" + image_header;
    }

    public void setimage_doa(String image_doa) {
        this.image_doa = image_doa;
    }
    public String getimage_doa() {
        return "http://www.numobile.id/__asset/__images/gambar_doa/" + image_doa;
    }
    
}
