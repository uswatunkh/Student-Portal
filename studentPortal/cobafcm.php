<?php

$curl = curl_init();
$authKey="key=AAAAMbnLd80:APA91bFnIYbC1_dz9RHEcmhWakO1rW_gujQrlPvwkxCbWlQkfM2Q1krg0BKvBg6MGmhTIVT_gGpF1iHIXsh-6WKNYL_Pp-6KZ-lVK6OKHz9nofVYOHEpEP2MkdDSamoLBPEfZpAxB7Cb";
$title = '"kirimm"';
$body = '"okeee"';

$notif='{
							  "title":'.$title.',
							  "body":'.$body.',
							  "sound":"Default",
							  "click_action": "INFOPEL"
						  }';
$oke='{
						  "to":"/topics/pengumuman",
						  "notification": '.$notif.',
						}';
$headers = array(

'Authorization:key=' . $authKey,
'Content-Type:application/json'

);
$path_to_fcm = 'https://fcm.googleapis.com/fcm/send';
$message = array( 

  'title'     => "kirim",
  'body'      => "oke",
  
  'sound'   => "Default",
  'click_action'      => "INFOPEL"

 );
 $fields = array( 

'to'        => "/topics/pengumuman",
'notification'      => $message,

 );

 $payload = json_encode($fields);
curl_setopt($curl, CURLOPT_URL, $path_to_fcm);
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl, CURLOPT_ENCODING, '');
curl_setopt($curl, CURLOPT_MAXREDIRS, 10);
curl_setopt($curl, CURLOPT_TIMEOUT, 0);
curl_setopt($curl, CURLOPT_FOLLOWLOCATION, true);
curl_setopt($curl, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1);
curl_setopt($curl, CURLOPT_CUSTOMREQUEST, 'POST');
curl_setopt($curl, CURLOPT_POSTFIELDS, $payload);
curl_setopt($curl, CURLOPT_HTTPHEADER, $headers);

/*curl_setopt_array($curl, array(
  CURLOPT_URL => 'https://fcm.googleapis.com/fcm/send',
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => '',
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 0,
  CURLOPT_FOLLOWLOCATION => true,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => 'POST',
  CURLOPT_POSTFIELDS =>$oke,
  CURLOPT_HTTPHEADER => array(
    'Authorization:'.$authKey,
    'Content-Type: application/json'
  ),
));*/

$response = curl_exec($curl);

curl_close($curl);
echo $response;
