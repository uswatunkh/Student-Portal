<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
    $npm = $_POST['npm'];

    require_once 'connect.php';

 //   $sql = "SELECT * FROM mahasiswa, periode WHERE mahasiswa.npm='$npm' ";
	$sql = "SELECT * FROM mahasiswa WHERE npm='$npm' ";
	// $sqlPeriode = "SELECT YEAR(periode.tanggalMulaiPeriode) FROM daftarulg INNER JOIN periode ON   daftarulg.npm='$npm' AND daftarulg.semester='1'  ";
	$sqll = "SELECT prodi.namaProdi, prodi.kelas FROM mahasiswa INNER JOIN prodi ON mahasiswa.idProdi=prodi.idProdi 
	AND mahasiswa.npm='$npm' ";

    $response = mysqli_query($connect, $sql);
	// $responsePeriode = mysqli_query($connect, $sqlPeriode);
	$responseProdi = mysqli_query($connect, $sqll);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 && mysqli_num_rows($responseProdi) === 1 ) {
        
        if (($row = (mysqli_fetch_assoc($response))) && ($rows = (mysqli_fetch_assoc($responseProdi))) ) {
 
             $h['npm']        = $row['npm'] ;
			 // $h['tanggalMulaiPeriode']        = $rowss['YEAR(periode.tanggalMulaiPeriode)'] ;
			 $h['namaProdi']        = $rows['namaProdi'] ;
			 $h['kelas']        = $rows['kelas'] ;
			 $h['statusDiri']        = $row['statusDiri'] ;
			 $h['nik']        = $row['nik'] ;
			 $h['namaLengkap']        = $row['namaLengkap'] ;
			 $h['tempatLahir']        = $row['tempatLahir'] ;
			 $h['tanggalLahir']        = $row['tanggalLahir'] ;
			 $h['jenisKelamin']        = $row['jenisKelamin'] ;
			 $h['agama']        = $row['agama'] ;
			 $h['provinsiAsal']        = $row['provinsiAsal'] ;
			 $h['alamatAsal']        = $row['alamatAsal'] ;
			 $h['kotaAsal']        = $row['kotaAsal'] ;
			 $h['kecamatanAsal']        = $row['kecamatanAsal'] ;
			 $h['desa']        = $row['desa'] ;
			 $h['alamatSekarang']        = $row['alamatSekarang'] ;
			 $h['tahunAngkatan']        = $row['tahunAngkatan'] ;
			 $h['noHp']        = $row['noHp'] ;
			 $h['email']        = $row['email'] ;
			 $h['scanKtp']        = $row['scanKtp'] ;   
			 $h['imageProfil']        = $row['imageProfil'] ;
             
 
             array_push($result["read"], $h);
 
             $result["success"] = "1";
             echo json_encode($result);
        }
 
   }
 
 }else {
 
     $result["success"] = "0";
     $result["message"] = "Error!";
     echo json_encode($result);
 
     mysqli_close($connect);
 }
 
 ?>