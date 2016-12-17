<?php 
	define('HOST','localhost');
	define('USER','numobile');
	define('PASS','sitiajayangbuat');
	define('DB','test');

	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

	$result = mysqli_query($con, 'SELECT * FROM tbl_sdm');

	$emparray = array();
	while($row =mysqli_fetch_assoc($result))
	{
		$emparray[] = $row;
	}

	$akhir = array(
	        'DataSDM' => $emparray
	);

	echo json_encode($akhir);

	//close the db connection
	mysqli_close($con);
?>