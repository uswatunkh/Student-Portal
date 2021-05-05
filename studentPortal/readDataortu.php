<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
    $npm = $_POST['npm'];

    require_once 'connect.php';

    $sql = "SELECT * FROM orangtua WHERE npm='$npm' ";

    $response = mysqli_query($connect, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
             $h['nikAyah']        = $row['nikAyah'];
			 $h['namaAyah']        = $row['namaAyah'];
			 $h['tglLahirAyah']        = $row['tglLahirAyah'];
			 $h['pendidikanAyah']        = $row['pendidikanAyah'];
			 $h['pekerjaanAyah']        = $row['pekerjaanAyah'] ;
			 $h['nipAyah']        = $row['nipAyah'];
			 $h['pangkatAyah']        = $row['pangkatAyah'];
			 $h['penghasilanAyah']        = $row['penghasilanAyah'];
			 $h['instansiAyah']        = $row['instansiAyah'] ;
			 $h['nikIbu']        = $row['nikIbu'];
			 $h['namaIbu']        = $row['namaIbu'];
			 $h['tglLahirIbu']        = $row['tglLahirIbu'];
			 $h['pendidikanIbu']        = $row['pendidikanIbu'];
			 $h['pekerjaanIbu']        = $row['pekerjaanIbu'] ;
			 $h['nipIbu']        = $row['nipIbu'];
			 $h['pangkatIbu']        = $row['pangkatIbu'];
			 $h['penghasilanIbu']        = $row['penghasilanIbu'];
			 $h['instansiIbu']        = $row['instansiIbu'] ;
			 $h['nikWali']        = $row['nikWali'];
			 $h['namaWali']        = $row['namaWali'];
			 $h['tglLahirWali']        = $row['tglLahirWali'];
			 $h['pendidikanWali']        = $row['pendidikanWali'];
			 $h['pekerjaanWali']        = $row['pekerjaanWali'] ;
			 $h['nipWali']        = $row['nipWali'];
			 $h['pangkatWali']        = $row['pangkatWali'];
			 $h['penghasilanWali']        = $row['penghasilanWali'];
			 $h['instansiWali']        = $row['instansiWali'] ;
             
 
             array_push($result["read"], $h);
 
             $result["success"] = "1";
             echo json_encode($result);
        }
 
   }
 
 }else {
 
     $result["success"] = "0";
     $result["message"] = "Error!";
     echo json_encode($result);
 
     mysqli_close($connect);
 }
 
 ?>