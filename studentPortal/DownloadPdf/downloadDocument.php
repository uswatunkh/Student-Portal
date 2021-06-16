<?php

	include 'connect.php';

	//$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);

	//$receivedSn = $_POST["SN"];
	$receivedSn = 16;

	$sqlQuery = "SELECT * FROM `kalenderakademik` WHERE sn = '$receivedSn'";

	$result = mysqli_query($connect, $sqlQuery);

	$pdfDetails = NULL;

	while ($row = mysqli_fetch_array($result)) {
		
		$pdfDetails["pdfSn"] = $row[0];
		$pdfDetails["pdfTitle"] = $row[1];

		$pdfLocation = $row[2];

		$pdfFile = file_get_contents($pdfLocation);

		$pdfDetails["encodedPDF"] = base64_encode($pdfFile);

	}

	mysqli_close($connect);

	print(json_encode($pdfDetails));



?>