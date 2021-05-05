<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
    $idDosen = $_POST['idDosen'];

    $conn = mysqli_connect("localhost", "root", "", "databasestudent");

 //   $sql = "SELECT * FROM mahasiswa, periode WHERE mahasiswa.npm='$npm' ";
	$sql = "SELECT * FROM datadosen WHERE idDosen='$idDosen' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
             $h['idDosen']        = $row['idDosen'] ;
		
             
 
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