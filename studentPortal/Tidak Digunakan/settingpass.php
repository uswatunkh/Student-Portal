<?php
   require_once 'connect.php';
	$newpass = "123456";
	$conformPass = "12";
    $npm = 12;
	
	if ($newpass == $conformPass){
		$passwordUser = password_hash($newpass, PASSWORD_DEFAULT);  // untuk merubah ke password_hash
		$update= "UPDATE mahasiswa SET passwordUser= '$passwordUser'	WHERE npm='$npm' ";
		$res=mysqli_query($connect,$update);
		if ($res){
			$result["success"] = "1";
			$result["message"] = "Password Berhasil Diubah";

			echo json_encode($result);
		}else{
			echo "eror";
		}
	
}else {
	$result["success"] = "0";
	$result["message"] = "konfirmasi password tidak sama";

	echo json_encode($result);
}



?>