<?php

if($_SERVER['REQUEST_METHOD'] == 'POST') {

    $idKeterampilan = $_POST['idKeterampilan'];
    $scanBukti = $_POST['scanBukti'];


    $path = "keterampilan/$idKeterampilan.jpeg";
    $finalPath = "http://192.168.1.43/studentPortal/".$path;

    require_once 'connect.php';

    $sql = "UPDATE keterampilan SET scanBukti='$finalPath' WHERE idKeterampilan='$idKeterampilan' ";

    if (mysqli_query($connect, $sql)) {
        
        if ( file_put_contents( $path, base64_decode($scanBukti) ) ) {
            
            $result['success'] = "1";
            $result['message'] = "success";

            echo json_encode($result);
            mysqli_close($connect);

        }

    }

}

?>