<?php
ob_start();
session_start();
require_once './connection.php';
require_once './functions.php';
if (isset($_GET['paginatin_id'])) {
    if ($_GET['paginatin_id'] == "All") {
        $_SESSION['pagination_item_NO'] = "-1";
    } else {
        $_SESSION['pagination_item_NO'] = $_GET['paginatin_id'];
    }
    exit();
}
?>
