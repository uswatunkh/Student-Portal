<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $nik = $_POST['nik'];		
	$namaLengkap = $_POST['namaLengkap'];	
    $tempatLahir = $_POST['tempatLahir'];
	$tanggalLahir = $_POST['tanggalLahir'];
	$jenisKelamin = $_POST['jenisKelamin'];
	$agama = $_POST['agama'];
	$provinsiAsal = $_POST['provinsiAsal'];
	$kotaAsal = $_POST['kotaAsal'];
	$kecamatanAsal = $_POST['kecamatanAsal'];
	$desa = $_POST['desa'];
	$alamatAsal = $_POST['alamatAsal'];
	$alamatSekarang = $_POST['alamatSekarang'];
	$tahunAngkatan = $_POST['tahunAngkatan'];
	$noHp = $_POST['noHp'];
	$email = $_POST['email'];
    $npm = $_POST['npm']; 
	

    require_once 'connect.php';

    $sql = "UPDATE mahasiswa SET nik='$nik', namaLengkap='$namaLengkap',tempatLahir='$tempatLahir',tanggalLahir='$tanggalLahir',jenisKelamin='$jenisKelamin',agama='$agama',
	provinsiAsal='$provinsiAsal',kotaAsal='$kotaAsal',kecamatanAsal='$kecamatanAsal',desa='$desa',alamatAsal='$alamatAsal',
	alamatSekarang='$alamatSekarang',tahunAngkatan='$tahunAngkatan', noHp='$noHp',email='$email'	WHERE npm='$npm' ";

    if(mysqli_query($connect, $sql)) {

          $result["success"] = "1";
          $result["message"] = "success";

          echo json_encode($result);
          mysqli_close($connect);
      }
  }

else{

   $result["success"] = "0";
   $result["message"] = "error!";
   echo json_encode($result);

   mysqli_close($connect);
}

?>


