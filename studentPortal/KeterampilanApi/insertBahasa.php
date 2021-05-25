<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $npm 	= $_POST['npm'];
	$periodeWisuda 	= "2019/2020";
	$tahunWisuda = "2022";
	$namaBahasa = $_POST['namaBahasa'];
	$skor = $_POST['skor'];
	$tanggalTes = $_POST['tanggalTes'];
	$verifikasi = "Belum Diverifikasi";
	$scanBukti = "scanBukti_Bahasa/$npm.$namaBahasa.pdf";
	$encodedPDF = $_POST['PDF'];
	
	//$pdfLocation = "documents/$pdfTitle.pdf";
	
	

    require_once 'koneksi.php';
		
		$sql = "INSERT INTO bahasainternational (idBahasa,npm,periodeWisuda,tahunWisuda,namaBahasa,skor,tanggalTes,verifikasi,scanBukti) 
		VALUES(0,'".$npm."','".$periodeWisuda."','".$tahunWisuda."','".$namaBahasa."','".$skor."','".$tanggalTes."','".$verifikasi."','".$scanBukti."') ";
		
			
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


