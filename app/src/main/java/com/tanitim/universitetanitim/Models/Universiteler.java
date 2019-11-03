package com.tanitim.universitetanitim.Models;

import java.io.Serializable;



public class Universiteler implements Serializable {
    private String adres;
    private int arastirma;
    private int docent;
    private int doktor;
    private int doktoraerkek;
    private int doktorakadin;
    private int doktoratoplam;
    private String eposta;
    private String il;
    private String isim;
    private String kurulus;
    private int lisanserkek;
    private int lisanskadin;
    private int lisanstoplam;
    private int ogretim;
    private int onlisanserkek;
    private int onlisanskadin;
    private int onlisanstoplam;
    private int profesor;
    private String rektor;
    private String slug;
    private int toplam;
    private int toplamerkek;
    private int toplamkadin;
    private String tur;
    private String website;
    private int yukseklisanserkek;
    private int yukseklisanskadin;
    private int yukseklisanstoplam;

    private String fax;
    private String bolge;
    private String telefon;

    public Universiteler(String adres, int arastirma, int docent, int doktor, int doktoraerkek, int doktorakadin, int doktoratoplam, String eposta, String il, String isim, String kurulus, int lisanserkek, int lisanskadin, int lisanstoplam, int ogretim, int onlisanserkek, int onlisanskadin, int onlisanstoplam, int profesor, String rektor, String slug, int toplam, int toplamerkek, int toplamkadin, String tur, String website, int yukseklisanserkek, int yukseklisanskadin, int yukseklisanstoplam, String fax, String bolge, String telefon) {
        this.adres = adres;
        this.arastirma = arastirma;
        this.docent = docent;
        this.doktor = doktor;
        this.doktoraerkek = doktoraerkek;
        this.doktorakadin = doktorakadin;
        this.doktoratoplam = doktoratoplam;
        this.eposta = eposta;
        this.il = il;
        this.isim = isim;
        this.kurulus = kurulus;
        this.lisanserkek = lisanserkek;
        this.lisanskadin = lisanskadin;
        this.lisanstoplam = lisanstoplam;
        this.ogretim = ogretim;
        this.onlisanserkek = onlisanserkek;
        this.onlisanskadin = onlisanskadin;
        this.onlisanstoplam = onlisanstoplam;
        this.profesor = profesor;
        this.rektor = rektor;
        this.slug = slug;
        this.toplam = toplam;
        this.toplamerkek = toplamerkek;
        this.toplamkadin = toplamkadin;
        this.tur = tur;
        this.website = website;
        this.yukseklisanserkek = yukseklisanserkek;
        this.yukseklisanskadin = yukseklisanskadin;
        this.yukseklisanstoplam = yukseklisanstoplam;

        this.fax = fax;
        this.bolge = bolge;
        this.telefon = telefon;
    }
    public Universiteler(String il){
        this.il = il;

    }

    public Universiteler() {
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBolge() {
        return bolge;
    }

    public void setBolge(String bolge) {
        this.bolge = bolge;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getArastirma() {
        return arastirma;
    }

    public void setArastirma(int arastirma) {
        this.arastirma = arastirma;
    }

    public int getDocent() {
        return docent;
    }

    public void setDocent(int docent) {
        this.docent = docent;
    }

    public int getDoktor() {
        return doktor;
    }

    public void setDoktor(int doktor) {
        this.doktor = doktor;
    }

    public int getDoktoraerkek() {
        return doktoraerkek;
    }

    public void setDoktoraerkek(int doktoraerkek) {
        this.doktoraerkek = doktoraerkek;
    }

    public int getDoktorakadin() {
        return doktorakadin;
    }

    public void setDoktorakadin(int doktorakadin) {
        this.doktorakadin = doktorakadin;
    }

    public int getDoktoratoplam() {
        return doktoratoplam;
    }

    public void setDoktoratoplam(int doktoratoplam) {
        this.doktoratoplam = doktoratoplam;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getKurulus() {
        return kurulus;
    }

    public void setKurulus(String kurulus) {
        this.kurulus = kurulus;
    }

    public int getLisanserkek() {
        return lisanserkek;
    }

    public void setLisanserkek(int lisanserkek) {
        this.lisanserkek = lisanserkek;
    }

    public int getLisanskadin() {
        return lisanskadin;
    }

    public void setLisanskadin(int lisanskadin) {
        this.lisanskadin = lisanskadin;
    }

    public int getLisanstoplam() {
        return lisanstoplam;
    }

    public void setLisanstoplam(int lisanstoplam) {
        this.lisanstoplam = lisanstoplam;
    }

    public int getOgretim() {
        return ogretim;
    }

    public void setOgretim(int ogretim) {
        this.ogretim = ogretim;
    }

    public int getOnlisanserkek() {
        return onlisanserkek;
    }

    public void setOnlisanserkek(int onlisanserkek) {
        this.onlisanserkek = onlisanserkek;
    }

    public int getOnlisanskadin() {
        return onlisanskadin;
    }

    public void setOnlisanskadin(int onlisanskadin) {
        this.onlisanskadin = onlisanskadin;
    }

    public int getOnlisanstoplam() {
        return onlisanstoplam;
    }

    public void setOnlisanstoplam(int onlisanstoplam) {
        this.onlisanstoplam = onlisanstoplam;
    }

    public int getProfesor() {
        return profesor;
    }

    public void setProfesor(int profesor) {
        this.profesor = profesor;
    }

    public String getRektor() {
        return rektor;
    }

    public void setRektor(String rektor) {
        this.rektor = rektor;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getToplam() {
        return toplam;
    }

    public void setToplam(int toplam) {
        this.toplam = toplam;
    }

    public int getToplamerkek() {
        return toplamerkek;
    }

    public void setToplamerkek(int toplamerkek) {
        this.toplamerkek = toplamerkek;
    }

    public int getToplamkadin() {
        return toplamkadin;
    }

    public void setToplamkadin(int toplamkadin) {
        this.toplamkadin = toplamkadin;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getYukseklisanserkek() {
        return yukseklisanserkek;
    }

    public void setYukseklisanserkek(int yukseklisanserkek) {
        this.yukseklisanserkek = yukseklisanserkek;
    }

    public int getYukseklisanskadin() {
        return yukseklisanskadin;
    }

    public void setYukseklisanskadin(int yukseklisanskadin) {
        this.yukseklisanskadin = yukseklisanskadin;
    }

    public int getYukseklisanstoplam() {
        return yukseklisanstoplam;
    }

    public void setYukseklisanstoplam(int yukseklisanstoplam) {
        this.yukseklisanstoplam = yukseklisanstoplam;
    }


}
