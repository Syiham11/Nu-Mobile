package com.adfin.numobile.model;

/**
 * Created by Siti on 11/4/2016.
 */
public class DataWarga {

    private String id_warga;
    private String username;
    private String password;
    private String nama;
    private String noktp;
    private String tempat_lahir;
    private String tanggal_lahir;
    private String jenis_kelamin;
    private String status_perkawinan;
    private String alamat;
    private String provinsi;
    private String kabkot;
    private String kecamatan;
    private String desa;
    private String kode_pos;
    private String tlp;
    private String hp;
    private String email;
    private String fb;
    private String twitter;
    private String instagram;
    private String photo;
    private String kemampuan;
    private String nama_induk_organisasi;
    private String nama_member;
    private String ya_tidak_pesantren;
    private String lama_pesantren;
    private String id_pesantren1;
    private String id_pesantren2;
    private String infak_organisasi;
    private String donasi_organisasi;
    private String nominal_organisasi;
    private String infak_warga;
    private String donasi_warga;
    private String nominal_warga;
    private String latitude;
    private String longtitude;
    private String flag;
   


    public void setid_warga(String id_warga) {
        this.id_warga = id_warga;
    }
    public String getid_warga() {
        return id_warga;
    }

    public void setusername(String username) {
        this.username = username;
    }
    public String getusername() {
        return username;
    }

    public void setpassword(String password) {
        this.password = password;
    }
    public String getpassword() {
        return password;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }
    public String getnama() {
        return nama;
    }

    public void setnoktp(String noktp) {
        this.noktp = noktp;
    }
    public String getnoktp() {
        return noktp;
    }

    public void settempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }
    public String gettempat_lahir() {
        return tempat_lahir;
    }

    public void settanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }
    public String gettanggal_lahir() {
        return tanggal_lahir;
    }

    public void setjenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }
    public String getjenis_kelamin() {
        return jenis_kelamin;
    }

    public void setstatus_perkawinan(String status_perkawinan) {
        this.status_perkawinan = status_perkawinan;
    }
    public String getstatus_perkawinan() {
        return status_perkawinan;
    }

    public void setalamat(String alamat) {
        this.alamat = alamat;
    }
    public String getalamat() {
        return alamat;
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

    public void setdesa(String desa) {
        this.desa = desa;
    }
    public String getdesa() {
        return desa;
    }

    public void setkode_pos(String kode_pos) {
        this.kode_pos = kode_pos  ;
    }
    public String getkode_pos() {
        return kode_pos  ;
    }

    public void settlp(String tlp) {
        this.tlp = tlp;
    }
    public String gettlp() {
        return tlp;
    }

    public void sethp(String hp) {
        this.hp = hp;
    }
    public String gethp() {
        return hp;
    }

    public void setemail(String email) {
        this.email = email;
    }
    public String getemail() {
        return email;
    }

    public void setfb(String fb) {
        this.fb = fb;
    }
    public String getfb() {
        return fb;
    }

    public void settwitter(String twitter) {
        this.twitter = twitter;
    }
    public String gettwitter() {
        return twitter;
    }

    public void setinstagram(String instagram) {
        this.instagram = instagram;
    }
    public String getinstagram() {
        return instagram;
    }

//    public void setpathh(String pathh) {
//        this.pathh = pathh;
//    }
//    public String getpathh() {
//        return pathh;
//    }

    public void setphoto(String photo) {
        this.photo = photo;
    }
    public String getphoto() {
        if( !photo.equals("null") )
            return "http://numobile.id/NUMobile/" + photo;
        return null;
    }



    public void setkemampuan(String kemampuan) {
        this.kemampuan = kemampuan;
    }
    public String getkemampuan() {
        return kemampuan;
    }


    public void setnama_induk_organisasi(String nama_induk_organisasi) {
        this.nama_induk_organisasi = nama_induk_organisasi;
    }
    public String getnama_induk_organisasi() {
        return nama_induk_organisasi;
    }

    public void setnama_member(String nama_member) {
        this.nama_member = nama_member;
    }
    public String getnama_member() {
        return nama_member;
    }

    public void setya_tidak_pesantren(String ya_tidak_pesantren) {
        this.ya_tidak_pesantren = ya_tidak_pesantren;
    }
    public String getya_tidak_pesantren() {
        return ya_tidak_pesantren;
    }

    public void setlama_pesantren(String lama_pesantren) {
        this.lama_pesantren = lama_pesantren;
    }
    public String getlama_pesantren() {
        return lama_pesantren;
    }

    public void setid_pesantren1(String id_pesantren1) {
        this.id_pesantren1 = id_pesantren1;
    }
    public String getid_pesantren1() {
        return id_pesantren1;
    }

    public void setid_pesantren2(String id_pesantren2) {
        this.id_pesantren2 = id_pesantren2;
    }
    public String getid_pesantren2() {
        return id_pesantren2;
    }


    public void setinfak_organisasi(String infak_organisasi) {
        this.infak_organisasi = infak_organisasi;
    }
    public String getinfak_organisasi() {
        return infak_organisasi;
    }


    public void setdonasi_organisasi(String donasi_organisasi) {
        this.donasi_organisasi = donasi_organisasi;
    }
    public String getdonasi_organisasi() {
        return donasi_organisasi;
    }

    public void setnominal_organisasi(String nominal_organisasi) {
        this.nominal_organisasi = nominal_organisasi;
    }
    public String getnominal_organisasi() {
        return nominal_organisasi;
    }

    public void setinfak_warga(String infak_warga) {
        this.infak_warga = infak_warga;
    }
    public String getinfak_warga() {
        return infak_warga;
    }

    public void setdonasi_warga(String donasi_warga) {
        this.donasi_warga = donasi_warga;
    }
    public String getdonasi_warga() {
        return donasi_warga;
    }

    public void setnominal_warga(String nominal_warga) {
        this.nominal_warga = nominal_warga;
    }
    public String getnominal_warga() {
        return nominal_warga;
    }

    public void setlatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getlatitude() {
        return latitude;
    }

    public void setlongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
    public String getlongtitude() {
        return longtitude;
    }



    public void setflag(String flag) {
        this.flag = flag;
    }
    public String getflag() {
        return flag;
    }

    /* Generate View For List */
}
