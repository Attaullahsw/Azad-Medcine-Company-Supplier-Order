<?php
ob_start();
session_start();
require_once './connection.php';
?>
<table class="table table-bordered table-striped table-condensed">
    <thead>
        <tr>
            <th>S.No</th>
            <th>Customer Name</th>
            <th>Address</th>
            <th>Contact</th>
            <th>Date</th>
            <th>Details</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <?php
        if (isset($_SESSION['pagination_item_NO'])) {
            $item_no = $_SESSION['pagination_item_NO'];
        } else {
            $item_no = 25;
        }

        if ($item_no == "-1") {
            $order_q = mysqli_query($link, "select distinct order_no,order_date,c_code,order_status from order_main_tbl  order by order_date desc");
        } else {
            $order_q = mysqli_query($link, "select distinct order_no,order_date,c_code,order_status from order_main_tbl  order by order_date desc LIMIT $item_no");
        }
        if (mysqli_num_rows($order_q) <= 0) {
            ?>
            <tr>
                <td colspan="8" class="text-center">No Record Found!</td>
            </tr>
            <?php
        } else {
            $sno = 1; 
            while ($order_row = mysqli_fetch_object($order_q)) {
                ?>
                <tr  <?php if ($order_row->order_status == 0) { ?> style="background-color: #BB8FCE; font-weight: bold;" class="tr1" <?php } ?> >
                    <?php
                    $customer_q = mysqli_query($link, "select c_code, c_name, c_address, c_contact_no from customer_tbl where c_code = '$order_row->c_code'");
                    $customer_data = mysqli_fetch_object($customer_q);
                    ?>
                    <td><?php echo $sno; ?></td>
                    <td><?php echo $customer_data->c_name; ?></td>
                    <td><?php echo $customer_data->c_address; ?></td>
                    <td><?php echo $customer_data->c_contact_no; ?></td>
                    <td><?php echo $order_row->order_date; ?></td>
                    <td><a href="order_details.php?order_id=<?php echo $order_row->order_no; ?>&aerj/adhklhy/678id=4/89kl/#" class="btn btn-info btn-xs"> Details </a> </td> 
                    <td><a href="index.php?order_del_id=<?php echo $order_row->order_no; ?>" onclick="return confirm('Are You Sure To Delete This Record?')" class="btn btn-warning btn-xs"> Delete</a></td>
                </tr>
                <?php
                $sno++;
            }
        }
        ?>
    </tbody>
</table>