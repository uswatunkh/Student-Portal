<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $npm 	= $_POST['npm'];
	$namaOrganisasi 	= $_POST['namaOrganisasi'];
	$tempat = $_POST['tempat'];
	$tahunMasuk = $_POST['tahunMasuk'];
	$tahunKeluar = $_POST['tahunKeluar'];
	$jabatan = $_POST['jabatan'];
	$verifikasi = "Belum Diverifikasi";
	$scanBukti = "scanBukti_Organisasi/$npm.$namaOrganisasi.pdf";
	$encodedPDF = $_POST['PDF'];
	
	//$pdfLocation = "documents/$pdfTitle.pdf";
	
	

    require_once 'koneksi.php';
		
		$sql = "INSERT INTO organisasi (idOrganisasi,npm,namaOrganisasi,tempat,tahunMasuk,tahunKeluar,jabatan,verifikasi,scanBukti) 
		VALUES(0,'".$npm."','".$namaOrganisasi."','".$tempat."','".$tahunMasuk."','".$tahunKeluar."','".$jabatan."','".$verifikasi."','".$scanBukti."') ";
		
			
		if(mysqli_query($connect, $sql)) {
			file_put_contents($scanBukti, base64_decode($encodedPDF));
			

          $result["success"] = "1";
          $result["message"] = "success";

          echo json_encode($result);
          mysqli_close($connect);
			}
			
  }

else{

   $result["success"] = "0";
   $result["message"] = "error!";
   echo json_encode($result);

   mysqli_close($conn);
}

?>


