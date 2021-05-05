
<?php
require_once 'connect.php';
//$npm = $_POST['npm'];
//$idKeterampilan = $_POST['idKeterampilan'];
$idKeterampilan = $_POST['idKeterampilan'] : '';

$sql = "DELETE FROM keterampilan WHERE idKeterampilan=7";

$result = mysqli_query($connect, $sql);

if($result){
	echo "Data Deleted"
}else{
	echo "Failed";
}
mysqli_close($connect);
	
	/*class emp{}
	
	if (empty($idKeterampilan)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Error hapus Data"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("DELETE FROM keterampilan WHERE idKeterampilan='".$idKeterampilan."'");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di hapus";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error hapus Data";
			die(json_encode($response)); 
		}	
	}*/
?>