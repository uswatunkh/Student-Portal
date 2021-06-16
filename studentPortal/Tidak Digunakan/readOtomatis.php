<?php
require_once 'connect.php';

$npm = $_POST['npm'];
 
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
    			$data["data"][]=$h;
    			echo json_encode($data);
			}else{
    			echo "NO GO!";  
				}

//$data["data"][]=$h;
 
}
 
 
 
 
 header('Content-Type:Application/json');
 
 //echo json_encode($data);
 
 
 ?>