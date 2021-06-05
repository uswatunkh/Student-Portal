<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
   $idBahasa 	= $_POST['idBahasa'];

    require_once 'koneksi.php';

 //   $sql = "SELECT * FROM mahasiswa, periode WHERE mahasiswa.npm='$npm' ";
	$sql = "SELECT * FROM bahasainternational WHERE idBahasa='".$idBahasa."' ";

    $response = mysqli_query($connect, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
             
			 $h['idBahasa']        = $row['idBahasa'] ;
			 $h['periodeWisuda']        = $row['periodeWisuda'] ;
			 $h['tahunWisuda']        = $row['tahunWisuda'] ;
			 $h['namaBahasa']        = $row['namaBahasa'] ;
              $h['skor']        = $row['skor'] ;
             $h['tanggalTes']        = $row['tanggalTes'] ;
			 $h['scanBukti']        = $row['scanBukti'] ;
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