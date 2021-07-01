<?php 
	require_once 'connect.php';
	
	//$npm = $_POST['npm'];
	// $sql ="SELECT DISTINCT pengajaran.idPengajaran, datadosen.idDosen,datadosen.namaDosen, matakuliah.namaMK FROM jawaban INNER JOIN pengajaran ON pengajaran.idPengajaran = jawaban.idPengajaran INNER JOIN datadosen ON datadosen.idDosen=pengajaran.idDosen INNER JOIN matakuliah ON matakuliah.idMataKuliah=pengajaran.idMataKuliah";
	// $sql ="SELECT DISTINCT pengajaran.idPengajaran, datadosen.idDosen,datadosen.namaDosen, matakuliah.namaMK FROM jawaban INNER JOIN pengajaran ON pengajaran.idPengajaran != jawaban.idPengajaran INNER JOIN datadosen ON datadosen.idDosen=pengajaran.idDosen INNER JOIN matakuliah ON matakuliah.idMataKuliah=pengajaran.idMataKuliah ";
	$sql ="SELECT DISTINCT pengajaran.idPengajaran FROM `pengajaran` cross join jawaban where pengajaran.idPengajaran=jawaban.idPengajaran ";
	$sql2 ="SELECT DISTINCT pengajaran.idPengajaran FROM `pengajaran` cross join jawaban where pengajaran.idPengajaran!=jawaban.idPengajaran";

	
	$sql3 ="SELECT  pengajaran.idPengajaran, datadosen.idDosen,datadosen.namaDosen, matakuliah.namaMk FROM jadwalkuliah INNER JOIN pengajaran INNER JOIN datadosen ON datadosen.idDosen=pengajaran.idDosen INNER JOIN matakuliah ON matakuliah.idMataKuliah=pengajaran.idMataKuliah INNER JOIN periode ON periode.idPeriode=jadwalkuliah.idPeriode Where periode.tanggalMulaiPeriode < NOW() And periode.tanggalSelesaiPeriode > NOW() ";
	
	$result=mysqli_query($connect,$sql);
	$result2=mysqli_query($connect,$sql2);
	$result3=mysqli_query($connect,$sql3);
	$json = array();
	$json2 = array();
	$json3 = array();
	while($row = mysqli_fetch_assoc($result)){
		$json["data"][] = $row;

	}
	while($row2 = mysqli_fetch_assoc($result2)){
		$json2["data"][] = $row2;

	}
	while($row3 = mysqli_fetch_assoc($result3)){
		$json3["data"][] = $row3;

	}
	
	header('Content-Type:Application/json');
 
   // echo json_encode($json);
    if(mysqli_num_rows($result) == null){
		//$json = array("success" => 0, "message" => "Kuisioner sudah terisi");
	    echo json_encode($json);
	    echo json_encode($json3);

	}else {
		
		echo json_encode($json2);
	}

	
?>