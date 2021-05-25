<?php
require_once 'connect.php';
$email = $_GET['key'];
$sql="SELECT * FROM mahasiswa WHERE email = '$email'";
$query= mysqli_query($connect, $sql);
 if(mysqli_num_rows($query)==1){
 	if(isset($_POST['submit'])){
 		$password = $_POST['password'];
 		$cpassword =$_POST['cpassword'];
 		if($password == "" && $cpassword==""){
	 	echo "Some fields are empty";
		 }else{
		 	if($password==$cpassword){
		 		$password = password_hash($password, PASSWORD_DEFAULT);
		 		$update="UPDATE mahasiswa SET passwordUser='$password' WHERE email = '$email'";
		 		if(mysqli_query($connect, $update)){
		 			echo "<h1> User Password are Change Successfuly !!! Please Login</h1>";
		 		}else{
		 			echo "Password Changing error refresh and reclick the email link";
		 		}

		 	}else{
		 		echo "Entered Password Not Match";
		 	}
		 }

 	}else{
 		echo "Click Here To Submit button and Change Password";
 	}


 }
	// if(isset($_POST['submit'])){
	// $password = $_POST['password'];
	// $cpassword =$_POST['cpassword'];
	// if($password == "" && $cpassword==""){
	// 	echo "some password are empty";
	// }else {
	// 	if($password == $cpassword){
	// 		echo "Success";
	// 	}else{
	// 		echo "password are not match";
	// 	}
	// }
	// }

?>

<!DOCTYPE html>
<html>
<head>
	<title>Forgot Password</title>
</head>
<body>
	<form action="" method="POST">
		<h1><?php echo "Welcome". $email?></h1>
		Enter New Password: <input type="text" name="password"><br>
		Enter Conform Password: <input type="text" name="cpassword"><br>
		<input type="submit" name="submit"> 
	</form>

</body>
</html>