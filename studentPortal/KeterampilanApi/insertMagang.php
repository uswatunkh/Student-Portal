<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $npm 	= $_POST['npm'];
	$judul 	= $_POST['judul'];
	$tempat = $_POST['tempat'];
	$provinsi = $_POST['provinsi'];
	$kota = $_POST['kota'];
	$tanggalmulaiMagang = $_POST['tanggalmulaiMagang'];
	$tanggalselesaiMagang = $_POST['tanggalselesaiMagang'];
	$ringkasan = $_POST['ringkasan'];
	$file = $_POST['file'];
	$scanBukti = "scanBukti_Magang/$npm.$file";
	$encodedPDF = $_POST['PDF'];
	$uploadLaporan = "laporan_Magang/$npm.$judul.pdf";
	$encodedPDFLaporan = $_POST['PDFLaporan'];
	
	//$pdfLocation = "documents/$pdfTitle.pdf";
	
	$verifikasi = "Belum Diverifikasi";
	
	

    require_once 'koneksi.php';
		
		$sql = "INSERT INTO magang (idMagang,npm,judul,tempat,provinsi,kota,tanggalmulaiMagang,tanggalselesaiMagang,ringkasan,scanBukti,uploadLaporan,verifikasi) 
		VALUES(0,'".$npm."','".$judul."','".$tempat."','".$provinsi."','".$kota."','".$tanggalmulaiMagang."','".$tanggalselesaiMagang."','".$ringkasan."'
		,'".$scanBukti."','".$uploadLaporan."','".$verifikasi."') ";
		
			
		if(mysqli_query($connect, $sql)) {
			file_put_contents($scanBukti, base64_decode($encodedPDF));
			file_put_contents($uploadLaporan, base64_decode($encodedPDFLaporan));
			

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


