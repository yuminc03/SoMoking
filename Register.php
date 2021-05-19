<?php
    $con = mysqli_connect("127.0.0.1:3307", "root", "secretnum", "smoking");
    mysqli_query($con,'SET NAMES utf8');
 
    $name = $_POST["name"];
    $userID = $_POST["userID"];
    $password = $_POST["password"];
    $phone = $_POST["phone"];
    $address = $_POST["address"];
 
    $statement = mysqli_prepare($con, "INSERT INTO user_info VALUES (?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssss", $name, $userID, $password, $phone, $address);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;
 
 
    echo json_encode($response);
 
?>