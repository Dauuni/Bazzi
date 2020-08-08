<?php 
    $con = mysqli_connect("localhost", "bazzi", "ekdms2400!", "bazzi");
    mysqli_query($con,'SET NAMES utf8');
 
    $babyDate = $_POST["babyDate"];
    $babyCM = $_POST["babyCM"];
    $babyKG = $_POST["babyKG"];
 
    $statement = mysqli_prepare($con, "INSERT INTO babyGraph VALUES (?,?,?)");
    mysqli_stmt_bind_param($statement, "sdd",$babyDate, $babyCM, $babyKG);
    mysqli_stmt_execute($statement);
 
 
    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);
?>