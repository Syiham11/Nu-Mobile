package com.adfin.numobile.modelSQLite;

/**
 * Created by Siti on 9/15/2016.
 */
public class AnggotaData {
    int _id_anggota;
    String _username, _nama, _noktp, _tempat_lahir, _tanggal_lahir,  _jenis_kelamin, _status_perkawinan,_alamat, _provinsi, _kabkot,
            _kecamatan, _desa, _kode_pos, _tlp, _hp, _email, _fb, _twitter, _pekerjaan, _jabatan, _pendapatan, _organisasi,
            _sd, _smp, _sma, _s1, _s2, _s3, _pesantren, _lama_pesantren, _nama_pesantren, _alamat_pesantren, _provinsi_pesantren, _kabkot_pesantren,
            _kecamatan_pesantren, _desa_pesantren,  _photo;

    public AnggotaData()
    {

    }

    public AnggotaData(int id_anggota,String username,String nama, String noktp, String tempat_lahir, String tanggal_lahir, String jenis_kelamin, String status_perkawinan, String alamat,
                       String provinsi, String kabkot, String kecamatan, String desa, String kode_pos, String tlp, String hp, String email, String fb, String twitter, String pekerjaan, String jabatan, String pendapatan, String organisasi,
                       String sd, String smp, String sma, String s1, String s2, String s3, String pesantren, String lama_pesantren, String nama_pesantren,
                       String alamat_pesantren, String provinsi_pesantren, String kabkot_pesantren, String kecamatan_pesantren, String desa_pesantren, String photo){

            this._id_anggota = id_anggota;
            this._username = username;
            this._nama = nama;
            this._noktp = noktp;
            this._tempat_lahir = tempat_lahir;
            this._tanggal_lahir = tanggal_lahir;
            this._jenis_kelamin = jenis_kelamin;
            this._status_perkawinan = status_perkawinan;
            this._alamat = alamat;
            this._provinsi = provinsi;
            this._kabkot = kabkot;
            this._kecamatan = kecamatan;
            this._desa = desa;
            this._kode_pos = kode_pos;
            this._tlp = tlp;
            this._hp = hp;
            this._email = email;
            this._fb = fb;
            this._twitter = twitter;
            this._pekerjaan = pekerjaan;
            this._jabatan = jabatan;
            this._pendapatan = pendapatan;
            this._organisasi = organisasi;
            this._sd = sd;
            this._smp = smp;
            this._sma = sma;
            this._s1 = s1;
            this._s2 = s2;
            this._s3 = s3;
            this._pesantren = pesantren;
            this._lama_pesantren = lama_pesantren;
            this._nama_pesantren = nama_pesantren;
            this._alamat_pesantren = alamat_pesantren;
            this._provinsi_pesantren = provinsi_pesantren;
            this._kabkot_pesantren = kabkot_pesantren;
            this._kecamatan_pesantren = kecamatan_pesantren;
            this._desa_pesantren = desa_pesantren;
            this._photo = photo;
    }

    public AnggotaData(String username, String nama, String noktp, String tempat_lahir, String tanggal_lahir,String jenis_kelamin, String status_perkawinan, String alamat,
                       String provinsi, String kabkot, String kecamatan, String desa,String kode_pos, String tlp, String hp, String email, String fb, String twitter, String pekerjaan, String jabatan, String pendapatan, String organisasi,
                       String sd, String smp, String sma, String s1, String s2, String s3, String pesantren, String lama_pesantren, String nama_pesantren,
                       String alamat_pesantren, String provinsi_pesantren, String kabkot_pesantren, String kecamatan_pesantren, String desa_pesantren, String photo){
        this._username = username;
        this._nama = nama;
        this._noktp = noktp;
        this._tempat_lahir = tempat_lahir;
        this._tanggal_lahir = tanggal_lahir;
        this._jenis_kelamin = jenis_kelamin;
        this._status_perkawinan = status_perkawinan;
        this._alamat = alamat;
        this._provinsi = provinsi;
        this._kabkot = kabkot;
        this._kecamatan = kecamatan;
        this._desa = desa;
        this._kode_pos = kode_pos;
        this._tlp = tlp;
        this._hp = hp;
        this._email = email;
        this._fb = fb;
        this._twitter = twitter;
        this._pekerjaan = pekerjaan;
        this._jabatan = jabatan;
        this._pendapatan = pendapatan;
        this._organisasi = organisasi;
        this._sd = sd;
        this._smp = smp;
        this._sma = sma;
        this._s1 = s1;
        this._s2 = s2;
        this._s3 = s3;
        this._pesantren = pesantren;
        this._lama_pesantren = lama_pesantren;
        this._nama_pesantren = nama_pesantren;
        this._alamat_pesantren = alamat_pesantren;
        this._provinsi_pesantren = provinsi_pesantren;
        this._kabkot_pesantren = kabkot_pesantren;
        this._kecamatan_pesantren = kecamatan_pesantren;
        this._desa_pesantren = desa_pesantren;
        this._photo = photo;
    }


