
package com.adfin.numobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Photo {

    @SerializedName("id_photo")
    @Expose
    private Integer idPhoto;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("file_foto")
    @Expose
    private String fileFoto;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("langitude")
    @Expose
    private String langitude;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status_upload")
    @Expose
    private String statusUpload;

    boolean value;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Photo() {
    }

    /**
     * 
     * @param updatedAt
     * @param langitude
     * @param idPhoto
     * @param fileFoto
     * @param createdAt
     * @param statusUpload
     * @param idUser
     * @param latitude
     * @param tanggal
     * @param deskripsi
     */
    public Photo(Integer idPhoto, String idUser, String deskripsi, String fileFoto, String latitude, String langitude, String tanggal, String createdAt, String updatedAt, String statusUpload) {
        this.idPhoto = idPhoto;
        this.idUser = idUser;
        this.deskripsi = deskripsi;
        this.fileFoto = fileFoto;
        this.latitude = latitude;
        this.langitude = langitude;
        this.tanggal = tanggal;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.statusUpload = statusUpload;
    }

    public Photo(Integer idPhoto, String idUser, String deskripsi, String fileFoto, String latitude, String langitude, String tanggal, String createdAt, String updatedAt, String statusUpload, Boolean value) {
        this.idPhoto = idPhoto;
        this.idUser = idUser;
        this.deskripsi = deskripsi;
        this.fileFoto = fileFoto;
        this.latitude = latitude;
        this.langitude = langitude;
        this.tanggal = tanggal;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.statusUpload = statusUpload;
        this.value = value;
    }

    /**
     * 
     * @return
     *     The idPhoto
     */
    public Integer getIdPhoto() {
        return idPhoto;
    }

    /**
     * 
     * @param idPhoto
     *     The id_photo
     */
    public void setIdPhoto(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

    /**
     * 
     * @return
     *     The idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * 
     * @param idUser
     *     The id_user
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * 
     * @return
     *     The deskripsi
     */
    public String getDeskripsi() {
        return deskripsi;
    }

    /**
     * 
     * @param deskripsi
     *     The deskripsi
     */
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    /**
     * 
     * @return
     *     The fileFoto
     */
    public String getFileFoto() {
        return fileFoto;
    }

    /**
     * 
     * @param fileFoto
     *     The file_foto
     */
    public void setFileFoto(String fileFoto) {
        this.fileFoto = fileFoto;
    }

    /**
     * 
     * @return
     *     The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The langitude
     */
    public String getLangitude() {
        return langitude;
    }

    /**
     * 
     * @param langitude
     *     The langitude
     */
    public void setLangitude(String langitude) {
        this.langitude = langitude;
    }

    /**
     * 
     * @return
     *     The tanggal
     */
    public String getTanggal() {
        return tanggal;
    }

    /**
     * 
     * @param tanggal
     *     The tanggal
     */
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The statusUpload
     */
    public String getStatusUpload() {
        return statusUpload;
    }

    /**
     * 
     * @param statusUpload
     *     The status_upload
     */
    public void setStatusUpload(String statusUpload) {
        this.statusUpload = statusUpload;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

}
