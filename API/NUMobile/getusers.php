<?php 
	define('HOST','localhost');
	define('USER','media');
	define('PASS','tubokarto1904');
	define('DB','numobile');

	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

	$result = mysqli_query($con, 'SELECT * FROM tbl_warga LIMIT 0, 10');

	$emparray = array();
	while($row =mysqli_fetch_assoc($result))
	{
		$emparray[] = $row;
	}

	$akhir = array(
	        'DataWarga' => $emparray
	);

	echo json_encode($akhir);

	//close the db connection
	mysqli_close($con);
?>
