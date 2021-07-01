
<?php
require_once 'connect.php';

	$npm = $_POST['npm'];
	//$semester = $_POST['semester'];
	
	$sql="SELECT rekappresensi.idRekapPresensi, rekappresensi.hadir, rekappresensi.izin,
		rekappresensi.sakit,rekappresensi.kosong, daftarulg.semester
	 	FROM rekappresensi INNER JOIN daftarulg ON rekappresensi.idDaftarUlang=daftarulg.idDaftarUlang
		WHERE daftarulg.npm='$npm' ";
		
	$result=mysqli_query($connect,$sql);
		$cek=mysqli_num_rows($result);
	if($cek==0){
		$pesan["success"] = "0";
		$pesan["message"] = "Data Masih Kosong";

		echo json_encode($pesan);
	}else{
		$sql="SELECT rekappresensi.idRekapPresensi, rekappresensi.hadir, rekappresensi.izin,
		rekappresensi.sakit,rekappresensi.kosong, daftarulg.semester
	 	FROM rekappresensi INNER JOIN daftarulg ON rekappresensi.idDaftarUlang=daftarulg.idDaftarUlang
		WHERE daftarulg.npm='$npm' ";
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