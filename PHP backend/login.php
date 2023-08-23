<?php
ob_start();
session_start();
require_once 'connection.php';
?>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from healthadmin.thememinister.com/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 03 Apr 2018 04:47:33 GMT -->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Azad Medicine Company Admin panel</title>

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/dist/img/ico/favicon.png" type="image/x-icon">


        <!-- Bootstrap -->
        <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- Bootstrap rtl -->
        <!--<link href="assets/bootstrap-rtl/bootstrap-rtl.min.css" rel="stylesheet" type="text/css"/>-->
        <!-- Pe-icon-7-stroke -->
        <link href="assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet" type="text/css"/>
        <!-- style css -->
        <link href="assets/dist/css/stylehealth.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/dist/css/mystyle.css" rel="stylesheet" type="text/css"/>
        <!-- Theme style rtl -->
        <!--<link href="assets/dist/css/stylehealth-rtl.css" rel="stylesheet" type="text/css"/>-->
    </head>
    <body>
        <!-- Content Wrapper -->

        <div class="login-wrapper" style="opacity: 0.9;">

            <div class="container-center">

                <div class="panel panel-bd">
                    <?php
                    if (isset($_POST['login_submit'])) {
                        $username = $_POST['username'];
                        $password = $_POST['password'];
                        $quer = "select * from login_table where login_email='$username' and login_password='$password'";
                        $re = mysqli_query($link, $quer);
                        if (mysqli_num_rows($re) > 0) {
                            $_SESSION['username'] = $username;
                            $_SESSION['userpassword'] = $password;
                            header("location:index.php");
                        } else {
                            ?>
                            <div class="alert alert-info" role="alert">
                                Invalid User Name Or password....
                            </div>	
                            <?php
                            header("refresh:2;login.php");
                        }
                    }
                    ?>
                    <div class="panel-heading">
                        <div class="view-header">
                            <div class="header-icon">
                                <i class="pe-7s-unlock"></i>
                            </div>
                            <div class="header-title">
                                <h3>Login</h3>
                                <small><strong>Please enter your credentials to login.</strong></small>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="login.php">
                            <div class="form-group">
                                <label class="control-label" for="username">Email</label>
                                <input type="email" placeholder="example@gmail.com" title="Please enter you username" required value="" name="username"  class="form-control">
                                <span class="help-block small">Your unique username to app</span>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="password">Password</label>
                                <input type="password" title="Please enter your password" placeholder="******" required value="" name="password" class="form-control">
                                <span class="help-block small">Your strong password</span>
                            </div>
                            <div>
                                <input type="submit" name="login_submit" class="btn btn-primary" value="Login" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.content-wrapper -->
        <!-- jQuery -->
        <script src="assets/plugins/jQuery/jquery-1.12.4.min.js" type="text/javascript"></script>
        <!-- bootstrap js -->
        <script src="assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </body>

    <!-- Mirrored from healthadmin.thememinister.com/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 03 Apr 2018 04:47:33 GMT -->
</html>