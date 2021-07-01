<?php 
	require_once 'connect.php';
	
	$npm = $_POST['npm'];
	$sql ="SELECT pengajaran.idPengajaran,pengajaran.statusKuisioner, datadosen.idDosen,datadosen.namaDosen, matakuliah.namaMk FROM jadwalkuliah INNER JOIN pengajaran INNER JOIN datadosen ON datadosen.idDosen=pengajaran.idDosen INNER JOIN matakuliah ON matakuliah.idMataKuliah=pengajaran.idMataKuliah INNER JOIN periode ON periode.idPeriode=jadwalkuliah.idPeriode Where periode.tanggalSelesaiKuliah < NOW() And periode.tanggalSelesaiPeriode > NOW() AND jadwalkuliah.npm='.$npm.'";

	// $sql ="SELECT pengajaran.idPengajaran, datadosen.idDosen,datadosen.namaDosen, matakuliah.namaMk FROM jadwalkuliah INNER JOIN pengajaran INNER JOIN datadosen ON datadosen.idDosen=pengajaran.idDosen INNER JOIN matakuliah ON matakuliah.idMataKuliah=pengajaran.idMataKuliah INNER JOIN periode ON periode.idPeriode=jadwalkuliah.idPeriode Where periode.tanggalMulaiPeriode < NOW() And periode.tanggalSelesaiPeriode > NOW() AND pengajaran.statusKuisioner='' AND jadwalkuliah.npm='.$npm.'";
	
	$result=mysqli_query($connect,$sql);
	$json = array();
	
	while($row = mysqli_fetch_assoc($result)){
		$json["data"][] = $row;

	}
	
	header('Content-Type:Application/json');
 
    echo json_encode($json);
	
?>