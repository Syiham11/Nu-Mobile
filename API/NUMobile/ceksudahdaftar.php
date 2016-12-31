<?php 
       //harus sama dgn di model api
        $vsusername = $_GET['vsusername'];
        $vspassword = $_GET['vspassword'];

//$vsid_user='0';
// $vsusername ='fatimah';
// $vspassword ='123456';
        $vspasswordmd5 = md5($vspassword); 
       
 

$connection = mysqli_connect("localhost","numobile","sitiajayangbuat", "numobile") or die("Error " . mysqli_error($connection));


        if (mysqli_connect_errno()) { 
            echo 'Database connection error: ' . mysqli_connect_error(); 
            exit(); 
        } 

        #Escape special characters to avoid SQL injection attacks 
        //$vsnohandphone = mysqli_real_escape_string($con, $vsnohandphone ); 
         
        #Query the database to get the user details. 
        $leveldetails = mysqli_query($connection, "SELECT username,password FROM tbl_warga WHERE username='$vsusername' and password='$vspasswordmd5'"); 

        #If no data was returned, check for any SQL errors 
        if (!$leveldetails) { 
    echo 'Could not run query: ' . mysqli_error($connection); 
                exit;
        } 
  
        #Get the first row of the results 
        $row = mysqli_fetch_row($leveldetails); 

        #Build the result array (Assign keys to the values) 
        $result_data = array( 
           'username' => $row[0],
            'password' => $row[1],
            ); 



    
        #Output the JSON data 
        echo json_encode($result_data);  

    //   $response["success"] = 1;
    //   echo json_encode($response);
     
?>