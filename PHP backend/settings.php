<?php
require_once 'header.php';
?>
<!-- =============================================== -->
<!-- Content Wrapper. Contains page content -->
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
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <a href="add_product.php">
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3">
                    <div class="panel panel-bd cardbox">
                        <div class="panel-body">
                            <div class="statistic-box">
                            </div>
                            <div class="items pull-left">
                                <i class="fa fa-users fa-2x"></i>
                                <h4>Add Products</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
            <a href="all_product.php">
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3">
                    <div class="panel panel-bd cardbox">
                        <div class="panel-body">
                            <div class="statistic-box">
                                <h2><span class="count-number">
                                        <?php
                                        $quey = mysqli_query($link, 'select p_code from product_tbl');
                                        if (mysqli_num_rows($quey) > 0) {
                                            echo mysqli_num_rows($quey);
                                        } else {
                                            echo '0';
                                        }
                                        ?>
                                    </span>
                                </h2>
                            </div>
                            <div class="items pull-left">
                                <i class="fa fa-users fa-2x"></i>
                                <h4>All Products</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
            <a href="add_customer.php">
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3">
                    <div class="panel panel-bd cardbox">
                        <div class="panel-body">
                            <div class="statistic-box">

                            </div>
                            <div class="items pull-left">
                                <i class="fa fa-user-circle fa-2x"></i>
                                <h4>Add Customer</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
            <a href="all_customer.php">
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3">
                    <div class="panel panel-bd cardbox">
                        <div class="panel-body">
                            <div class="statistic-box">
                                <h2><span class="count-number">
                                        <?php
                                        $quey = mysqli_query($link, 'select c_code from customer_tbl');
                                        if (mysqli_num_rows($quey) > 0) {
                                            echo mysqli_num_rows($quey);
                                        } else {
                                            echo '0';
                                        }
                                        ?>
                                    </span>
                                </h2>
                            </div>
                            <div class="items pull-left">
                                <i class="fa fa-user-circle fa-2x"></i>
                                <h4>All Customer</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </div>

</div> <!-- /.row -->
</section> <!-- /.content -->
<?php
require_once 'footer.php';
?>