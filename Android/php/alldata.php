<?php

$link = mysqli_connect("localhost","root","","amc");


$res['msg'] = "";
$res['error'] = true;
$array = array();
if($_SERVER['REQUEST_METHOD'] == "POST" ){

	$q = "SELECT * FROM product_tbl";
	$query = mysqli_query($link,$q);

	if(mysqli_num_rows($query)>0){

		while($row = mysqli_fetch_object($query)){
		
			$array[] = $row;
			
		}


		$res['product_tbl'] = $array;
	}



	$q = "SELECT * FROM customer_tbl";
		$query = mysqli_query($link,$q);
		$array = "";

		if(mysqli_num_rows($query)>0){

			while($row = mysqli_fetch_object($query)){
		
			$array[] = $row;
			
		}

		$res['customer_tbl'] = $array;



		$res['msg'] = "fetch successfully";
		$res['error'] = false;

	}else{
		$res['msg'] = "error occur";
		$res['error'] = true;
	}
}else{
	$res['msg'] = "Invalid Request";
	$res['error'] = true;
}


	


echo json_encode($res);


?>