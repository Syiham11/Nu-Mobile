package com.adfin.numobile.model;

/**
 * Created by Siti on 11/4/2016.
 */
public class DataPesantren {

    private String id_pesantren;
    private String nama_pesantren;
    private String alamat_pesantren;
    private String provinsi;
    private String kabkot;
    private String kecamatan;
    private String desa;
    private String negara;

    public void setid_pesantren (String id_pesantren) {
        this.id_pesantren = id_pesantren;
    }
    public String getid_pesantren() {
        return id_pesantren;
    }
    
    public void setnama_pesantren(String nama_pesantren) {
        this.nama_pesantren = nama_pesantren;
    }
    public String getnama_pesantren() {
        return nama_pesantren;
    }

    public void setalamat_pesantren(String alamat_pesantren) {
        this.alamat_pesantren = alamat_pesantren;
    }
    public String getalamat_pesantren() {
        return alamat_pesantren;
    }

    public void setprovinsi(String provinsi) {
        this.provinsi = provinsi;
    }
    public String getprovinsi() {
        return provinsi;
    }

    public void setkabkot(String kabkot) {
        this.kabkot = kabkot;
    }
    public String getkabkot() {
        return kabkot;
    }

    public void setkecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }
    public String getkecamatan() {
        return kecamatan;
    }

    public void setdesa(String desa_p1) {
        this.desa = desa;
    }
    public String getdesa() {
        return desa;
    }

    public void setnegara(String negara) {
        this.negara = negara;
    }
    public String getnegara() {
        return negara;
    }

}
