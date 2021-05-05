<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
    $npm = $_POST['npm'];

    require_once 'connect.php';

 //   $sql = "SELECT * FROM mahasiswa, periode WHERE mahasiswa.npm='$npm' ";
	$sql = "SELECT * FROM jadwalkuliah,datadosen,ruang,matakuliah,mahasiswa WHERE mahasiswa.npm='$npm' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
             $h['npm']        = $row['npm'] ;
			 $h['namaDosen']        = $row['namaDosen'] ;
			 $h['namaRuang']        = $row['namaRuang'] ;
			 $h['namaMK']        = $row['namaMK'] ;
			 $h['jamke']        = $row['jamke'] ;
			 
             
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