<?php 
	require_once 'connect.php';
	
	$idDosen = $_POST['idDosen'];
	
	$sql ="Select evaluasidosen.idEvdos, kuisioner.pertanyaan FROM evaluasidosen INNER JOIN kuisioner ON evaluasidosen.idKuisioner=kuisioner.idKuisioner AND idDosen='$idDosen' 
	AND kuisioner.kategori='Sikap Dosen saat memberikan Tugas dan Portofolio'";
	
	$result=mysqli_query($connect,$sql);
	$json = array();
	
	while($row = mysqli_fetch_assoc($result)){
		$json["data"][] = $row;
	}
	
	header('Content-Type:Application/json');
 
    echo json_encode($json);
	
?>