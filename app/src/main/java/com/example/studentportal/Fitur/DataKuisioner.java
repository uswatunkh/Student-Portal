package com.example.studentportal.Fitur;

public class DataKuisioner {
    int idNomor, nomor;
    private String idEvdos;
    private String pertanyaan;
    private String a;
    private String b;
    private String c;
    private String d;

    public DataKuisioner( ) {



    }

    public DataKuisioner(int idNomor, String idEvdos,String pertanyaan, String a, String b ,String c,String d) {
        this.idNomor = idNomor;
        this.idEvdos = idEvdos;
        this.pertanyaan = pertanyaan;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;



    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    public int getIdNomor() {
        return idNomor;
    }

    public void setIdNomor(int idNomor) {
        this.idNomor = idNomor;
    }

    public String getIdEvdos() {
        return idEvdos;
    }

    public void setIdEvdos(String idEvdos) {
        this.idEvdos = idEvdos;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
