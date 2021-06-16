<?php
require_once 'connect.php';

$npm = 12;
 
$sql = "SELECT   daftarulg.semester,periode.tanggalMulaiPeriode, periode.tanggalSelesaiPeriode FROM daftarulg INNER JOIN periode ON daftarulg.idPeriode=periode.idPeriode Where npm='$npm'";

$result=mysqli_query($connect,$sql);
 
$data=array();
while($row=mysqli_fetch_assoc($result)){
	 $h['semester']        = $row['semester'] ;
	 $h['tanggalMulaiPeriode']        = $row['tanggalMulaiPeriode'] ;
             $h['tanggalSelesaiPeriode']        = $row['tanggalSelesaiPeriode'] ;

             $paymentDate = date('Y-m-d');
			$paymentDate=date('Y-m-d', strtotime($paymentDate));
			//echo $paymentDate; // echos today! 
			$contractDateBegin = date('Y-m-d', strtotime($h['tanggalMulaiPeriode']));
			$contractDateEnd = date('Y-m-d', strtotime($h['tanggalSelesaiPeriode']));
			if (($paymentDate >= $contractDateBegin) && ($paymentDate <= $contractDateEnd)){
    			///echo "is between";
    			// $data["data"][]=$h;
    			// echo json_encode($data);
    			$sql="SELECT jadwalkuliah.idJadwal,jadwalkuliah.jamke,jadwalkuliah.hari,  ruang.namaRuang,datadosen.namaDosen, matakuliah.namaMK, matakuliah.idMataKuliah
		FROM jadwalkuliah
		INNER JOIN  ruang INNER JOIN pengajaran INNER JOIN datadosen INNER JOIN matakuliah ON jadwalkuliah.idPengajaran=pengajaran.idPengajaran AND jadwalkuliah.idRuang=ruang.idRuang AND pengajaran.idDosen=datadosen.idDosen  AND pengajaran.idMataKuliah=matakuliah.idMataKuliah 
		AND jadwalkuliah.npm='$npm' AND jadwalkuliah.hari='senin'";
		
	$result=mysqli_query($connect,$sql);
		$cek=mysqli_num_rows($result);
	if($cek==0){
		$pesan["success"] = "0";
		$pesan["message"] = "Data Masih Kosong";

		echo json_encode($pesan);
	}else{
		$sql="SELECT jadwalkuliah.idJadwal,jadwalkuliah.jamke,jadwalkuliah.hari,  ruang.namaRuang,datadosen.namaDosen, matakuliah.namaMK
		FROM jadwalkuliah
		INNER JOIN  ruang INNER JOIN pengajaran INNER JOIN datadosen INNER JOIN matakuliah ON jadwalkuliah.idPengajaran=pengajaran.idPengajaran AND jadwalkuliah.idRuang=ruang.idRuang AND pengajaran.idDosen=datadosen.idDosen  AND pengajaran.idMataKuliah=matakuliah.idMataKuliah 
		AND jadwalkuliah.npm='$npm' AND jadwalkuliah.hari='senin' ";
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
			}else{
    			//echo "NO GO!"; 
    			$pesan["success"] = "2";
				$pesan["message"] = "Data Belum siap"; 
				echo json_encode($pesan);
				}

//$data["data"][]=$h;
 
}
 
 
 
 
 header('Content-Type:Application/json');
 
 //echo json_encode($data);
 
 
 ?>