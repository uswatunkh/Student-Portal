<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $idKeterampilan 	= $_POST['idKeterampilan'];
	

    require_once 'koneksi.php';
		
		$sql = "DELETE FROM keterampilan WHERE idKeterampilan='".$idKeterampilan."'";

		if(mysqli_query($connect, $sql)) {

          $result["success"] = "1";
          $result["message"] = "Delete success";

          echo json_encode($result);
          mysqli_close($connect);
			}
			
  }

else{

   $result["success"] = "0";
   $result["message"] = "error!";
   echo json_encode($result);

   mysqli_close($conn);
}

?>


