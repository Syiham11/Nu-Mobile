<?php

#header('Content-type: application/json; charset=UTF-8');

$connection = mysqli_connect("localhost","media","tubokarto1904", "numobile") or die("Error " . mysqli_error($connection));

//    $connection = mysqli_connect("localhost","root","","dbpulsa") or die("Error " . mysqli_error($connection));


$sql = "SELECT * FROM master_pesantren";

$result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));

//create an array
$emparray = array();
while($row =mysqli_fetch_assoc($result))
{
    $emparray[] = $row;
}

$akhir = array(
        'DataPesantren' => $emparray
);

echo json_encode($akhir);

//close the db connection
mysqli_close($connection);
