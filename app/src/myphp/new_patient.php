<?php    
    $user_name = isset($_POST["name"]);    
    $user_pass = isset($_POST["adress"]);  
    $user_name = isset($_POST["amka"]);  
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="data_user";    
    $con = mysqli_connect($host,$user,$password,$db_name);    
    $sql = "insert into user_info values('".$user_name."','".$user_adress"','".$user_amka");    
    if(mysqli_query($con,$sql))    
    {    
        echo "Data inserted successfully....";    
    }    
    else     
    {    
        echo "some error occured";    
    }    
    mysqli_close($con);    
    ?>    