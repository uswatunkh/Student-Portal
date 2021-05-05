<?php

$curl = curl_init();
$authKey="key=AAAAMbnLd80:APA91bFnIYbC1_dz9RHEcmhWakO1rW_gujQrlPvwkxCbWlQkfM2Q1krg0BKvBg6MGmhTIVT_gGpF1iHIXsh-6WKNYL_Pp-6KZ-lVK6OKHz9nofVYOHEpEP2MkdDSamoLBPEfZpAxB7Cb";
//$title = $_POST['title'];
curl_setopt_array($curl, array(
  CURLOPT_URL => 'https://fcm.googleapis.com/fcm/send',
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => '',
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 0,
  CURLOPT_FOLLOWLOCATION => true,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => 'POST',
  CURLOPT_POSTFIELDS =>'{
						  "to":"/topics/pengumuman",
						  "notification": {
							  "title":"Kirim dari ceo",
							  "body":"test message postmanyaaa",
							  "sound":"Default",
							  "click_action": "INFOPEL"
						  },
						  "data": {
							"extra" : "Lorem sjhjkajhjgsjsahgjak"
							
						  }
						}',
  CURLOPT_HTTPHEADER => array(
    'Authorization:'.$authKey,
    'Content-Type: application/json'
  ),
));

$response = curl_exec($curl);

curl_close($curl);
echo $response;
