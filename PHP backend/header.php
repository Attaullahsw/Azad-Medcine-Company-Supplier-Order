<?php
session_start();
ob_start();
if (!isset($_SESSION['username']) || !isset($_SESSION['userpassword'])) {
    header("location:login.php");
    exit();
}
require_once 'connection.php';
?>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from healthadmin.thememinister.com/ by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 03 Apr 2018 04:45:50 GMT -->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Azad Medicine Company Admin panel</title>

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/dist/img/ico/favicon.png" type="image/x-icon">
        <!-- Start Global Mandatory Style
        =====================================================================-->
        <!-- jquery-ui css -->
        <link href="assets/plugins/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <!-- Bootstrap -->
        <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- Bootstrap rtl -->
        <!--<link href="assets/bootstrap-rtl/bootstrap-rtl.min.css" rel="stylesheet" type="text/css"/>-->
        <!-- Lobipanel css -->
        <link href="assets/plugins/lobipanel/lobipanel.min.css" rel="stylesheet" type="text/css"/>
        <!-- Pace css -->
        <link href="assets/plugins/pace/flash.css" rel="stylesheet" type="text/css"/>
        <!-- Font Awesome -->
        <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <!-- Pe-icon -->
        <link href="assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet" type="text/css"/>
        <!-- Themify icons -->
        <link href="assets/themify-icons/themify-icons.css" rel="stylesheet" type="text/css"/>
        <!-- End Global Mandatory Style
        =====================================================================-->
        <!-- Start page Label Plugins 
        =====================================================================-->
        <!-- Toastr css -->
        <link href="assets/plugins/toastr/toastr.css" rel="stylesheet" type="text/css"/>
        <!-- Emojionearea -->
        <link href="assets/plugins/emojionearea/emojionearea.min.css" rel="stylesheet" type="text/css"/>
        <!-- Monthly css -->
        <link href="assets/plugins/monthly/monthly.css" rel="stylesheet" type="text/css"/>
        <!-- End page Label Plugins 
        =====================================================================-->
        <!-- Start Theme Layout Style
        =====================================================================-->
        <!-- Theme style -->
        <link href="assets/dist/css/stylehealth.min.css" rel="stylesheet" type="text/css"/>
        <!--<link href="assets/dist/css/stylehealth-rtl.css" rel="stylesheet" type="text/css"/>-->
        <!-- End Theme Layout Style
        =====================================================================-->
        <style>
            .badge{
                background-color: black;
                font-weight: bold;
            }
        </style>
    </head>
    <body class="hold-transition sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">
            <header class="main-header">
                <a href="index.php" class="logo"> <!-- Logo -->
                    <span class="logo-mini">
                        <!--<b>A</b>H-admin-->
                        <img src="images/cropped-az-logo.png" alt="">
                    </span>
                    <span class="logo-lg">
                        <!--<b>Admin</b>H-admin-->
                        <img src="images/cropped-az-logo.png" alt="">
                    </span>
                </a>
                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top ">
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"> <!-- Sidebar toggle button-->
                        <span class="sr-only">Toggle navigation</span>
                        <span class="fa fa-tasks"></span>
                    </a>
                    
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- Orders -->
                            <li class="dropdown messages-menu">

                                <ul class="dropdown-menu">
                                    <li>
                                        <ul class="menu">

                                            <li>
                                                <a href="#" class="border-gray">
                                                    <div class="pull-left">
                                                        <img src="assets/dist/img/nocontrol.png" class="img-thumbnail" alt="User Image"></div>
                                                    <h4>Nocontrol</h4>
                                                    <p><strong>total item:</strong> 11
                                                    </p>
                                                </a> 
                                            </li>
                                            <li>
                                                <a href="#" class="border-gray">
                                                    <div class="pull-left">
                                                        <img src="assets/dist/img/lab.png" class="img-thumbnail" alt="User Image"></div>
                                                    <h4>lab</h4>
                                                    <p><strong>total item:</strong> 16
                                                    </p>
                                                </a> 
                                            </li>
                                            <li class="nav-list">
                                                <a href="#" class="border-gray">
                                                    <div class="pull-left">
                                                        <img src="assets/dist/img/therm.jpg" class="img-thumbnail" alt="User Image"></div>
                                                    <h4>Pressure machine</h4>
                                                    <p><strong>total item:</strong> 10
                                                    </p>
                                                </a> 
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#"> See all Orders <i class="fa fa-arrow-right"></i></a></li>
                                </ul>
                            </li>
                            <!-- Messages -->
                            <li class="dropdown messages-menu">
                                <ul class="dropdown-menu">

                                    <li>
                                        <ul class="menu">
                                            <li><!-- start message -->
                                                <a href="#" class="border-gray">
                                                    <div class="pull-left">
                                                        <img src="assets/dist/img/avatar2.png" class="img-thumbnail" alt="User Image"></div>
                                                    <h4>Sajad</h4>
                                                    <p>Lorem Ipsum is simply dummy text of...
                                                    </p>
                                                    <span class="label label-success pull-right">11.00am</span>
                                                </a>       

                                            </li>
                                            <li>
                                                <a href="#" class="border-gray">
                                                    <div class="pull-left">
                                                        <img src="assets/dist/img/avatar4.png" class="img-thumbnail" alt="User Image"></div>
                                                    <h4>Tanjil</h4>
                                                    <p>Lorem Ipsum is simply dummy text of...
                                                    </p>
                                                    <span class="label label-success pull-right"> 12.00am</span>
                                                </a>       

                                            </li>
                                            <li>
                                                <a href="#" class="border-gray">
                                                    <div class="pull-left">
                                                        <img src="assets/dist/img/avatar3.png" class="img-thumbnail" alt="User Image"></div>
                                                    <h4>Jahir</h4>
                                                    <p>Lorem Ipsum is simply dummy text of...
                                                    </p>
                                                    <span class="label label-success pull-right"> 10.00am</span>
                                                </a>       

                                            </li>
                                            <li>
                                                <a href="#" class="border-gray">
                                                    <div class="pull-left">
                                                        <img src="assets/dist/img/avatar4.png" class="img-thumbnail" alt="User Image"></div>
                                                    <h4>Shawon</h4>
                                                    <p>Lorem Ipsum is simply dummy text of...
                                                    </p>
                                                    <span class="label label-success pull-right"> 09.00am</span>
                                                </a>       

                                            </li>
                                            <li>
                                                <a href="#" class="border-gray">
                                                    <div class="pull-left">
                                                        <img src="assets/dist/img/avatar3.png" class="img-thumbnail" alt="User Image"></div>
                                                    <h4>Shipon</h4>
                                                    <p>Lorem Ipsum is simply dummy text of...
                                                    </p>
                                                    <span class="label label-success pull-right"> 03.00pm</span>
                                                </a>       
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">See all messages <i class=" fa fa-arrow-right"></i></a>
                                    </li>
                                </ul>
                            </li>
                            <!-- Notifications -->
                            <li class="dropdown notifications-menu">

                                <ul class="dropdown-menu">
                                    <li>
                                        <ul class="menu">
                                    </li>
                                </ul>
                            </li>
                            <li class="footer">
                                <a href="#"> See all Notifications <i class=" fa fa-arrow-right"></i></a>
                            </li>
                        </ul>
                        </li>
                        <!-- Tasks -->
                        <li class="dropdown tasks-menu">

                            <ul class="dropdown-menu">
                                <li class="header"><i class="fa fa-file"></i> 9 tasks</li>
                                <li>
                                    <ul class="menu">
                                        <li> <!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    <i class="fa fa-check-circle"></i> Data table error
                                                    <span class="label-primary label label-default pull-right">35%</span>
                                                </h3>
                                                <div class="progress">
                                                    <div class="progress-bar progress-bar-primary progress-bar-striped active" role="progressbar" aria-valuenow="35" aria-valuemin="0" aria-valuemax="100" data-toggle="tooltip" data-placement="top" data-original-title="35%" style="width: 35%">
                                                        <span class="sr-only">35% Complete (primary)</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li> <!-- end task item -->
                                        <li> <!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    <i class="fa fa-check-circle"></i>  Change theme color
                                                    <span class="label-success label label-default pull-right">55%</span>
                                                </h3>
                                                <div class="progress">
                                                    <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="55" aria-valuemin="0" aria-valuemax="100" data-toggle="tooltip" data-placement="top" data-original-title="55%" style="width: 55%">
                                                        <span class="sr-only">55% Complete (primary)</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li> <!-- end task item -->
                                        <li> <!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    <i class="fa  fa-check-circle"></i> Change the font-family 
                                                    <span class="label-info label label-default pull-right">60%</span>
                                                </h3>
                                                <div class="progress">
                                                    <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" data-toggle="tooltip" data-placement="top" data-original-title="60%" style="width: 60%">
                                                        <span class="sr-only">60% Complete (info)</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li> <!-- end task item -->
                                        <li> <!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    <i class="fa  fa-check-circle"></i> Animation should be skip
                                                    <span class="label-warning label label-default pull-right">80%</span>
                                                </h3>
                                                <div class="progress">
                                                    <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" data-toggle="tooltip" data-placement="top" data-original-title="80%"  style="width: 80%">
                                                        <span class="sr-only">80% Complete (warning)</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <!-- end task item -->
                                    </ul>
                                </li>
                                <li class="footer"><a href="#">See all tasks <i class=" fa fa-arrow-right"></i></a></li>
                            </ul>

                        </li>
                        <!-- user -->
                        <li class="dropdown dropdown-user admin-user" id="request_li">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
                                <div class="user-image">
                                    <?php
                                    $orders_q = mysqli_query($link, "select * from order_main_tbl where order_status = 0 order by order_no desc ");
                                    ?>
                                    <span class="badge"><span class="fa fa-bell fa-2x"> <?php echo mysqli_num_rows($orders_q); ?> &#x25BE;</span></span>
                                </div>
                            </a>
                            <ul class="dropdown-menu">
                                <?php
                                while ($requist_data = mysqli_fetch_array($orders_q)) {
                                    ?>
                                    <li><a href="order_details.php?order_id=<?php echo $requist_data['order_no']; ?>&aerj/adhklhy/678id=4/89kl/#"><i class=""></i>
                                            <?php
                                            $c_CODE = $requist_data['c_code'];
                                            $cus_q = mysqli_query($link, "select * from customer_tbl where c_code = '$c_CODE'");
                                            $cus_data = mysqli_fetch_array($cus_q);
                                            echo "<b>" . $cus_data['c_name'] . "</b><br>";
                                            ?>
                                            <span class="fa fa-clock-o">
                                                <?php echo $requist_data['order_date']; ?>
                                            </span>
                                        </a></li>
                                <?php } ?>
                            </ul>

                        </li>
                        <li class="dropdown dropdown-user admin-user">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
                                <div class="user-image">
                                    <img src="assets/dist/img/avatar4.png" class="img-circle" height="40" width="40" alt="User Image">
                                </div>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="update_admin.php" target="_blank"><i class="fa fa-gear"></i>Change Settings</a></li>
                                <li><a href="logout.php"><i class="fa fa-sign-out"></i> Logout</a></li>
                            </ul>
                        </li>
                        </ul>
                    </div>
                </nav>
            </header>
