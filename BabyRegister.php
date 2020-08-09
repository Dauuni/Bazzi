<?php 
    $con = mysqli_connect("localhost", "bazzi", "ekdms2400!", "bazzi");
    mysqli_query($con,'SET NAMES utf8');
 
    $babyName = $_POST["babyName"];
    $babyMW = $_POST["babyMW"];
    $babyAge = $_POST["babyAge"];

    mysqli_query($con,"DELETE FROM baby");
 
    $statement = mysqli_prepare($con, "INSERT INTO baby VALUES (?,?,?)");
    mysqli_stmt_bind_param($statement, "sss", $babyName, $babyMW, $babyAge);
    mysqli_stmt_execute($statement);
 
 
    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);
?>