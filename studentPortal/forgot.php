<?php
require_once 'connect.php';
require 'PHPMailer/PHPMailerAutoload.php';
require 'PHPMailer/class.phpmailer.php';
require 'PHPMailer/class.smtp.php';
require 'admin.php';
//$email = "khasanahnganjuk1122@gmail.com";
$email = $_POST['email'];
 $sql="SELECT * FROM mahasiswa WHERE email = '$email'";

 $query= mysqli_query($connect, $sql);
 if(mysqli_num_rows($query)==1){

	$mail = new PHPMailer;

	//$mail->SMTPDebug = 3;                               // Enable verbose debug output

	$mail->isSMTP();                                      // Set mailer to use SMTP
	$mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP servers
	$mail->SMTPAuth = true;                               // Enable SMTP authentication
	//Enter Your sender email
	$mail->Username = $adminUsername;                 // SMTP username
	$mail->Password = $adminPass;                           // SMTP password
	$mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
	$mail->Port = 587;                                    // TCP port to connect to

	$mail->setFrom('khasanahnganjuk1122@gmail.com', 'STUDENT PORTAL');
	$mail->addAddress($email);     // Add a recipient
	               // Name is optional
	$mail->addReplyTo('khasanahnganjuk1122@gmail.com', 'STUDENT PORTAL');
	


	$mail->Subject = 'Forgot Password Student Portal';
	$mail->Body    = "Click Here the Link Below: 
	http://192.168.10.42/studentPortal/resetPasswordForm.php?key=$email";

	if(!$mail->send()) {
	    echo 'Message could not be sent.';
	    echo 'Mailer Error: ' . $mail->ErrorInfo;
	} else {
	    $msg["mail"]="send";
	    echo json_encode($msg);
	}
//batas
 }else{
 	echo "Envalid Email";
 }
?>