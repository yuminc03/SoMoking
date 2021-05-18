<?php
    $con = mysqli_connect("127.0.0.1:3307", "root", "secretnum", "smoking");
    mysqli_query($con,'SET NAMES utf8');
 
    $userID = $_POST["userID"];
    $password = $_POST["password"];
 
    $statement = mysqli_prepare($con, "SELECT * FROM user_info WHERE userID = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $userID, $password);
    mysqli_stmt_execute($statement);
 
 
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $name, $userID, $password, $phone, $address);
  
    $response = array();
    $response["success"] = false;
 
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["name"] = $name;
        $response["userID"] = $userID;
        $response["password"] = $password;
        $response["phone"] = $phone;
        $response["address"] = $address;
    }
 
    echo json_encode($response);
 

?>