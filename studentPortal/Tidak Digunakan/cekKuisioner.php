<?php 
	require_once 'connect.php';
	
	$npm = $_POST['npm'];
	$idPengajaran = $_POST['idPengajaran'];
	//$namaMK = $_POST['namaMK'];

	$sql ="SELECT pengajaran.idPengajaran, datadosen.idDosen,datadosen.namaDosen, matakuliah.namaMK FROM jawaban INNER JOIN pengajaran ON pengajaran.idPengajaran = jawaban.idPengajaran INNER JOIN datadosen ON datadosen.idDosen=pengajaran.idDosen INNER JOIN matakuliah ON matakuliah.idMataKuliah=pengajaran.idMataKuliah WHERE jawaban.npm=".$npm." && jawaban.idPengajaran=".$idPengajaran ."" ;

	//&& matakuliah.namaMK='".$namaMK."'";
	
	// $sql ="SELECT jawaban.*, pengajaran.idMataKuliah, matakuliah.namaMK FROM jawabanINNER JOIN pengajaran ON pengajaran.idPengajaran = jawaban.idPengajaran INNER JOIN matakuliah ON matakuliah.idMataKuliah=pengajaran.idMataKuliah"; WHERE npm=".$npm." && idPengajaran=".$idPengajaran .""

	$result=mysqli_query($connect,$sql);
	// while($row = mysqli_fetch_assoc($result)){
	// 	$json["data"][] = $row;
	// 	print_r($row);	
	// }
	header('Content-Type:Application/json');
	if(mysqli_num_rows($result) != null){
		$json = array("success" => 0, "message" => "Kuisioner sudah terisi");
	    echo json_encode($json);
	}else{
		$json = array("success" => 1, "message" => "Kuisioner belum terisi");
		echo json_encode($json);
	}

?>