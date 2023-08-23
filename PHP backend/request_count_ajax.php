<?php
ob_start();
session_start();
require_once './connection.php';
?>
<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
    <div class="user-image">
        <?php
        $orders_q = mysqli_query($link, "select * from order_main_tbl where order_status = 0 order by order_no desc ");
        ?>
        <span class="badge"><span class="fa fa-envelope fa-2x"> <?php echo mysqli_num_rows($orders_q); ?> &#x25BE;</span></span>
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
<?php
mysqli_close($link);
?>