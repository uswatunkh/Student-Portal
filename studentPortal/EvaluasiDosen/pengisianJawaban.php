<?php
ini_set('display_errors', 1);
error_reporting(E_ALL);

include 'connect.php';

$npm =$_POST['npm'];
$idPengajaran =$_POST['idPengajaran'];
//$npm = json_decode($npm,TRUE);
$idPertanyaan =$_POST['idPertanyaan'];
// $idEvdos = $_POST['idEvdos'];
 $idPertanyaan = json_decode($idPertanyaan,TRUE);

$skor = $_POST['skor'];
$skor = json_decode($skor,TRUE);
//to jawaban pesan
$pesan =$_POST['pesan'];
$kesan =$_POST['kesan'];



for($i = 0; $i < count($idPertanyaan); $i++){

      $sql = "INSERT INTO jawaban (npm,idPengajaran,idPertanyaan, skor) VALUES ('$npm','$idPengajaran','$idPertanyaan[$i]','$skor[$i]')";
      


        mysqli_query($connect, $sql);
        
}
$sql2 = "UPDATE `pengajaran` SET `statusKuisioner`='Sudah Terisi' WHERE idPengajaran='$idPengajaran'";
mysqli_query($connect, $sql2);


$sqlPesan = "INSERT INTO jawabanpesan (npm,idPengajaran,kesan, pesan) VALUES ('$npm','$idPengajaran', '$kesan', '$pesan')";
mysqli_query($connect, $sqlPesan);

echo "DONE"

?>