<?php 
	require_once 'koneksi.php';
	
	$npm = $_POST['npm'];
	$sql ="SELECT * FROM keterampilan where npm='$npm'";
	
	$result=mysqli_query($connect,$sql);
	$json = array();
	
	while($row = mysqli_fetch_assoc($result)){
		$json["data"][] = $row;
	}
	
	header('Content-Type:Application/json');
 
    echo json_encode($json);
	
?>