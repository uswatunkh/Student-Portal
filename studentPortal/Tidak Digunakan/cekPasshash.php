<?php

//if ($_SERVER['REQUEST_METHOD']=='POST') {

    $npm = $_POST['npm'];
    $oldpass = $_POST['oldpass'];
    $newpass = $_POST['newpass'];
    $conformPass = $_POST['conformpass'];

    require_once 'connect.php';

    $sql = "SELECT * FROM mahasiswa WHERE npm='$npm' ";

    $response = mysqli_query($connect, $sql);

    $result = array();
    //$result['login'] = array();
    if ($newpass == $conformPass){
	    //BATAS confirm
	    
	    if ( mysqli_num_rows($response) === 1 ) {
	        
	        $row = mysqli_fetch_assoc($response);

	        if ( password_verify($oldpass, $row['passwordUser']) ) {
	            
	            //BATAS
					$passwordUser = password_hash($newpass, PASSWORD_DEFAULT);  // untuk merubah ke password_hash
					$update= "UPDATE mahasiswa SET passwordUser= '$passwordUser' WHERE npm='$npm' ";
					$res=mysqli_query($connect,$update);
					if ($res){
						$result["success"] = "1";
						$result["message"] = "Password Berhasil Diubah";

						echo json_encode($result);
					}else{
						echo "eror";
					}
				
				//BATAS

	        } else {

	            $result["success"] = "2";
				$result["message"] = "Password lama salah";
				echo json_encode($result);

	        }

	    }
	    //BATAS confirm
	 }else {
	$result["success"] = "0";
	$result["message"] = "konfirmasi password tidak sama";

	echo json_encode($result);
}
//}

?>




<!-- 
//CEK PASWORD HASH
<?php
$hashed = '$2y$10$0.6Q3uWxSX1sOzqG/4XipOBFMsK2LBp1066021hQSLgmQpUnO.YTS';
 
if (password_verify('12345', $hashed)) {
    echo 'Password is valid!';
} else {
    echo 'Invalid password.';
} -->