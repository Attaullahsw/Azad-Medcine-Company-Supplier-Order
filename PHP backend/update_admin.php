<?php
require_once 'header.php';
?>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <form action="#" method="get" class="sidebar-form search-box pull-right hidden-md hidden-lg hidden-sm">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn"><i class="fa fa-search"></i></button>
                </span>
            </div>
        </form>   
        <div class="header-icon">
            <i class="fa fa-tachometer"></i>
        </div>
        <div class="header-title">
            <h1> Dashboard</h1>
            <small> Dashboard features</small>

        </div>

    </section>
    <div class="row">
        <!-- Form controls -->
        <div class="col-sm-12">
            <?php
            if (isset($_POST['user'])) {

                $useremail =   $_POST['useremail'];
                $userpassword = $_POST['userpassword'];
                $query_update = "update login_table set login_email='$useremail'
								,login_password='$userpassword'";
                $runqueryup = mysqli_query($link, $query_update);
                if ($runqueryup) {
                    ?>
                    <div class="alert alert-success" role="alert">Profile Updated Successfully, Next Time Log-In With New Account Details.</div>
                    <?php
                } else {
                    ?>
                    <div class="alert alert-danger" role="alert">Profile Not Updated</div>
                    <?php
                }
            }
            ?>
            <div class="panel panel-bd lobidrag">
                <?php
                $updatequery = "select * from login_table";
                $runqueryupdate = mysqli_query($link, $updatequery);
                if ($fetehRecord = mysqli_fetch_array($runqueryupdate)) {
                    ?>
                    <div class="panel-body">

                        <form action="update_admin.php" method="post" class="col-sm-6">
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" value="<?php echo $fetehRecord['login_email']; ?>" required name="useremail" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>password</label>
                                <input type="password" value="<?php echo $fetehRecord['login_password']; ?>" required name="userpassword" class="form-control">
                            </div>
                            <div class="form-group">
                                <div class="form-group">
                                    <input type="submit" name="user" class="btn btn-success" value="Update profile" class="form-control">
                                </div>	 


                        </form>
                        <?php
                    }
                    ?>
                </div>
            </div>
        </div>


    </div>
</div>
</div>

<?php
require_once 'footer.php';
?>