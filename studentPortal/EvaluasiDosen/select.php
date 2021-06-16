<?php 
	require_once 'connect.php';
	
	
	$sql ="SELECT pengajaran.idPengajaran, datadosen.idDosen,datadosen.namaDosen, matakuliah.namaMk FROM pengajaran INNER JOIN datadosen ON datadosen.idDosen=pengajaran.idDosen INNER JOIN matakuliah ON matakuliah.idMataKuliah=pengajaran.idMataKuliah ";
	
	$result=mysqli_query($connect,$sql);
	$json = array();
	
	while($row = mysqli_fetch_assoc($result)){
		$json["data"][] = $row;
	}
	
	header('Content-Type:Application/json');
 
    echo json_encode($json);
	
?>