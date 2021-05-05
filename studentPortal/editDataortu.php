<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $npm = $_POST['npm'];
	$nikAyah = $_POST['nikAyah'];
	$namaAyah = $_POST['namaAyah'];
	$tglLahirAyah = $_POST['tglLahirAyah'];
	$pendidikanAyah = $_POST['pendidikanAyah'];
	$pekerjaanAyah = $_POST['pekerjaanAyah'];
	$nipAyah = $_POST['nipAyah'];
	$pangkatAyah = $_POST['pangkatAyah'];
	$penghasilanAyah = $_POST['penghasilanAyah'];
	$instansiAyah = $_POST['instansiAyah'];
	$nikIbu = $_POST['nikIbu'];
	$namaIbu = $_POST['namaIbu'];
	$tglLahirIbu = $_POST['tglLahirIbu'];
	$pendidikanIbu = $_POST['pendidikanIbu'];
	$pekerjaanIbu = $_POST['pekerjaanIbu'];
	$nipIbu = $_POST['nipIbu'];
	$pangkatIbu = $_POST['pangkatIbu'];
	$penghasilanIbu = $_POST['penghasilanIbu'];
	$instansiIbu = $_POST['instansiIbu'];
	$nikWali = $_POST['nikWali'];
	$namaWali = $_POST['namaWali'];
	$tglLahirWali = $_POST['tglLahirWali'];
	$pendidikanWali = $_POST['pendidikanWali'];
	$pekerjaanWali = $_POST['pekerjaanWali'];
	$nipWali = $_POST['nipWali'];
	$pangkatWali = $_POST['pangkatWali'];
	$penghasilanWali = $_POST['penghasilanWali'];
	$instansiWali = $_POST['instansiWali'];
	 
	

    require_once 'connect.php';

    $sql = "UPDATE orangtua SET nikAyah='$nikAyah',namaAyah='$namaAyah',tglLahirAyah='$tglLahirAyah',pendidikanAyah='$pendidikanAyah',
	pekerjaanAyah='$pekerjaanAyah',nipAyah='$nipAyah',pangkatAyah='$pangkatAyah',penghasilanAyah='$penghasilanAyah',instansiAyah='$instansiAyah',
	nikIbu='$nikIbu',namaIbu='$namaIbu',tglLahirIbu='$tglLahirIbu',pendidikanIbu='$pendidikanIbu',pekerjaanIbu='$pekerjaanIbu',nipIbu='$nipIbu',
	pangkatIbu='$pangkatIbu',penghasilanIbu='$penghasilanIbu',instansiIbu='$instansiIbu',
	nikWali='$nikWali',namaWali='$namaWali',tglLahirWali='$tglLahirWali',pendidikanWali='$pendidikanWali',pekerjaanWali='$pekerjaanWali',nipWali='$nipWali',
	pangkatWali='$pangkatWali',penghasilanWali='$penghasilanWali',instansiWali='$instansiWali'	
	WHERE npm='$npm' ";

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


