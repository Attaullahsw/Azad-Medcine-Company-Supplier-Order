<?php
require_once 'header.php';
require_once './functions.php';
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

        <div class="header-title text-center">
            <h1> ORDER DETAILS </h1>
            <small> Azad Medicine Company</small>
        </div>
    </section>
    <div class="row">
        <!-- Form controls -->
        <div class="col-sm-12">
            <div class="panel panel-bd lobidrag">
                <script>
                    function printContent(content) {
                        var restorePage = document.body.innerHTML;
                        var printcontent = document.getElementById(content).innerHTML;
                        document.body.innerHTML = printcontent;
                        window.location.href = (window.location.href);
                        window.print();
                        document.body.innerHTML = restorePage;
                    }
                </script>

                <?php
                /** order status change code start here * */
                if (isset($_GET['order_id'])) {
                    $orderID = cleanInput($_GET['order_id']);
                    if (mysqli_query($link, "update order_main_tbl set order_status = 1 where order_no = '$orderID'")) {
                        
                    }
                }
                if (isset($_GET['order_id'])) {
                    $orderID = cleanInput($_GET['order_id']);
                    $orderQ = mysqli_query($link, "select order_no,c_code, order_date from order_main_tbl where order_no = '$orderID'");
                    $customer_data = mysqli_fetch_array($orderQ);
                    ?>

                    <div class="panel-body">
                        <div class="btn-group pull-right" role="group" aria-label="...">
                            <a href="index.php" type="button" class="btn btn-success">Back</a>
                            <button type="button" class="btn btn-info" onclick="printContent('print_table')">Print</button>
                        </div>
                        <?php
                        $C_CODE = $customer_data['c_code'];
                        $cus_q = mysqli_query($link, "select c_name, c_address, c_area, c_contact_no from customer_tbl where c_code = '$C_CODE' ");
                        $all_cus_data = mysqli_fetch_array($cus_q)
                        ?>
                        <div id="print_table">
                            <h4 class="text-center">
                                AZAD MEDICINE COMPANY SAMI ORDER FORM
                            </h4>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label> Customer Code : <span style="color: #888;"> <?php echo $customer_data['c_code']; ?> </span> </label>
                                </div>
                                <div class="col-sm-6">
                                    <label> Name : <span style="color: #888;"> <?php echo $all_cus_data['c_name']; ?> </span> </label>
                                </div>
                                <div class="col-sm-6">
                                    <label> Address : <span style="color: #888;">  <?php echo $all_cus_data['c_address']; ?> </span> </label>
                                </div>
                                <div class="col-sm-6">
                                    <label> Area :  <span style="color: #888;"> <?php echo $all_cus_data['c_area']; ?> </span> </label>
                                </div>
                                <div class="col-sm-6">
                                    <label> Contact : <span style="color: #888;"> <?php echo $all_cus_data['c_contact_no']; ?> </span></label>
                                </div>
                                <div class="col-sm-6">
                                    <label> Date :  <span style="color: #888;"><?php echo $customer_data['order_date']; ?> </span> </label>
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-bordered table-striped table-condensed">
                                    <thead>
                                    <th>Code</th>
                                    <th>Products</th>
                                    <th>Quantity</th>
                                    <th>Code</th>
                                    <th>Products</th>
                                    <th>Quantity</th>
                                    </thead>
                                    <tbody>
                                        <?php
                                        $orderNO = $customer_data['order_no'];
                                        $order_details_q = mysqli_query($link, "select p_code, order_details_quantity from order_details_tbl where order_no = '$orderNO'");
                                        $sno = 1;
                                        $total_price = 0;
                                        while ($order_details_data = mysqli_fetch_array($order_details_q)) {

                                            /*                                             * ** quary for product details ** */
                                            $pCODE = $order_details_data['p_code'];
                                            $product_q = mysqli_query($link, "select p_description, p_tp from product_tbl where p_code = '$pCODE'");
                                            $product_data = mysqli_fetch_array($product_q);
                                            ?>
                                            <tr>
                                                <td><?php echo $order_details_data['p_code']; ?></td>
                                                <td><?php echo $product_data['p_description']; ?></td>
                                                <td><?php echo $order_details_data['order_details_quantity']; ?></td>
                                                <?php
                                                 $subtotal = ($product_data['p_tp'] * $order_details_data['order_details_quantity']);
                                                $total_price = $total_price + $subtotal;
                                                
                                                $order_details_data = mysqli_fetch_array($order_details_q);
                                                $pCODE = $order_details_data['p_code'];
                                                $product_q = mysqli_query($link, "select p_description, p_tp from product_tbl where p_code = '$pCODE'");
                                                $product_data = mysqli_fetch_array($product_q);
                                                 ?>
                                                <td><?php echo $order_details_data['p_code']; ?></td>
                                                <td><?php echo $product_data['p_description']; ?></td>
                                                <td><?php echo $order_details_data['order_details_quantity']; ?></td>
                                                <?php
                                                $subtotal = ($product_data['p_tp'] * $order_details_data['order_details_quantity']);
                                                $total_price = $total_price + $subtotal;
                                                ?>
                                                <?php /* ?>  <td><?php
                                                  echo $product_data['p_tp'];
                                                  ?></td>
                                                 * <?php */ ?>

                                                 
                                            </tr>
                                            <?php
                                        }
                                        ?>
                                        <tr>
                                            <td colspan="5" class="text-right"><b> Total : </b> </td>
                                            <td ><b><?php echo $total_price; ?></b></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    <?php } ?>
                </div>
            </div>
        </div>
    </div>
</div>
<?php
require_once 'footer.php';
?>