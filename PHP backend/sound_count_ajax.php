<?php
ob_start();
session_start();
require_once './connection.php';
?>
<?php
$orders_q = mysqli_query($link, "select * from order_main_tbl where  order_status = 0  ");
if (mysqli_num_rows($orders_q) > 0) {
    //echo $_SESSION['num_orders'];
    if ($_SESSION['num_orders'] == mysqli_num_rows($orders_q)) {
        echo "-1";
        exit();
    }
    if ($_SESSION['num_orders'] > mysqli_num_rows($orders_q)) {
        $_SESSION['num_orders'] = mysqli_num_rows($orders_q);
        echo "-1";
        exit();
    }
    $_SESSION['num_orders'] = mysqli_num_rows($orders_q);
    echo mysqli_num_rows($orders_q);
    exit();
} else {
    unset($_SESSION['num_orders']);
    echo "-1";
    exit();
}
 mysqli_close($link);
?>