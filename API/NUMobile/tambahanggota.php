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
                mysqli_query($con, $fullQuery);
                // END OF tbl_warga

                $mySchool = explode("~~", $_POST['xssekolah']);
                $n = 1; $return = "";
                foreach ($mySchool as $key => $value) {
                        $pendquery = 'INSERT INTO tbl_warga_pendidikan (id_warga, id_pendidikan, nama_sekolah) VALUES (' . $_POST['id_warga'] . ',' . $n++ . ',"' . $value . '")';
                        file_put_contents($_POST['token'] . '_pendidikan.json', $pendquery . PHP_EOL, FILE_APPEND);
                        if( mysqli_query($con, $pendquery) ){
                                $last_id = mysqli_insert_id($con);
                                $return .= $last_id . '~~';
                        }
                }

                echo $_POST['id_warga'];

                // Close My Connection
                mysqli_close($con);

        }else if($_POST['token'] == 'form3'){
                // Query Data tbl_warga, please insert valid field for the best return
                $keys = '';
                $query = 'UPDATE tbl_warga SET ';
                foreach($_POST as $key => $value){
                        if( $value && strpos($key, 'vs') !== false ){
                                if( $key == 'vsid_pesantren1' || $key == 'vsid_pesantren2' ){
                                        $qF2 = mysqli_query($con, 'SELECT id_pesantren FROM master_pesantren WHERE nama_pesantren ="' . $value . '"');
                                        $row = mysqli_fetch_assoc($qF2);
                                        $value = $row['id_pesantren'];
                                }
                                $keys .= str_replace('vs', '', $key) . '="' . $value . '",';
                        }
                }
                $keys = rtrim($keys, ',');

                $fullQuery = $query . $keys . ' WHERE id_warga=' . $_POST['id_warga']; 
                mysqli_query($con, $fullQuery);
                // END OF tbl_warga

                echo $_POST['id_warga'];

                // Close My Connection
                mysqli_close($con);
        }else if($_POST['token'] == 'form4'){
                $target_dir = "uploads/";
                if( ! is_dir($target_dir) ){
                        mkdir($target_dir);
                }
                $target_file = $target_dir . basename($_FILES["UploadFile"]["name"]);
                $imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);
                // Check if image file is a actual image or fake image
                $check = getimagesize($_FILES["UploadFile"]["tmp_name"]);
                if($check !== false) {
                        if (move_uploaded_file($_FILES["UploadFile"]["tmp_name"], $target_file)) {
                                // Query Data tbl_warga, please insert valid field for the best return
                                $fullQuery = 'UPDATE tbl_warga SET photo = "' . $target_file . '"' . ' WHERE id_warga =' . $_POST['id_warga'];
                                mysqli_query($con, $fullQuery);
                                // END OF tbl_warga

                                echo $_POST['id_warga'];

                                // Close My Connection
                                mysqli_close($con);
                        }else{
                                echo 0;
                        }
                }else{
                        echo 0;
                }
                file_put_contents('files_' . $_POST['token'] . '.json', json_encode($_FILES));
        }
        
        file_put_contents($_POST['token'] . '.json', $fullQuery);
}
die;
