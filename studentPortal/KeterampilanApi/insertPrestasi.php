<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $npm 	= $_POST['npm'];
	$namaLomba 	= $_POST['namaLomba'];
	$tahun = $_POST['tahun'];
	$juara = $_POST['juara'];
	$tingkat = $_POST['tingkat'];
	$jenis = $_POST['jenis'];
	$verifikasi = "Belum Diverifikasi";
	$scanBukti = "scanBukti_Prestasi/$npm.$namaLomba.png";
	$encodedPDF = $_POST['PDF'];
	
	//$pdfLocation = "documents/$pdfTitle.pdf";
	
	

    require_once 'koneksi.php';
		
		$sql = "INSERT INTO prestasi (idPrestasi,npm,namaLomba,tahun,juara,tingkat,jenis,verifikasi,scanBukti) 
		VALUES(0,'".$npm."','".$namaLomba."','".$tahun."','".$juara."','".$tingkat."','".$jenis."','".$verifikasi."','".$scanBukti."') ";
		
			
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


