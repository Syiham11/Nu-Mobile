<?php

	if(isset($_GET['key']) && isset($_GET['val']) && isset($_GET['id'])){
		define('HOST','localhost');
		define('USER','numobile');
		define('PASS','sitiajayangbuat');
		define('DB','numobile');

		$con = mysqli_connect(HOST,USER,PASS,DB);

		$emparray = array();

		if(!$con){
			echo json_encode($akhir);
			exit();
		}

		if (mysqli_connect_errno()) {
			echo json_encode($akhir);
			exit();
		}

		switch ($_GET['key']) {
			case 'warga':
				$data = mysqli_query($con, 
					"SELECT * FROM tbl_warga 
					WHERE " . $_GET['val'] . "=" . $_GET['id']); 

				if (!$data) {
					echo json_encode($akhir);
					exit();
				}

				$row = mysqli_fetch_row($data);
		        $result = array(
		        	'DataWarga' =>
		        		array(
		        			array( 
					            'id_warga' => $row[0],
					            'username' => $row[1],
					            'nama' => $row[3],
					            'email' => $row[17],
					            'photo' => $row[22],
					            'latitude' => $row[41],
					            'longtitude' => $row[42]
					        )
		        		)
				    );
				break;
			
			default:
				break;
		}

        echo json_encode($result);
	}
	exit();
?>