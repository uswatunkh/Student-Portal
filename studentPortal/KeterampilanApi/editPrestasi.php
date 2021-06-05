<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
   $idPrestasi 	= $_POST['idPrestasi'];

    require_once 'koneksi.php';

 //   $sql = "SELECT * FROM mahasiswa, periode WHERE mahasiswa.npm='$npm' ";
	$sql = "SELECT * FROM prestasi WHERE idPrestasi='".$idPrestasi."' ";

    $response = mysqli_query($connect, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
             
			 $h['idPrestasi']        = $row['idPrestasi'] ;
			 $h['namaLomba']        = $row['namaLomba'] ;
			 $h['tahun']        = $row['tahun'] ;
			 $h['juara']        = $row['juara'] ;
              $h['tingkat']        = $row['tingkat'] ;
             $h['jenis']        = $row['jenis'] ;
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