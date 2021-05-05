<?php

$curl = curl_init();
$authKey = "key=AAAAMbnLd80:APA91bFnIYbC1_dz9RHEcmhWakO1rW_gujQrlPvwkxCbWlQkfM2Q1krg0BKvBg6MGmhTIVT_gGpF1iHIXsh-6WKNYL_Pp-6KZ-lVK6OKHz9nofVYOHEpEP2MkdDSamoLBPEfZpAxB7Cb";
$registration_ids = '["fdLOoNVgl6Y:APA91bHPGzYAGBdy6WLPVjFFkOqlaCLnY__V6xhl9bJ2Fv0jPyR7XM5sfdxmi8FNAct8h9U1PLMaOqLXkG2CztLLWo219IWqrdR79PaJfL-LerdUe4R3qXeY31DfpXEgh9MoXZjaCCfO"]';
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