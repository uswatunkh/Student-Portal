package com.example.studentportal.Fitur;

public class DaftarUlang_list {
    String idNomor;
    private String periodeAkademik;
    private String ukt;
    private String status;
    private String cetakKrs;



    public DaftarUlang_list( String idNomor, String periodeAkademik, String ukt, String status) {
        this.idNomor=idNomor;
        this.periodeAkademik = periodeAkademik;
        this.ukt = ukt;
        this.status = status;

    }

    public String getIdNomor() {
        return idNomor;
    }

    public void setIdNomor(String idNomor) {
        this.idNomor = idNomor;
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
