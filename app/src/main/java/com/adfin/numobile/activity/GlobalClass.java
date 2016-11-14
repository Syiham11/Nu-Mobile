package com.adfin.numobile.activity;

import android.app.Application;

/**
 * Created by Siti on 9/14/2016.
 */
public class GlobalClass extends Application {

    private String vid_warga;
    //FORM 1
    private String vUsername;
    private String vNamaLengkap;
    private String vNoKtp;
    private String vTempatLahir;
    private String vTanggalLahir;
    private String vJenisKelamin;
    private String vStatusPerkawinan;
    private String vAlamat;
    private String vProvinsi;
    private String vKabupaten;//10
    private String vKecamatan;
    private String vDesa;
    private String vKodePos;
    private String vTelepon;
    private String vHandphone;
    private String vEmail;
    private String vFacebook;
    private String vTwitter;
    private String vInstagram;
    private String vPathh;//20

    //FORM 2
    private String vPekerjaan;
    private String vInstansi;
    private String vJabatan;
    private String vPendapatan;
    private String vKemampuan;
    private String vid_induk_organisasi;
    private String vSD;
    private String vSMP;
    private String vSMA;
    private String vD1;
    private String vD3;
    private String vS1;
    private String vS2;
    private String vS3;


    //FORM 3
    private String vYaTidakPesantren;
    private String vLamaPesantren;
    private String vIdPesantren1;
    private String vIdPesantren2;
    private String vInfak;
    private String vJalur;
    private String vNominalDonasi;
    private String vInfakWarga;
    private String vJalurWarga;
    private String vNominalDonasiWarga;
    private String vIdStatusMember;

    //FORM 4
    private String vNamaPhoto;

    //-----------------------------
    //FORM 1
    public String getId() {
        return vid_warga;
    }
    public String getUsername() {
        return vUsername;
    }
    public String getNamaLengkap() {
        return vNamaLengkap;
    }
    public String getNoKtp() {
        return vNoKtp;
    }
    public String getTempatLahir() {
        return vTempatLahir;
    }
    public String getTanggalLahir() {
        return vTanggalLahir;
    }
    public String getJenisKelamin() {
        return vJenisKelamin;
    }
    public String getStatusPerkawinan() {
        return vStatusPerkawinan;
    }
    public String getAlamat() {
        return vAlamat;
    }
    public String getProvinsi() {
        return vProvinsi;
    }
    public String getKabupaten() {
        return vKabupaten;
    }
    public String getKecamatan() {
        return vKecamatan;
    }
    public String getDesa() {
        return vDesa;
    }
    public String getKodePos() {
        return vKodePos;
    }
    public String getTelepon() {
        return vTelepon;
    }
    public String getHandphone() {
        return vHandphone;
    }
    public String getEmail() {
        return vEmail;
    }
    public String getFacebook() {
        return vFacebook;
    }
    public String getTwitter() {
        return vTwitter;
    }
    public String getInstagram() {
        return vInstagram;
    }
    public String getPathh() {
        return vPathh;
    }

    //FORM 2
    public String getPekerjaan() {
        return vPekerjaan;
    }
    public String getInstansi() {
        return vInstansi;
    }
    public String getJabatan() {
        return vJabatan;
    }
    public String getPendapatan() {
        return vPendapatan;
    }
    public String getKemampuan() {
        return vKemampuan;
    }
    public String getIndukOrganisasi() {
        return vid_induk_organisasi;
    }
    public String getSD() {
        return vSD;
    }
    public String getSMP() {
        return vSMP;
    }
    public String getSMA() {
        return vSMA;
    }
    public String getD1() {
        return vD1;
    }
    public String getD3() {
        return vD3;
    }
    public String getS1() {
        return vS1;
    }
    public String getS2() {
        return vS2;
    }
    public String getS3() {
        return vS3;
    }


