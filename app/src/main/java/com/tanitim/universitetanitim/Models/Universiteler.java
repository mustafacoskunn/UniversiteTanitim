package com.tanitim.universitetanitim.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Universiteler implements Serializable {

    @SerializedName("isim")
    @Expose
    private String isim;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("kurulus")
    @Expose
    private String kurulus;
    @SerializedName("tur")
    @Expose
    private String tur;
    @SerializedName("il")
    @Expose
    private String il;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("eposta")
    @Expose
    private String eposta;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("adres")
    @Expose
    private String adres;
    @SerializedName("rektor")
    @Expose
    private String rektor;
    @SerializedName("profesor")
    @Expose
    private String profesor;
    @SerializedName("docent")
    @Expose
    private String docent;
    @SerializedName("doktor")
    @Expose
    private String doktor;
    @SerializedName("ogretim")
    @Expose
    private String ogretim;
    @SerializedName("arastirma")
    @Expose
    private String arastirma;
    @SerializedName("onlisanserkek")
    @Expose
    private String onlisanserkek;
    @SerializedName("onlisanskadin")
    @Expose
    private String onlisanskadin;
    @SerializedName("onlisanstoplam")
    @Expose
    private String onlisanstoplam;
    @SerializedName("telefon")
    @Expose
    private String telefon;
    @SerializedName("bolge")
    @Expose
    private String bolge;
    @SerializedName("lisanserkek")
    @Expose
    private String lisanserkek;
    @SerializedName("lisanskadin")
    @Expose
    private String lisanskadin;
    @SerializedName("lisanstoplam")
    @Expose
    private String lisanstoplam;
    @SerializedName("yukseklisanserkek")
    @Expose
    private String yukseklisanserkek;
    @SerializedName("yukseklisanskadin")
    @Expose
    private String yukseklisanskadin;
    @SerializedName("yukseklisanstoplam")
    @Expose
    private String yukseklisanstoplam;
    @SerializedName("doktoraerkek")
    @Expose
    private String doktoraerkek;
    @SerializedName("doktorakadin")
    @Expose
    private String doktorakadin;
    @SerializedName("doktoratoplam")
    @Expose
    private String doktoratoplam;
    @SerializedName("toplamerkek")
    @Expose
    private String toplamerkek;
    @SerializedName("toplamkadin")
    @Expose
    private String toplamkadin;
    @SerializedName("toplam")
    @Expose
    private String toplam;

    @SerializedName("favoriMi")
    @Expose
    private String favoriMi;

    @SerializedName("cihazid")
    @Expose
    private String cihazid;




    public String getFavoriMi() {
        return favoriMi;
    }

    public void setFavoriMi(String cihazid) {
        this.favoriMi = favoriMi;
    }

    public String getCihazid() {
        return cihazid;
    }

    public void setCihazid(String cihazid) {
        this.cihazid = cihazid;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getKurulus() {
        return kurulus;
    }

    public void setKurulus(String kurulus) {
        this.kurulus = kurulus;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getRektor() {
        return rektor;
    }

    public void setRektor(String rektor) {
        this.rektor = rektor;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getDocent() {
        return docent;
    }

    public void setDocent(String docent) {
        this.docent = docent;
    }

    public String getDoktor() {
        return doktor;
    }

    public void setDoktor(String doktor) {
        this.doktor = doktor;
    }

    public String getOgretim() {
        return ogretim;
    }

    public void setOgretim(String ogretim) {
        this.ogretim = ogretim;
    }

    public String getArastirma() {
        return arastirma;
    }

    public void setArastirma(String arastirma) {
        this.arastirma = arastirma;
    }

    public String getOnlisanserkek() {
        return onlisanserkek;
    }

    public void setOnlisanserkek(String onlisanserkek) {
        this.onlisanserkek = onlisanserkek;
    }

    public String getOnlisanskadin() {
        return onlisanskadin;
    }

    public void setOnlisanskadin(String onlisanskadin) {
        this.onlisanskadin = onlisanskadin;
    }

    public String getOnlisanstoplam() {
        return onlisanstoplam;
    }

    public void setOnlisanstoplam(String onlisanstoplam) {
        this.onlisanstoplam = onlisanstoplam;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getBolge() {
        return bolge;
    }

    public void setBolge(String bolge) {
        this.bolge = bolge;
    }

    public String getLisanserkek() {
        return lisanserkek;
    }

    public void setLisanserkek(String lisanserkek) {
        this.lisanserkek = lisanserkek;
    }

    public String getLisanskadin() {
        return lisanskadin;
    }

    public void setLisanskadin(String lisanskadin) {
        this.lisanskadin = lisanskadin;
    }

    public String getLisanstoplam() {
        return lisanstoplam;
    }

    public void setLisanstoplam(String lisanstoplam) {
        this.lisanstoplam = lisanstoplam;
    }

    public String getYukseklisanserkek() {
        return yukseklisanserkek;
    }

    public void setYukseklisanserkek(String yukseklisanserkek) {
        this.yukseklisanserkek = yukseklisanserkek;
    }

    public String getYukseklisanskadin() {
        return yukseklisanskadin;
    }

    public void setYukseklisanskadin(String yukseklisanskadin) {
        this.yukseklisanskadin = yukseklisanskadin;
    }

    public String getYukseklisanstoplam() {
        return yukseklisanstoplam;
    }

    public void setYukseklisanstoplam(String yukseklisanstoplam) {
        this.yukseklisanstoplam = yukseklisanstoplam;
    }

    public String getDoktoraerkek() {
        return doktoraerkek;
    }

    public void setDoktoraerkek(String doktoraerkek) {
        this.doktoraerkek = doktoraerkek;
    }

    public String getDoktorakadin() {
        return doktorakadin;
    }

    public void setDoktorakadin(String doktorakadin) {
        this.doktorakadin = doktorakadin;
    }

    public String getDoktoratoplam() {
        return doktoratoplam;
    }

    public void setDoktoratoplam(String doktoratoplam) {
        this.doktoratoplam = doktoratoplam;
    }

    public String getToplamerkek() {
        return toplamerkek;
    }

    public void setToplamerkek(String toplamerkek) {
        this.toplamerkek = toplamerkek;
    }

    public String getToplamkadin() {
        return toplamkadin;
    }

    public void setToplamkadin(String toplamkadin) {
        this.toplamkadin = toplamkadin;
    }

    public String getToplam() {
        return toplam;
    }

    public void setToplam(String toplam) {
        this.toplam = toplam;
    }

}
