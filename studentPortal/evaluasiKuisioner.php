<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

     $npm = $_POST['npm'];		
	 $idDosen = $_POST['idDosen'];	
     $jawaban1_1 = $_POST['jawaban1_1'];
	

    require_once 'connect.php';

    $sql = "INSERT INTO evaluasikuisioner( npm, idDosen, jawaban1_1) VALUES ('$npm','$idDosen','$jawaban1_1')	 ";

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


