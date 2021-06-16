

<?php
 function send_notifiction ( $title, $body)
 {
  $url='https://fcm.googleapis.com/fcm/send';
  $fields=  array(
    'to'=>'/topics/pengumuman',
    'notification'=> array(
      'title'=>$title,
      'body'=>$body,
      'sound'=>"default",
      'click_action'=>"SOMEACTIVITY"

    ),

      
    
  );
  $headers = array (
          'Authorization:key = AAAAMbnLd80:APA91bFnIYbC1_dz9RHEcmhWakO1rW_gujQrlPvwkxCbWlQkfM2Q1krg0BKvBg6MGmhTIVT_gGpF1iHIXsh-6WKNYL_Pp-6KZ-lVK6OKHz9nofVYOHEpEP2MkdDSamoLBPEfZpAxB7Cb',
          'Content-Type: application/json'
  );
  $ch = curl_init();
  curl_setopt($ch, CURLOPT_URL, $url);
  curl_setopt($ch, CURLOPT_POST, true);
  curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
  curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
  curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
  curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
  curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
  $result = curl_exec($ch);
  if($result == FALSE){
    die('Curl failed:'. curl_error($ch));
  }
  curl_close($ch);
  return $result;

  //echo $result;
}

  //$conn = mysqli_connect("localhost","root","","dbsendmessage");
  //$sql = "SELECT * FROM fcm ORDER BY id DESC LIMIT 0, 1";
  
  
?>




<!DOCTYPE html>
<!-- Coding By CodingNepal - youtube.com/codingnepal -->
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Pengumuman</title>
    <link rel="stylesheet" href="style.css">
  </head>
  <body>
    <div class="center">
    	<center><h2>Notifikasi Pengumuman</h2></center>


    	
      <!-- <h1>Login</h1> -->
      <form action="" method="POST">
        <div class="txt_field">
          <h1></h1>
          <select  name="title"  class="custom-select" style="width:320px; ,height: 35px;" required>
              <option value="INFORMASI">INFORMASI</option>
              <option value="PERINGATAN">PERINGATAN</option>
              <option value="KUISIONER">KUISIONER</option>
              <option value="DOWNLOAD">DOWNLOAD</option>
         </select>
          
          <label>Masukkan Title</label>
          
        </div>

        <div class="txt_field">
          <input type="text" name="body" required>
          <span></span>
          <label>Masukkan Body</label>
        </div>

        
        <div class="txt_field">
          <input type="date" name="tanggalPengumuman" >
          
          
          <label>Masukkan Title</label>
          
        </div>

        
        <input  type="submit" name="submit"   >
        <div class="signup_link">
          <!-- Not a member? <a href="#">Signup</a> -->
        </div>

        <!-- BATAS PHP FUNGSI -->

                      <font color="red"><center> <?php

            if ($_SERVER['REQUEST_METHOD'] =='POST'){
            require_once 'connect.php';
              if(isset($_POST['submit'])){
                $title = $_POST['title'];
                $body =$_POST['body'];
                $tanggalPengumuman =$_POST['tanggalPengumuman'];
                if($title=="" && $body=="" && $tanggalPengumuman==""){
                echo "Some fields are empty";
                 }else{
                    
                    $insert="INSERT INTO pengumuman (title,body,tanggalPengumuman) VALUES ('$title','$body', '$tanggalPengumuman')";
                    $message_status = send_notifiction($title,$body);
                    echo $message_status;
                    if(mysqli_query($connect, $insert)){
                      echo "<h3> Berhasil Tambah</h3>";
                      $title=="";
                      $body=="";
                      $tanggalPengumuman=="";

                    }else{
                      echo "Gagal tambah data";
                    }

                  
                 }

              }else{
                echo "Click Here To Submit button and Change Password";
              }




             }
              
              else{
                echo "Silahkan tambah data";
              }

             

            ?></center></font>

            <!-- BATAS PHP FUNGSI -->

        <div class="signup_link">
          <!-- Not a member? <a href="#">Signup</a> -->
        </div>



      </form>
    </div>

  </body>
</html>
