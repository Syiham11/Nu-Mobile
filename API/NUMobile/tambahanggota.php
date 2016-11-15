<?php

if(isset($_POST['token'])){
        file_put_contents(@$_POST['token'] . '_demo.json', json_encode($_POST));
        define('HOST','localhost');
        define('USER','media');
        define('PASS','tubokarto1904');
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

                if( $result = mysqli_query($con, $fullQuery) ){
                        $last_id = mysqli_insert_id($con);
                        echo $last_id;
                }else{
                        var_dump(mysqli_error($con));
                        echo 0;
                }

                // Close My Connection
                mysqli_close($con);
                
                die;
        }else if( $_POST['token'] == 'form2' ){
                // Query Data tbl_warga, please insert valid field for the best return
                $keys = '';
                $query = 'UPDATE tbl_warga SET ';
                foreach($_POST as $key => $value){
                        if( $value && strpos($key, 'vs') !== false ){
                                if( $key == 'vsid_pendapatan' ){
                                        $qF2 = mysqli_query($con, 'SELECT id_pendapatan FROM master_pendapatan WHERE jumlah_pendapatan ="' . $value . '"');
                                        $row = mysqli_fetch_assoc($qF2);
                                        $value = $row['id_pendapatan'];
                                }else if( $key == 'vsid_jenis_pekerjaan' ){
                                        $qF2 = mysqli_query($con, 'SELECT id_jenis_pekerjaan FROM master_jenis_pekerjaan WHERE nama_pekerjaan ="' . $value . '"');
                                        $row = mysqli_fetch_assoc($qF2);
                                        $value = $row['id_jenis_pekerjaan'];
                                }else if( $key == 'vsid_induk_organisasi' ){
                                        $qF2 = mysqli_query($con, 'SELECT id_induk_organisasi FROM master_induk_organisasi WHERE nama_organisasi ="' . $value . '"');
                                        $row = mysqli_fetch_assoc($qF2);
                                        $value = $row['id_induk_organisasi'];
                                }
                                $keys .= str_replace('vs', '', $key) . '="' . $value . '",';
                        }
                }
                $keys = rtrim($keys, ',');

                $fullQuery = $query . $keys . ' WHERE id_warga=' . $_POST['id_warga']; 
                // END OF tbl_warga

                $mySchool = explode("~~", $_POST['xssekolah']);
                $n = 1; $return = "";
                foreach ($mySchool as $key => $value) {
                        $pendquery = 'INSERT INTO tbl_warga_pendidikan (id_warga, id_pendidikan, nama_sekolah) VALUES (' . $_POST['id_warga'] . ',' . $n++ . ',"' . $value . '")';
                        if( mysqli_query($con, $pendquery) ){
                                $last_id = mysqli_insert_id($con);
                                $return .= $last_id . '~~';
                        }
                }

                echo $return;

                // Close My Connection
                mysqli_close($con);

        }else if($_POST['token'] == 'form3'){

        }
        
        file_put_contents($_POST['token'] . '.json', $fullQuery);
}
die;
