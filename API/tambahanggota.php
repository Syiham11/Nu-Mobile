<?php

file_put_contents('demo.txt', json_encode($_POST));

if(isset($_POST['token'])){
        define('HOST','localhost');
        define('USER','numobile');
        define('PASS','sitiajayangbuat');
        define('DB','numobile');

        $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

        if( $_POST['token'] == 'form1' ){
                // Query Data tbl_warga, please insert valid field for the best return
                $keys = ''; $values = ' VALUES (';
                $query = 'INSERT INTO tbl_warga (';
                foreach($_POST as $key => $value){
                        if( $key != 'token' && $value ){
                                $keys .= str_replace('vs', '', $key) . ',';
                                $values .= "'" . $value . "'" . ',';
                        }
                }
                $keys = rtrim($keys, ',');
                $values = rtrim($values, ',');

                $fullQuery = $query . $keys . ')' . $values . ')';
                // END OF tbl_warga
        }else if( $_POST['token'] == 'form2' ){
                /*$pendkeys = ''; $pendvalues = ' VALUES (';
                $pendquery = 'INSERT INTO tbl_warga_pendidikan (';
                foreach($_POST as $pkey => $pvalue){
                        if( $pkey != 'token' && strpos($pkey, 'x2') !== false ){
                                $pendkeys .= str_replace('x2', '', $pkey) . ',';
                                $pendvalues .= "'" . $pvalue . "'" . ',';
                        }
                }
                $pendkeys = rtrim($pendkeys, ',');
                $pendvalues = rtrim($pendvalues, ',');
                $pendfullQuery = $pendquery . $pendkeys . ')' . $pendvalues . ')';*/
        }else if($_POST['token'] == 'form3'){

        }

        if( $result = mysqli_query($con, $fullQuery) ){
                $last_id = mysqli_insert_id($con);
                echo $last_id;
        }else{
                echo 0;
        }
        
        file_put_contents($_POST['token'] . '.txt', $fullQuery);
#die;
        // Close My Connection
        mysqli_close($con);
}
die;
