<?php
	$connection = mysqli_connect("localhost","numobile","sitiajayangbuat", "numobile") or die("Error " . mysqli_error($connection));

	$emparray = array();

	if (mysqli_connect_errno()) {
		$akhir = array(
	        'DataSuratMasuk' => $emparray
		);

		echo json_encode($akhir);
		exit();
	}

	$cond = isset($_GET['id_warga']) && $_GET['id_warga'] ? @$_GET['id_warga'] : 0;

	if($cond){
		$results = mysqli_query($connection, "SELECT * FROM tbl_surat_masuk WHERE diteruskan_kepada='$cond'");
		
		if($results->num_rows){
			while($row = mysqli_fetch_assoc($results)){
				$emparray[] = $row;
			}
		}

		$akhir = array(
		        'DataSuratMasuk' => $emparray
		);
	}else{
		$akhir = array(
	        'DataSuratMasuk' => $emparray
		);

		echo json_encode($akhir);
		exit();
	}

	echo json_encode($akhir);
?>