<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
   $idOrganisasi 	= $_POST['idOrganisasi'];

    require_once 'koneksi.php';

 //   $sql = "SELECT * FROM mahasiswa, periode WHERE mahasiswa.npm='$npm' ";
	$sql = "SELECT * FROM organisasi WHERE idOrganisasi='".$idOrganisasi."' ";

    $response = mysqli_query($connect, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
             
			 $h['idOrganisasi']        = $row['idOrganisasi'] ;
			 $h['namaOrganisasi']        = $row['namaOrganisasi'] ;
			 $h['tempat']        = $row['tempat'] ;
			 $h['tahunMasuk']        = $row['tahunMasuk'] ;
              $h['tahunKeluar']        = $row['tahunKeluar'] ;
             $h['jabatan']        = $row['jabatan'] ;
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