<?php
 function send_notifiction ( $title, $body)
 {
  $url='https://fcm.googleapis.com/fcm/send';
  $fields=  array(
    'to'=>'/topics/pengumuman',
    'notification'=> array(
      'title'=>$title,
      'body'=>$body,
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
  $conn = mysqli_connect("localhost","root","","databasestudent");
  $sql = "SELECT * FROM pengumuman ORDER BY idPengumuman DESC LIMIT 0, 1";
  $result= mysqli_query($conn,$sql);
  $title = array ();
  if(mysqli_num_rows($result)>0){
    while($row=mysqli_fetch_assoc($result)){
      $title[] = $row['title'];
      $body[] = $row['body'];
      
    }
  }
  mysqli_close($conn);
  $title = $title[0] ;
  $body = $body[0] ;
  $message_status = send_notifiction($title,$body);
  echo $message_status;
?>