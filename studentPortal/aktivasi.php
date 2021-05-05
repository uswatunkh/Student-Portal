<?php

//if ($_SERVER['REQUEST_METHOD'] =='POST'){
	
    require_once 'connect.php';
    $npm = $_POST['npm'];
	//$idPeriode = 1;
    $tanggalLahir = $_POST['tanggalLahir'];
    $passwordUser = $_POST['passwordUser'];
	$conformPass = $_POST['conformPass'];
	
	$select = mysqli_query($connect,"SELECT npm FROM mahasiswa WHERE npm='$npm' ");
	
	if ($select->num_rows >0){
		$result["success"] = "2";
				$result["message"] = "Data sudah ada";
				echo json_encode($result);
				mysqli_close($connect);
	}else{
		if ($passwordUser == $conformPass){
			$passwordUser = password_hash($passwordUser, PASSWORD_DEFAULT);
			$sql = "INSERT INTO mahasiswa (npm, tanggalLahir, passwordUser) VALUES ('$npm', '$tanggalLahir', '$passwordUser')";
			$sqll = "INSERT INTO orangtua (npm) VALUES ('$npm')";
			if ( mysqli_query($connect, $sql) && mysqli_query($connect, $sqll)) {
				$result["success"] = "1";
				$result["message"] = "success";

				echo json_encode($result);
				mysqli_close($connect);

			} else {
				$result["success"] = "3";
				$result["message"] = "eror";

				echo json_encode($result);
				mysqli_close($connect);
				
			}
		}else{
			$result["success"] = "4";
				$result["message"] = "Konfirmasi Tidak sama";

				echo json_encode($result);
				mysqli_close($connect);
		}
		
	}
	/*if ($passwordUser == $conformPass){
		$passwordUser = password_hash($passwordUser, PASSWORD_DEFAULT);
		$sql = "INSERT INTO mahasiswa (npm,idPeriode, tanggalLahir, passwordUser) VALUES ('$npm','$idPeriode', '$tanggalLahir', '$passwordUser')";

		if ( mysqli_query($conn, $sql) ) {
			$result["success"] = "1";
			$result["message"] = "success";

			echo json_encode($result);
			mysqli_close($conn);

		} else {
			echo "eror";
			
		}
	}
	
	else{
		$result["success"] = "0";
        $result["message"] = "error";

        echo json_encode($result);
        mysqli_close($conn);
	}*/
//}

?>