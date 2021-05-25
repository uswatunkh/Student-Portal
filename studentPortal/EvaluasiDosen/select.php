<?php 
	require_once 'connect.php';
	
	
	$sql ="SELECT datadosen.idDosen,datadosen.namaDosen, matakuliah.namaMk FROM datadosen INNER JOIN matakuliah ON matakuliah.idMataKuliah=datadosen.idMataKuliah ";
	
	$result=mysqli_query($connect,$sql);
	$json = array();
	
	while($row = mysqli_fetch_assoc($result)){
		$json["data"][] = $row;
	}
	
	header('Content-Type:Application/json');
 
    echo json_encode($json);
	
?>