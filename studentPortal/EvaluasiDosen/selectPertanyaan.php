<?php 
	require_once 'connect.php';
	
	 //$idDosen = $_POST['idDosen'];
	
	$sql ="Select * FROM pertanyaan ";
	
	$result=mysqli_query($connect,$sql);
	$json = array();
	
	while($row = mysqli_fetch_assoc($result)){
		$json["data"][] = $row;
	}
	
	header('Content-Type:Application/json');
 
    echo json_encode($json);
	
?>