<?php 
	define('HOST','localhost');
	define('USER','media');
	define('PASS','tubokarto1904');
	define('DB','numobile');

	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

	$query = "SELECT *,
		(
	        6371 *
	        acos(
	            cos( radians(" . $_GET['lat'] . ") ) *
	            cos( radians( latitude ) ) *
	            cos(
	                radians( longtitude ) - radians(" . $_GET['long'] . ")
	            ) +
	            sin(radians(" . $_GET['lat'] . ")) *
	            sin(radians(latitude))
	        )
	    ) AS distance
	    FROM
	    	tbl_warga
	    HAVING
	    	distance < 50 LIMIT 0, 10";

	$result = mysqli_query($con, $query);

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
