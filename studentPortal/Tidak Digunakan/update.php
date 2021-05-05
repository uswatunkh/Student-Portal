<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

   $idKeterampilan 	= $_POST['idKeterampilan'];
	$namaKeterampilan 	= $_POST['namaKeterampilan'];
	$jenis = $_POST['jenis'];
	$tingkat = $_POST['tingkat'];
	$scanBukti = "documents/$npm.pdf";
	$encodedPDF = $_POST['PDF'];
	//$pdfLocation = "documents/$pdfTitle.pdf";
	

    require_once 'koneksi.php';
		
		$sql = "UPDATE keterampilan SET idKeterampilan='".$idKeterampilan."',namaKeterampilan='".$namaKeterampilan."', jenis='".$jenis."', tingkat='".$tingkat."',scanBukti='".$scanBukti."'  WHERE idKeterampilan='".$idKeterampilan."' ";
		file_put_contents($pdfLocation, base64_decode($encodedPDF));
		if(mysqli_query($connect, $sql)) {

          $result["success"] = "1";
          $result["message"] = "success update";

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


