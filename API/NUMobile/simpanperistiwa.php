<?php
if(isset($_POST['token'])){
	define('HOST','localhost');
	define('USER','numobile');
	define('PASS','sitiajayangbuat');
	define('DB','numobile');

	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

	$target_dir = "uploads/";
	if( ! is_dir($target_dir) ){
	    mkdir($target_dir);
	}

	if(isset($_POST['path']) && $_POST['path']){
		$img = base64_decode($_POST['path']);
		$fullpath = $target_dir . strtotime("now") . ".jpg";
		$success = @file_put_contents($fullpath, $img);
	}else{
		$success = true;
	}

	if( $success ){

		if( $_POST['id_peristiwa'] ){
			$query = "UPDATE tbl_peristiwa 
			SET id_warga = '" . @$_POST['id_warga'] . "', 
			deskripsi = '" . @utf8_decode($_POST['deskripsi']) . "',
			path = '" . @$fullpath . "',
			latitude = '" . @$_POST['latitude'] . "',
			langitude = '" . @$_POST['langitude'] . "',
			type = '" . @$_POST['type'] . "'
			WHERE id_peristiwa = " . @$_POST['id_peristiwa'];
			
			if( $result = mysqli_query($con, $query) ){
				echo $_POST['id_peristiwa'];
			}else{
				echo 0;
			}		
		}else{
			$query = "INSERT INTO tbl_peristiwa (id_warga, deskripsi, path, latitude, langitude, created_at, type) VALUES ('" . @$_POST['id_warga'] . "', '" . @utf8_decode($_POST['deskripsi']) . "', '" . @$fullpath . "', '" . @$_POST['latitude'] . "', '" . @$_POST['langitude'] . "', '" . @date('Y-m-d H.i.s') . "', '" . @$_POST['type'] . "')";

			if( $result = mysqli_query($con, $query) ){
				$last_id = mysqli_insert_id($con);
				echo $last_id;
			}else{
				echo 0;
			}
		}

		file_put_contents('Query.txt', $query);
	}

	mysqli_close($con);
}
exit();