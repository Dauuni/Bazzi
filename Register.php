<?php 
    $con = mysqli_connect("localhost", "bazzi", "ekdms2400!", "bazzi");
    mysqli_query($con,'SET NAMES utf8');
 
    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userName = $_POST["userName"];
    $userNum = $_POST["userNum"];
 
    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?,?,?,?)");
    mysqli_stmt_bind_param($statement, "ssss", $userID, $userPassword, $userName, $userNum);
    mysqli_stmt_execute($statement);
 
 
    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);
?>