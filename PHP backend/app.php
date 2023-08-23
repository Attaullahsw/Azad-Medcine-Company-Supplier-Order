<?php

$link = mysqli_connect("localhost","root","","amc");


$res['msg'] = "";
$res['error'] = true;
if($_SERVER['REQUEST_METHOD'] == "POST" ){


	$order_date = $_POST['order_date'];
	$c_code = $_POST['c_code'];

	$q = "INSERT INTO order_main_tbl(order_date,c_code)VALUES('$order_date','$c_code')";
	$query = mysqli_query($link,$q);
	if($query){

		$q = "SELECT order_no FROM order_main_tbl WHERE c_code = '$c_code' ORDER BY order_no DESC LIMIT 1";
		$query = mysqli_query($link,$q);

		if(mysqli_num_rows($query)>0){

			$order_no = mysqli_fetch_object($query);
			$order = $order_no -> order_no;

			$size =(int) $_POST['size'];
			for($i=0; $i<$size; $i++){

			$p_code =(int) $_POST['p_code'.$i];
			$order_details_quantity =(int) $_POST['order_details_quantity'.$i];

			$q = "INSERT INTO order_details_tbl(order_no,p_code,order_details_quantity)VALUES('$order','$p_code','$order_details_quantity')";
			$query = mysqli_query($link,$q);

		}
		$res['msg'] = "Added Successfully";
		$res['error'] = false;

		}else{
			$res['msg'] = "Not Added";
			$res['error'] = true;
		}

	}else{

		$res['msg'] = "Invalid request";
		$res['error'] = true;

	}



	
}else{
		$res['msg'] = "Invalid request";
		$res['error'] = true;


}

echo json_encode($res);


?>