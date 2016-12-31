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

	if(isset($_POST['image']) && $_POST['image']){
		$img = base64_decode($_POST['image']);
		$fullpath = $target_dir . strtotime("now") . ".jpg";

		$success = @file_put_contents($fullpath, $img);
	}else{
		$success = true;
	}

	if( $success ){

		if( $_POST['id_surat_masuk'] ){
			$query = "UPDATE tbl_surat_masuk 
			SET id_pengirim = '" . @$_POST['id_pengirim'] . "',
			image = '" . @$fullpath . "',
			diteruskan_kepada = '" . @$_POST['diteruskan_kepada'] . "',
			isi_surat = '" . @$_POST['isi_surat'] . "'
			WHERE id_surat_masuk = " . @$_POST['id_surat_masuk'];
			
			if( $result = mysqli_query($con, $query) ){
				echo $_POST['id_surat_masuk'];
			}else{
				echo 0;
			}		
		}else{
			$query = "INSERT INTO id_surat_masuk (id_pengirim, tanggal_surat, diteruskan_kepada, image, isi_surat) VALUES ('" . @$_POST['id_pengirim'] . "', '" . @date('Y-m-d H.i.s') . "', '" . @$_POST['diteruskan_kepada'] . "', '" . @$fullpath . "', '" . @$_POST['isi_surat'] . "')";

			if( $result = mysqli_query($con, $query) ){
				$last_id = mysqli_insert_id($con);
				echo $last_id;
			}else{
				echo 0;
			}
		}
	}

	mysqli_close($con);
}
exit();