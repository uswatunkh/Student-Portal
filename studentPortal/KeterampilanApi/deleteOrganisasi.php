<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $idOrganisasi 	= $_POST['idOrganisasi'];
	

    require_once 'koneksi.php';
		
		$sql = "DELETE FROM organisasi WHERE idOrganisasi='".$idOrganisasi."'";

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


