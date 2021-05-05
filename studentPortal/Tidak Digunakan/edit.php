<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
   $idKeterampilan 	= $_POST['idKeterampilan'];

    require_once 'koneksi.php';

 //   $sql = "SELECT * FROM mahasiswa, periode WHERE mahasiswa.npm='$npm' ";
	$sql = "SELECT * FROM keterampilan WHERE idKeterampilan='".$idKeterampilan."' ";

    $response = mysqli_query($connect, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
             
			 $h['idKeterampilan']        = $row['idKeterampilan'] ;
			 $h['namaKeterampilan']        = $row['namaKeterampilan'] ;
			 $h['jenis']        = $row['jenis'] ;
			 $h['tingkat']        = $row['tingkat'] ;
			 $h['scanBukti']        = $row['scanBukti'] ;
			
             
 
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