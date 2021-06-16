
<?php
require_once 'connect.php';

$npm = $_POST['npm'];
 
$sql = "SELECT   daftarulg.semester FROM daftarulg Where npm='$npm'";

$result=mysqli_query($connect,$sql);
 
$data=array();
while($row=mysqli_fetch_assoc($result)){
$data["data"][]=$row;
 
}
 
 
 
 
 header('Content-Type:Application/json');
 
 echo json_encode($data);
 
 
 ?>












 