    public int getID_Anggota(){
        return this._id_anggota;
    }
    public void setID_Anggota(int id_anggota){
        this._id_anggota = id_anggota;
    }

    public String getUsername(){
        return this._username;
    }
    public void setUsername(String username){
        this._username = username;
    }

    public String getNama(){
        return this._nama;
    }
    public void setNama(String nama){
        this._nama = nama;
    }

    public String getNoKtp(){
        return this._noktp;
    }
    public void setNoKtp(String noKtp){
        this._noktp = noKtp;
    }

    public String getTempat_Lahir(){
        return this._tempat_lahir;
    }
    public void setTempat_Lahir(String tempat_lahir){
        this._tempat_lahir = tempat_lahir;
    }

    public String getTanggal_Lahir(){
        return this._tanggal_lahir;
    }
    public void getTanggal_Lahir(String tanggal_lahir){
        this._tanggal_lahir = tanggal_lahir;
    }

    public String getJenis_Kelamin(){
        return this._jenis_kelamin;
    }
    public void setJenis_Kelamin(String jenis_kelamin){
        this._jenis_kelamin = jenis_kelamin;
    }

    public String getStatus_Perkawinan(){
        return this._status_perkawinan;
    }
    public void setStatus_Perkawinan(String status_perkawinan){
        this._status_perkawinan = status_perkawinan;
    }

    public String getProvinsi(){
        return this._provinsi;
    }
    public void setProvinsi(String provinsi){this._provinsi = provinsi;}

    public String getKabkot(){
        return this._kabkot;
    }
    public void setKabkot(String kabkot){this._kabkot = kabkot;}

    public String getKecamatan(){
        return this._kecamatan;
    }
    public void setKecamatan(String kecamatan){this._kecamatan = kecamatan;}

    public String getDesa(){
        return this._desa;
    }
    public void setDesa(String desa){this._desa = desa;}

    public String getAlamat(){
        return this._alamat;
    }
    public void setAlamat(String alamat){this._alamat = alamat;}

    public String getKode_Pos(){
        return this._kode_pos;
    }
    public void setKode_Pos(String kode_pos){this._kode_pos = kode_pos;}

    public String getTlp(){
        return this._tlp;
    }
    public void setTlp(String tlp){this._tlp = tlp;}

    public String getEmail(){
        return this._email;
    }
    public void setEmail(String email){this._email = email;}

    public String getFb(){
        return this._fb;
    }
    public void setFb(String fb){this._fb = fb;}

    public String getTwitter(){
        return this._twitter;
    }
    public void setTwitter(String twitter){this._twitter = twitter;}

    public String getPekerjaan(){
        return this._pekerjaan;
    }
    public void setPekerjaan(String pekerjaan){this._pekerjaan = pekerjaan;}

    public String getJabatan(){
        return this._jabatan;
    }
    public void setJabatan(String jabatan){this._jabatan = jabatan;}

    public String getPendapatan(){
        return this._pendapatan;
    }
    public void setPendapatan(String pendapatan){this._pendapatan = pendapatan;}

