<?php
require_once 'connect.php';
//$email = $_GET['key'];
$email = "khasanahnganjuk1122@gmail.com";
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


 } ?>


<!DOCTYPE html>
<!-- Coding By CodingNepal - youtube.com/codingnepal -->
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Forgot Password Student Portal</title>
    <link rel="stylesheet" href="style.css">
  </head>
  <body>
    <div class="center">
    	<center><h2>Welcome</h2></center>
    	<center><h3><?php echo "". $email?></h3></center>
      <!-- <h1>Login</h1> -->
      <form action="" method="POST">
        <div class="txt_field">
          <input type="text" name="password" required>
          <span></span>
          <label>Enter New Password</label>
        </div>
        <div class="txt_field">
          <input type="text" name="cpassword" required>
          <span></span>
          <label>Enter Confirm Password</label>
        </div>
        
        <input type="submit" name="submit">
        <div class="signup_link">
          Not a member? <a href="#">Signup</a>
        </div>
      </form>
    </div>

  </body>
</html>