    //FORM 3
    public String getYaTidakPesantren() {
        return vYaTidakPesantren;
    }
    public String getLamaPesantren() {
        return vLamaPesantren;
    }
    public String getIdPesantren1() {
        return vIdPesantren1;
    }
    public String getIdPesantren2() {
        return vIdPesantren2;
    }
    public String getInfak() {
        return vInfak;
    }
    public String getJalur() {
        return vJalur;
    }
    public String getNominalDonasi() {
        return vNominalDonasi;
    }
    public String getInfakWarga() {
        return vInfakWarga;
    }
    public String getJalurWarga() {
        return vJalurWarga;
    }
    public String getNominalDonasiWarga() {
        return vNominalDonasiWarga;
    }
    public String getIdStatusMember() {
        return vIdStatusMember;
    }


    //FORM 4
    public String getNamaPhoto() {
        return vNamaPhoto;
    }

    //-----------------------------
    //FORM 1
    public void setId(String str)  {
        vid_warga= str;
    }
    public void setUsername(String str)  {
        vUsername= str;
    }
    public void setNamaLengkap(String str)  {
        vNamaLengkap= str;
    }
    public void setNoKtp(String str)  {
        vNoKtp= str;
    }
    public void setTempatLahir(String str)  {
        vTempatLahir= str;
    }
    public void setTanggalLahir(String str)  {
        vTanggalLahir= str;
    }
    public void setJenisKelamin(String str)  {
        vJenisKelamin= str;
    }
    public void setStatusPerkawinan(String str)  {
        vStatusPerkawinan= str;
    }
    public void setAlamat(String str)  {
        vAlamat= str;
    }
    public void setProvinsi(String str)  {
        vProvinsi= str;
    }
    public void setKabupaten(String str)  {
        vKabupaten= str;
    }
    public void setKecamatan(String str)  {
        vKecamatan= str;
    }
    public void setDesa(String str)  {
        vDesa= str;
    }
    public void setKodePos(String str)  {
        vKodePos= str;
    }
    public void setTelepon(String str)  {
        vTelepon= str;
    }
    public void setHandphone(String str)  {
        vHandphone= str;
    }
    public void setEmail(String str)  {
        vEmail= str;
    }
    public void setFacebook(String str)  {
        vFacebook= str;
    }
    public void setTwitter(String str)  {
        vTwitter= str;
    }
    public void setInstagram(String str)  {
        vInstagram= str;
    }
    public void setPathh(String str)  {
        vPathh= str;
    }

    //FORM 2
    public void setPekerjaan(String str)  {
        vPekerjaan= str;
    }
    public void setInstansi(String str)  {
        vInstansi= str;
    }
    public void setJabatan(String str)  {
        vJabatan= str;
    }
    public void setPendapatan(String str)  {
        vPendapatan= str;
    }
    public void setKemampuan(String str)  {
        vKemampuan= str;
    }
    public void setIndukOrganisasi(String str)  {
        vid_induk_organisasi= str;
    }
    public void setSD(String str)  {
        vSD= str;
    }
    public void setSMP(String str)  {
        vSMP= str;
    }
    public void setSMA(String str)  {
        vSMA= str;
    }
    public void setD1(String str)  {
        vD1= str;
    }
    public void setD3(String str)  {
        vD3= str;
    }
    public void setS1(String str)  {
        vS1= str;
    }
    public void setS2(String str)  {
        vS2= str;
    }
    public void setS3(String str)  {
        vS3= str;
    }


    //FORM 3
    public void setYaTidakPesantren(String str)  {
        vYaTidakPesantren= str;
    }
    public void setLamaPesantren(String str)  {
        vLamaPesantren= str;
    }
    public void setIdPesantren1(String str)  {
        vIdPesantren1= str;
    }
    public void setIdPesantren2(String str)  {
        vIdPesantren2= str;
    }
    public void setInfak(String str)  {
        vInfak= str;
    }
    public void setJalur(String str)  {
        vJalur= str;
    }
    public void setNominalDonasi(String str)  {
        vNominalDonasi= str;
    }
    public void setInfakWarga(String str)  {
        vInfakWarga= str;
    }
    public void setJalurWarga(String str)  {
        vJalurWarga= str;
    }
    public void setNominalDonasiWarga(String str)  {
        vNominalDonasiWarga= str;
    }
    public void setIdStatusMember(String str)  {
        vIdStatusMember= str;
    }


    //FORM 4
    public void setNamaPhoto(String str)  {
        vNamaPhoto= str;
    }

}
