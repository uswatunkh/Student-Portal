
<?php
require_once 'connect.php';

	$npm = $_POST['npm'];;
 
	$sql="SELECT jadwalkuliah.idJadwal,jadwalkuliah.jamke,jadwalkuliah.hari,  ruang.namaRuang,datadosen.namaDosen, matakuliah.namaMK, matakuliah.idMataKuliah
		FROM jadwalkuliah INNER JOIN  ruang INNER JOIN pengajaran INNER JOIN datadosen INNER JOIN matakuliah ON jadwalkuliah.idPengajaran=pengajaran.idPengajaran AND jadwalkuliah.idRuang=ruang.idRuang AND pengajaran.idDosen=datadosen.idDosen  AND pengajaran.idMataKuliah=matakuliah.idMataKuliah 
		AND jadwalkuliah.npm='$npm' AND jadwalkuliah.hari='jumat'  
		INNER JOIN periode ON periode.idPeriode=jadwalkuliah.idPeriode Where periode.tanggalMulaiPeriode < NOW() And periode.tanggalSelesaiPeriode > NOW()";
		
	$result=mysqli_query($connect,$sql);
		$cek=mysqli_num_rows($result);
	if($cek==0){
		$pesan["success"] = "0";
		$pesan["message"] = "Data Masih Kosong";

		echo json_encode($pesan);
	}else{
		$sql="SELECT jadwalkuliah.idJadwal,jadwalkuliah.jamke,jadwalkuliah.hari,  ruang.namaRuang,datadosen.namaDosen, matakuliah.namaMK, matakuliah.idMataKuliah
		FROM jadwalkuliah INNER JOIN  ruang INNER JOIN pengajaran INNER JOIN datadosen INNER JOIN matakuliah ON jadwalkuliah.idPengajaran=pengajaran.idPengajaran AND jadwalkuliah.idRuang=ruang.idRuang AND pengajaran.idDosen=datadosen.idDosen  AND pengajaran.idMataKuliah=matakuliah.idMataKuliah 
		AND jadwalkuliah.npm='$npm' AND jadwalkuliah.hari='jumat'  
		INNER JOIN periode ON periode.idPeriode=jadwalkuliah.idPeriode Where periode.tanggalMulaiPeriode < NOW() And periode.tanggalSelesaiPeriode > NOW()";
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