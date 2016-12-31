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

	$cond = isset($_GET['type']) && $_GET['type'] ? @$_GET['type'] : '';

	$results = mysqli_query($connection, "SELECT * FROM tbl_surat_masuk WHERE id_pengirim='$vsusername'");

	while($row =mysqli_fetch_assoc($result)){
		$emparray[] = $row;
	}

	$akhir = array(
	        'DataSuratMasuk' => $emparray
	);

	echo json_encode($akhir);
?>