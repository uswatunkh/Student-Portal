
<?php
require_once 'connect.php';

	$npm = $_POST['npm'];
	//$semester = $_POST['semester'];
	$idRekap = $_POST['idRekap'];

	$sql="SELECT presensi.mingguKe, presensi.tanggalPresensi, matakuliah.namaMK, presensi.ket
		 FROM presensi
		 INNER JOIN jadwalkuliah INNER JOIN rekappresensi  ON presensi.idJadwal=jadwalkuliah.idJadwal 
		 CROSS JOIN pengajaran ON jadwalkuliah.idPengajaran=pengajaran.idPengajaran CROSS JOIN matakuliah ON pengajaran.idMataKuliah=matakuliah.idMataKuliah AND rekappresensi.idRekapPresensi=presensi.idRekapPresensi
		AND rekappresensi.idRekapPresensi='$idRekap'";

	
	// $sql="SELECT presensi.mingguKe, presensi.tanggalPresensi, matakuliah.namaMK, presensi.ket
	// 	 FROM presensi
	// 	 INNER JOIN jadwalkuliah INNER JOIN daftarulg  ON presensi.idJadwal=jadwalkuliah.idJadwal 
	// 	 CROSS JOIN pengajaran ON jadwalkuliah.idPengajaran=pengajaran.idPengajaran CROSS JOIN matakuliah ON pengajaran.idMataKuliah=matakuliah.idMataKuliah AND presensi.idDaftarUlang=daftarulg.idDaftarUlang
	// 	AND daftarulg.npm='$npm' AND daftarulg.semester='$semester'";
		
	$result=mysqli_query($connect,$sql);
		$cek=mysqli_num_rows($result);
	if($cek==0){
		$pesan["success"] = "0";
		$pesan["message"] = "Data Masih Kosong";

		echo json_encode($pesan);
	}else{
		$sql="SELECT presensi.mingguKe, presensi.tanggalPresensi, matakuliah.namaMK, presensi.ket
		 FROM presensi
		 INNER JOIN jadwalkuliah INNER JOIN rekappresensi  ON presensi.idJadwal=jadwalkuliah.idJadwal 
		 CROSS JOIN pengajaran ON jadwalkuliah.idPengajaran=pengajaran.idPengajaran CROSS JOIN matakuliah ON pengajaran.idMataKuliah=matakuliah.idMataKuliah AND rekappresensi.idRekapPresensi=presensi.idRekapPresensi
		AND rekappresensi.idRekapPresensi='$idRekap'";
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