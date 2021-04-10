package com.example.studentportal;

public class DaftarUlang_list {
    private String periodeAkademik;
    private String ukt;
    private String status;
    private String cetakKrs;



    public DaftarUlang_list(  String periodeAkademik, String ukt, String status, String cetakKrs) {

        this.periodeAkademik = periodeAkademik;
        this.ukt = ukt;
        this.status = status;
        this.cetakKrs = cetakKrs;

    }

    public String getPeriodeAkademik() {
        return periodeAkademik;
    }

    public void setPeriodeAkademik(String periodeAkademik) {
        this.periodeAkademik = periodeAkademik;
    }

    public String getUkt() {
        return ukt;
    }

    public void setUkt(String ukt) {
        this.ukt = ukt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCetakKrs() {
        return cetakKrs;
    }

    public void setCetakKrs(String cetakKrs) {
        this.cetakKrs = cetakKrs;
    }
}