    public String getOrganisasi(){
        return this._organisasi;
    }
    public void setOrganisasi(String organisasi){this._organisasi = organisasi;}

    public String getSd(){
        return this._sd;
    }
    public void setSd(String sd){this._sd = sd;}

    public String getSmp(){
        return this._smp;
    }
    public void setSmp(String smp){this._smp = smp;}


    public String getSma(){
        return this._sma;
    }
    public void setSma(String sma){this._sma = sma;}


    public String getS1(){
        return this._s1;
    }
    public void setS1(String s1){this._s1 = s1;}


    public String getS2(){
        return this._s2;
    }
    public void setS2(String s2){this._s2 = s2;}


    public String getS3(){
        return this._s3;
    }
    public void setS3(String s3){this._hp = s3;}


    public String getPesantren(){
        return this._pesantren;
    }
    public void setPesantren(String pesantren){this._pesantren = pesantren;}


    public String getLama_Pesantren(){
        return this._lama_pesantren;
    }
    public void setLama_Pesantren(String lama_pesantren){this._lama_pesantren = lama_pesantren;}


    public String getNama_Pesantren(){
        return this._nama_pesantren;
    }
    public void setNama_Pesantren(String nama_pesantren){this._nama_pesantren = nama_pesantren;}


    public String getAlamat_Pesantren(){
        return this._alamat_pesantren;
    }
    public void setAlamat_Pesantren(String alamat_pesantren){this._alamat_pesantren = alamat_pesantren;}

    public String getProvinsi_Pesantren(){
        return this._provinsi_pesantren;
    }
    public void setProvinsi_Pesantren(String provinsi_pesantren){this._provinsi_pesantren = provinsi_pesantren;}

    public String getKabkot_Pesantren(){
        return this._kabkot_pesantren;
    }
    public void setKabkot_Pesantren(String kabkot_pesantren){this._kabkot_pesantren = kabkot_pesantren;}

    public String getKecamatan_Pesantren(){
        return this._kecamatan_pesantren;
    }
    public void setKecamatan_Pesantren(String kecamatan_pesantren){this._kecamatan_pesantren = kecamatan_pesantren;}

    public String getDesa_Pesantren(){
        return this._desa_pesantren;
    }
    public void setDesa_Pesantren(String desa_pesantren){this._desa_pesantren = desa_pesantren;}

    public String getPhoto(){
        return this._photo;
    }
    public void setPhoto(String photo){this._photo = photo;}

    @Override
    public String toString() {
        return "UserInfo [Username=" + _username + ",Nama=" + _nama + ", No KTP=" + _noktp + ", Tempat Lahir="+_tempat_lahir+", Tanggal Lahir="+_tanggal_lahir+",Jenis Kelamin ="+_jenis_kelamin +", " +
                "Status Perkawinan="+_status_perkawinan+", alamat="+_alamat+",Provinsi="+_provinsi+",Kabkot="+_kabkot+",Kecamatan="+_kecamatan+",Desa="+_desa+",Kode Pos="+_kode_pos+", Telepon="+_tlp+", HP="+_hp+", Email="+_email+", Fb="+_fb+", Twitter="+_twitter+", Pekerjaan="+_pekerjaan+"," +
                "Jabatan="+_pekerjaan+", Pendapatan="+_pendapatan+", Organisasi="+_organisasi+", SD="+_sd+", SMP="+_smp+", SMA="+_sma+", S1="+_s1+"," +
                "S2="+_s2+", S3="+_s3+", Pesantren="+_pesantren+", Lama Pesantren="+_lama_pesantren+", Nama Pesantren="+_nama_pesantren+", Alamat Pesantren="+_alamat_pesantren+
                "Provinsi Pesantren="+_provinsi_pesantren+",Kabkot Pesantren="+_kabkot_pesantren+",Kecamatan Pesantren="+_kecamatan_pesantren+",Desa Pesantren="+_desa_pesantren+",Photo="+_photo+"]";

    }
}
