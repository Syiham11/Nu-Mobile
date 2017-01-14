package com.adfin.numobile.model;

/**
 * Created by Siti on 1/14/2017.
 */

public class DataMemberUser {

    private String id_user;
    private String username;
    private String password;
    private String email;
    private String join_date;
    private String closed_date;
    private String last_login;
    private String detail_lengkap;
    private String keterangan;
    private String noktp;
    private String tempat_lahir;
    private String tanggal_lahir;
    private String jenis_kelamin;
    private String status_perkawinan;
    private String fb;
    private String twitter;
    private String instagram;
    private String path;
    private String user_type;
    private String phone;
    private String photo;
    private String photo_thumb;
    private String id_user_level;
    private String status;
    private String alamat;
    private String parent_id;
    private String bahasa;
    private String no_hp;
    private String id_jenis_pekerjaan;
    private String jabatan;
    private String provinsi;
    private String kabkot;
    private String kecamatan;
    private String desa;
    private String kode_pos;

    private String id_pendapatan;
    private String kemampuan;
    private String id_induk_organisasi;
    private String id_pendidikan;
    private String nama_sekolah;
    private String id_nama_member;
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
    private String kategori_kepengurusan;



    public void setid_user(String id_user) {
        this.id_user = id_user;
    }
    public String getid_user() {
        return id_user;
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

    public void setemail(String email) {
        this.email = email;
    }
    public String getemail() {
        return email;
    }

    public void setjoin_date(String join_date) {
        this.join_date = join_date;
    }
    public String getjoin_date() {
        return join_date;
    }

    public void setclosed_date(String closed_date) {
        this.closed_date = closed_date;
    }
    public String getclosed_date() {
        return closed_date;
    }

    public void setlast_login(String last_login) {
        this.last_login = last_login;
    }
    public String getlast_login() {
        return last_login;
    }

    public void setdetail_lengkap(String detail_lengkap) {
        this.detail_lengkap = detail_lengkap;
    }
    public String getdetail_lengkap() {
        return detail_lengkap;
    }

    public void setketerangan(String keterangan) {
        this.keterangan = keterangan;
    }
    public String getketerangan() {
        return keterangan;
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

    public void setpath(String path) {
        this.path = path;
    }
    public String getpath() {
        return path;
    }

    public void setuser_type(String user_type) {
        this.user_type = user_type;
    }
    public String getuser_type() {
        return user_type;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }
    public String getphone() {
        return phone;
    }

    public void setphoto(String photo) {
        this.photo = photo;
    }
    public String getphoto() {
        if( !photo.equals("null") && !photo.equals("") && photo != null )
            return "http://numobile.id/NUMobile/" + photo;
        return null;
    }

    public void setphoto_thumb(String photo_thumb) {
        this.photo_thumb = photo_thumb;
    }
    public String getphoto_thumb() {
        return photo_thumb;
    }

    public void setid_user_level(String id_user_level) {
        this.id_user_level = id_user_level;
    }
    public String getid_user_level() {
        return id_user_level;
    }

    public void setstatus(String status) {
        this.status = status;
    }
    public String getstatus() {
        return status;
    }

    public void setalamat(String alamat) {
        this.alamat = alamat;
    }
    public String getalamat() {
        return alamat;
    }

    public void setparent_id(String parent_id) {
        this.parent_id = parent_id;
    }
    public String getparent_id() {
        return parent_id;
    }

    public void setbahasa(String bahasa) {
        this.bahasa = bahasa;
    }
    public String getbahasa() {
        return bahasa;
    }

    public void setno_hp (String no_hp ) {
        this.no_hp  = no_hp ;
    }
    public String getno_hp () {
        return no_hp ;
    }

    public void setid_jenis_pekerjaan (String id_jenis_pekerjaan ) {
        this.id_jenis_pekerjaan  = id_jenis_pekerjaan ;
    }
    public String getid_jenis_pekerjaan () {
        return id_jenis_pekerjaan ;
    }

    public void setjabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    public String getjabatan() {
        return jabatan;
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

    public void setid_pendapatan(String id_pendapatan) {
        this.id_pendapatan = id_pendapatan;
    }
    public String getid_pendapatan() {
        return id_pendapatan;
    }

    public void setkemampuan(String kemampuan) {
        this.kemampuan = kemampuan;
    }
    public String getkemampuan() {
        return kemampuan;
    }

    public void setnid_induk_organisasi(String id_induk_organisasi) {
        this.id_induk_organisasi = id_induk_organisasi;
    }
    public String getid_induk_organisasi() {
        return id_induk_organisasi;
    }

    public void setid_pendidikan(String id_pendidikan) {
        this.id_pendidikan = id_pendidikan;
    }
    public String getid_pendidikan() {
        return id_pendidikan;
    }

    public void setid_nama_member(String id_nama_member) {
        this.id_nama_member = id_nama_member;
    }
    public String getid_nama_member() {
        return id_nama_member;
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

    public void setkategori_kepengurusan(String kategori_kepengurusan) {
        this.kategori_kepengurusan = kategori_kepengurusan;
    }
    public String getkategori_kepengurusan() {
        return kategori_kepengurusan;
    }


}
