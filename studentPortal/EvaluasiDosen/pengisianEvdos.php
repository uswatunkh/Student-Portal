<?php
ini_set('display_errors', 1);
error_reporting(E_ALL);

include 'connect.php';

$npm =$_POST['npm'];
//$npm = json_decode($npm,TRUE);

$idEvdos = $_POST['idEvdos'];
$idEvdos = json_decode($idEvdos,TRUE);

$jawaban = $_POST['jawaban'];
$jawaban = json_decode($jawaban,TRUE);



for($i = 0; $i < count($idEvdos); $i++){

      $sql = "INSERT INTO pengisiankuisioner (npm,idEvdos, jawaban) VALUES ('$npm','$idEvdos[$i]','$jawaban[$i]')";

        mysqli_query($connect, $sql);
}

echo "DONE"

?>