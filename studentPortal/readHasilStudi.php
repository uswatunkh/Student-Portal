
<?php
require_once 'connect.php';

	$npm = $_POST['npm'];
	$semester = $_POST['semester'];
 
	$sql="SELECT hasilstd.idMataKuliah, matakuliah.namaMK, matakuliah.sks, hasilstd.nilaiAngka, hasilstd.nilaiHuruf, hasilstd.totalNilai
		,daftarulg.ip, daftarulg.ipk
		FROM hasilstd
		INNER JOIN  matakuliah INNER JOIN  daftarulg ON hasilstd.idMataKuliah=matakuliah.idMataKuliah AND hasilstd.idDaftarUlang=daftarulg.idDaftarUlang
		AND hasilstd.npm='$npm' AND daftarulg.semester='$semester'";
		
	$result=mysqli_query($connect,$sql);
		$cek=mysqli_num_rows($result);
	if($cek==0){
		$pesan["success"] = "0";
		$pesan["message"] = "Data Masih Kosong";

		echo json_encode($pesan);
	}else{
		$sql="SELECT hasilstd.idMataKuliah, matakuliah.namaMK, matakuliah.sks, hasilstd.nilaiAngka, hasilstd.nilaiHuruf, hasilstd.totalNilai
		,daftarulg.ip, daftarulg.ipk
		FROM hasilstd
		INNER JOIN  matakuliah INNER JOIN  daftarulg ON hasilstd.idMataKuliah=matakuliah.idMataKuliah AND hasilstd.idDaftarUlang=daftarulg.idDaftarUlang
		AND hasilstd.npm='$npm' AND daftarulg.semester='$semester'";
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