<?php

if($_SERVER['REQUEST_METHOD'] == 'POST') {

    $npm = $_POST['npm'];
    $scanKtp = $_POST['scanKtp'];

    $path = "ktp_image/$npm.jpeg";
    $finalPath = "http://192.168.1.44/studentPortal/".$path;

    require_once 'connect.php';

    $sql = "UPDATE mahasiswa SET scanKtp='$finalPath' WHERE npm='$npm' ";

    if (mysqli_query($connect, $sql)) {
        
        if ( file_put_contents( $path, base64_decode($scanKtp) ) ) {
            
            $result['success'] = "1";
            $result['message'] = "success";

            echo json_encode($result);
            mysqli_close($connect);

        }

    }

}

?>