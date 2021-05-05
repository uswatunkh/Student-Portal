
<?php
require_once 'connect.php';

	$npm = $_POST['npm'];;
 
	$sql="SELECT jadwalkuliah.idJadwal,jadwalkuliah.jamke,jadwalkuliah.hari, datadosen.namaDosen, ruang.namaRuang, matakuliah.namaMK
	FROM jadwalkuliah
	INNER JOIN datadosen INNER JOIN ruang INNER JOIN matakuliah ON jadwalkuliah.idDosen=datadosen.idDosen AND jadwalkuliah.idRuang=ruang.idRuang AND 
	jadwalkuliah.idMataKuliah=matakuliah.idMataKuliah AND jadwalkuliah.npm='$npm' AND jadwalkuliah.hari='sabtu'";
		
	$result=mysqli_query($connect,$sql);
		$cek=mysqli_num_rows($result);
	if($cek==0){
		$pesan["success"] = "0";
		$pesan["message"] = "Data Masih Kosong";

		echo json_encode($pesan);
	}else{
		$sql="SELECT jadwalkuliah.idJadwal,jadwalkuliah.jamke,jadwalkuliah.hari, datadosen.namaDosen, ruang.namaRuang, matakuliah.namaMK
	FROM jadwalkuliah
	INNER JOIN datadosen INNER JOIN ruang INNER JOIN matakuliah ON jadwalkuliah.idDosen=datadosen.idDosen AND jadwalkuliah.idRuang=ruang.idRuang AND 
	jadwalkuliah.idMataKuliah=matakuliah.idMataKuliah AND jadwalkuliah.npm='$npm' AND jadwalkuliah.hari='sabtu' ";
		$result=mysqli_query($connect,$sql);
		$data=array();
		while($row=mysqli_fetch_assoc($result)){
		$data["data"][]=$row;
 
		}
		header('Content-Type:Application/json');
		$data["success"] = "1";
	    echo json_encode($data);
	//echo "data found";
	}
 
 ?>