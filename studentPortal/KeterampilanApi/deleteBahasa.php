<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $idBahasa 	= $_POST['idBahasa'];
	

    require_once 'koneksi.php';
		
		$sql = "DELETE FROM bahasainternational WHERE idBahasa='".$idBahasa."'";

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


