
<?php
require_once 'connect.php';

	$npm = $_POST['npm'];
 
	$sql="SELECT  daftarulg.ukt, daftarulg.statusBayar, daftarulg.cetakKrs, daftarulg.semester , periode.periode
		FROM daftarulg
		INNER JOIN periode ON daftarulg.idPeriode=periode.idPeriode AND daftarulg.npm='$npm'";
		
	$result=mysqli_query($connect,$sql);
		$cek=mysqli_num_rows($result);
	if($cek==0){
		$pesan["success"] = "0";
		$pesan["message"] = "Data Masih Kosong";

		echo json_encode($pesan);
	}else{
		$sql="SELECT  daftarulg.ukt, daftarulg.statusBayar, daftarulg.cetakKrs, daftarulg.semester , periode.periode
		FROM daftarulg
		INNER JOIN periode ON daftarulg.idPeriode=periode.idPeriode AND daftarulg.npm='$npm' ";
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