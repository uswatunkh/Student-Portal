package com.example.studentportal;

public class RekapPresensi_list{
    String Semester;
    private String idRekapPresensi;
    private String hadir;
    private String izin;
    private String sakit;
    private String kosong;

    public RekapPresensi_list() {
    }


    public RekapPresensi_list (String semester, String idRekapPresensi, String hadir, String izin,String sakit, String kosong) {
        Semester = semester;
        this.idRekapPresensi = idRekapPresensi;
        this.hadir = hadir;
        this.izin = izin;
        this.sakit = sakit;
        this.kosong = kosong;

    }


    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getIdRekapPresensi() {
        return idRekapPresensi;
    }

    public void setIdRekapPresensi(String idRekapPresensi) {
        this.idRekapPresensi = idRekapPresensi;
    }

    public String getHadir() {
        return hadir;
    }

    public void setHadir(String hadir) {
        this.hadir = hadir;
    }

    public String getIzin() {
        return izin;
    }

    public void setIzin(String izin) {
        this.izin = izin;
    }

    public String getSakit() {
        return sakit;
    }

    public void setSakit(String sakit) {
        this.sakit = sakit;
    }

    public String getKosong() {
        return kosong;
    }

    public void setKosong(String kosong) {
        this.kosong = kosong;
    }
}