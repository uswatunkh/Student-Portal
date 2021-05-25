<?php
$paymentDate = date('d-m-Y');
$paymentDate=date('d-m-Y', strtotime($paymentDate));
//echo $paymentDate; // echos today! 
$contractDateBegin = date('d-m-Y', strtotime("2021/05/02"));
$contractDateEnd = date('d-m-Y', strtotime("2021/05/30"));

    
if (($paymentDate >= $contractDateBegin) && ($paymentDate <= $contractDateEnd)){
    echo "is between";
}else{
    echo "NO GO!";  
}

?>