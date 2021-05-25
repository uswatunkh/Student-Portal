package com.example.studentportal;

public class PengumumanItem {
    String  title,body, tanggalPengumuman;




    public PengumumanItem( String title,String body, String tanggalPengumuman) {

        this.title = title;
        this.body = body;
        this.tanggalPengumuman = tanggalPengumuman;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTanggalPengumuman() {
        return tanggalPengumuman;
    }

    public void setTanggalPengumuman(String tanggalPengumuman) {
        this.tanggalPengumuman = tanggalPengumuman;
    }
}
