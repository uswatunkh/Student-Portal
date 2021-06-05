<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $npm 	= $_POST['npm'];
	$namaKeterampilan 	= $_POST['namaKeterampilan'];
	$jenis = $_POST['jenis'];
	$tingkat = $_POST['tingkat'];
	$verifikasi = "Belum Diverifikasi";
	
	$scanBukti = "documents/$npm.$namaKeterampilan.png"  ;
	$encodedPDF = $_POST['PDF'];
	
	//$pdfLocation = "documents/$pdfTitle.pdf";
	
	

    require_once 'koneksi.php';
		
		$sql = "INSERT INTO keterampilan (idKeterampilan,npm,namaKeterampilan,jenis,tingkat,verifikasi,scanBukti) 
		VALUES(0,'".$npm."','".$namaKeterampilan."','".$jenis."','".$tingkat."','".$verifikasi."','".$scanBukti."') ";
		
			
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


