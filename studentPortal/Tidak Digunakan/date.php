<?php


// $paymentDate = strtotime(date("Y-m-d H:i:s"));
// $contractDateBegin = strtotime("2021-02-22 12:42:00");
// $contractDateEnd = strtotime("2021-06-22 12:50:00");

// if($paymentDate > $contractDateBegin && $paymentDate < $contractDateEnd) {
//    echo "is between";
// } else {
//     echo "NO GO!";  
// }    

$paymentDate = date('Y-m-d');
$paymentDate=date('Y-m-d', strtotime($paymentDate));
//echo $paymentDate; // echos today! 
$contractDateBegin = date('Y-m-d', strtotime("02-01-2021"));
$contractDateEnd = date('Y-m-d', strtotime("09-06-2021"));
    
if (($paymentDate >= $contractDateBegin) && ($paymentDate <= $contractDateEnd)){
    echo "is between";
}else{
    echo "NO GO!";  
}


// $paymentDate = date('d-m-Y');
// $paymentDate=date('d-m-Y', strtotime($paymentDate));
// //echo $paymentDate; // echos today! 
// $contractDateBegin = date('d-m-Y', strtotime("2021/05/02"));
// $contractDateEnd = date('d-m-Y', strtotime("2021/05/30"));

    
// if (($paymentDate >= $contractDateBegin) && ($paymentDate <= $contractDateEnd)){
//     echo "is between";
// }else{
//     echo "NO GO!";  
//}

?>