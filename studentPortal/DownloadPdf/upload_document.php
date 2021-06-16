<?php
	
	include 'connect.php';

	//$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);

	$encodedPDF = $_POST['PDF'];


	$pdfTitle = "Kalender_Akademik_PNM";
	$pdfLocation = "documents/$pdfTitle.pdf";

	$sqlQuery = "INSERT INTO `documents`(`title`, `location`) VALUES ('$pdfTitle', '$pdfLocation')";


	if(mysqli_query($connect, $sqlQuery)){


		file_put_contents($pdfLocation, base64_decode($encodedPDF));

		$result["status"] = TRUE;
		$result["remarks"] = "document uploaded successfully";

	}else{

		$result["status"] = FALSE;
		$result["remarks"] = "document uploading Failed";

	}

	mysqli_close($connect);

	print(json_encode($result));

?>