<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
   $idMagang 	= $_POST['idMagang'];

    require_once 'koneksi.php';

 //   $sql = "SELECT * FROM mahasiswa, periode WHERE mahasiswa.npm='$npm' ";
	$sql = "SELECT * FROM magang WHERE idMagang='".$idMagang."' ";

    $response = mysqli_query($connect, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
             
			 $h['idMagang']        = $row['idMagang'] ;
			 $h['judul']        = $row['judul'] ;
			 $h['tempat']        = $row['tempat'] ;
			 $h['provinsi']        = $row['provinsi'] ;
			$h['kota']        = $row['kota'] ;
            $h['tanggalmulaiMagang']        = $row['tanggalmulaiMagang'] ;
             $h['tanggalselesaiMagang']        = $row['tanggalselesaiMagang'] ;
             $h['ringkasan']        = $row['ringkasan'] ;
             $h['scanBukti']        = $row['scanBukti'] ;
             $h['uploadLaporan']        = $row['uploadLaporan'] ;
            $h['verifikasi']        = $row['verifikasi'] ;
             
 
             array_push($result["read"], $h);
 
             $result["success"] = "1";
             echo json_encode($result);
        }
 
   }
 
 }else {
 
     $result["success"] = "0";
     $result["message"] = "Error!";
     echo json_encode($result);
 
     mysqli_close($conn);
 }
 
 ?>