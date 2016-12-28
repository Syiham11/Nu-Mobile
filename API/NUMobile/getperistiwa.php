<?php 
	define('HOST','localhost');
	define('USER','numobile');
	define('PASS','sitiajayangbuat');
	define('DB','numobile');

	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

	$cond = isset($_GET['type']) && $_GET['type'] ? "WHERE type = '" . @$_GET['type'] . "'" : '';
	
	$result = mysqli_query($con, "SELECT * FROM tbl_peristiwa " . $cond . " ORDER BY id_peristiwa DESC LIMIT 0, 10");

	$emparray = array();
	while($row =mysqli_fetch_assoc($result))
	{
		$emparray[] = $row;
	}

	$akhir = array(
	        'DataPeristiwa' => $emparray
	);

	echo json_encode($akhir);

	//close the db connection
	mysqli_close($con);
?>
