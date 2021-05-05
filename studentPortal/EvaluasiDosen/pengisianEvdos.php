<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$npm = $_POST['npm'];
	$idEvdos = $_POST['idEvdos'];
	$jawaban = $_POST['jawaban'];
	
    require_once 'connect.php';

    $sql = "INSERT INTO `pengisiankuisioner`( `npm`, `idEvdos`, `jawaban`) VALUES ('$npm','$idEvdos','$jawaban') ";

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


