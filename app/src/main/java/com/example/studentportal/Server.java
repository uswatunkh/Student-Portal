package com.example.studentportal;

public class Server {
    /* Jika IP 10.0.2.2, itu adalah IP Address localhost EMULATOR ANDROID STUDIO,
    Ganti IP Address tersebut dengan IP Laptop Anda. Apabila di RUN di HP. HP dan Laptop harus 1 jaringan */
    public static final String URL = "http://192.168.1.44/studentPortal/";
    public static final String URLKeterampilan = "http://192.168.1.44/studentPortal/KeterampilanApi/";
    public static final String URLEvaluasiDosen = "http://192.168.1.44/studentPortal/EvaluasiDosen/";
    public static final String UPLOAD_URL = "http://192.168.1.44/AndroidUploadImage/Keterampilan/upKeterampilan.php";
    public static final String IMAGES_URL = "http://192.168.1.44/AndroidUploadImage/getImages.php";
}