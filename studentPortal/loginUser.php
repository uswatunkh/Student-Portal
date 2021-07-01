<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {

    $npm = $_POST['npm'];
    $passwordUser = $_POST['passwordUser'];

    require_once 'connect.php';

    $sql = "SELECT * FROM mahasiswa WHERE npm='$npm' ";

    $response = mysqli_query($connect, $sql);

    $result = array();
    $result['login'] = array();
    
    if ( mysqli_num_rows($response) === 1 ) {
        
        $row = mysqli_fetch_assoc($response);

        if ( password_verify($passwordUser, $row['passwordUser']) ) {
            
            $index['npm'] = $row['npm'];
            $index['tanggalLahir'] = $row['tanggalLahir'];

            array_push($result['login'], $index);

            $result['success'] = "1";
            $result['message'] = "success";
            echo json_encode($result);

            mysqli_close($connect);

        } else {

            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);

            mysqli_close($connect);

        }

    } else{
        $result['success'] = "2";
            $result['message'] = "error";
            echo json_encode($result);

            mysqli_close($connect);
    }

}

?>