<?php

$curl = curl_init();
$authKey = "key=AAAAMbnLd80:APA91bFnIYbC1_dz9RHEcmhWakO1rW_gujQrlPvwkxCbWlQkfM2Q1krg0BKvBg6MGmhTIVT_gGpF1iHIXsh-6WKNYL_Pp-6KZ-lVK6OKHz9nofVYOHEpEP2MkdDSamoLBPEfZpAxB7Cb";
$registration_ids = '["cxgmsMjB8A8:APA91bHFQ6-sox_iRudO_4gLBqIy4nIR_jaQnqRYI8koYa0u4ApEnvBM5etndIqhWV7QWAieICDIq32HKZkGgEcF3Zf8Q84Drg-Q1gF2baWA3y1_p2Z4cfvHZ6y01WuUVYnz1Wf2HPPb"]';
curl_setopt_array($curl, array(
  CURLOPT_URL => "https://fcm.googleapis.com/fcm/send",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "POST",
  CURLOPT_POSTFIELDS => '{
                "registration_ids": ' . $registration_ids . ',
                "notification": {
                    "title": "Judul Notifikasi",
                    "body": "Isi Notifikasi"
                }
              }',
  CURLOPT_HTTPHEADER => array(
    "Authorization: " . $authKey,
    "Content-Type: application/json",
    "cache-control: no-cache"
  ),
));

$response = curl_exec($curl);
$err = curl_error($curl);

curl_close($curl);

if ($err) {
  echo "cURL Error #:" . $err;
} else {
  echo $response;
}