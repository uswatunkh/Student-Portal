<?php
//require_once 'connect.php';
require 'PHPMailer/PHPMailerAutoload.php';
require 'PHPMailer/class.phpmailer.php';
require 'PHPMailer/class.smtp.php';
require 'admin.php';
$email = "khasanahnganjuk1122@gmail.com";
//$email = $_POST['email'];
// $sql="SELECT * FROM mahasiswa WHERE email = '$email'";

// $check= mysqli_query($connect, $sql)
// if($check){

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

	$mail->setFrom('khasanahnganjuk1122@gmail.com', 'USWATUN');
	$mail->addAddress($email);     // Add a recipient
	               // Name is optional
	$mail->addReplyTo('khasanahnganjuk1122@gmail.com', 'USWATUN');
	


	$mail->Subject = 'Forgot Password Uswatun Khasanah';
	$mail->Body    = 'Click Here the Link Below'."<br>".'http://localhost/studentPortal/resetPasswordForm.php';

	if(!$mail->send()) {
	    echo 'Message could not be sent.';
	    echo 'Mailer Error: ' . $mail->ErrorInfo;
	} else {
	    echo 'Message has been sent';
	}

// }else{
// 	echo "Envalid Email";
// }
?>