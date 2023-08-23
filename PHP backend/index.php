<?php
require_once 'header.php';
require_once './functions.php';
?>
<!-- =============================================== -->
<?php 
if (isset($_GET['order_del_id'])) {
    $delete_id = cleanInput($_GET['order_del_id']);
    if (mysqli_query($link, "delete from order_main_tbl where  order_no = '$delete_id'")) {
        header('location:index.php');
    }
}
?>
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
            <h1> AMC</h1>
            <small> Azad Medicine Company</small>

        </div>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <?php 
                if(isset($_SESSION['pagination_item_NO'])){
                    $itemNO = $_SESSION['pagination_item_NO'];
                }else{
                    $itemNO = 2;
                }
               ?>
            <div class="text-left">
                Number of Records :  
                <select  id="select_pagination_id">
                    <option <?php if($itemNO == 25){ ?>  selected <?php } ?> >25</option>
                    <option <?php if($itemNO == 50){ ?>  selected <?php } ?> >50</option>
                    <option <?php if($itemNO == 100){ ?>  selected <?php } ?> >100</option>
                    <option <?php if($itemNO == 500){ ?>  selected <?php } ?> >500</option>
                    <option <?php if($itemNO == "-1"){ ?>  selected <?php } ?> >All</option>
                </select>
            </div>
            <br>
            <div class="table-responsive" id="request_table">
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
                    
                </tbody>
            </table>
            </div>
        </div>
</section> <!-- /.content -->
</div> <!-- /.row -->

<?php
require_once 'footer.php';
?>

<script>
    var bflat = new Audio();
    bflat.src = "sound/pizza.mp3";

    function PlaySound() {
        //    var x = document.getElementById("myAudio");
        //  var x = new Audio('../assets/sound/pizza.mp3');

        bflat.play();
        // x.play();
    }
    $(document).ready(function () {
        function startTime() {
         
            //alert("khan");
            $.ajax({
                url: "all_request_ajax.php",
                data: {},
                type: "POST",
                success: function (data) {
                    document.getElementById('request_table').innerHTML = "";
                    $("#request_table").html(data);
                }
            });


            $.ajax({
                url: "request_count_ajax.php",
                data: {},
                type: "GET",
                success: function (data) {
                    document.getElementById('request_li').innerHTML = "";
                    //alert(data);   
                    $("#request_li").html(data);
                }
            });
            $.ajax({
                url: "sound_count_ajax.php",
                data: {},
                type: "POST",
                success: function (data) {
                   // alert(data);
                    if (data != "-1") {
                        PlaySound();
                    }
                }
            });
            var today = new Date();
            var t = setTimeout(startTime, 10000);
        }

        startTime();

        /*** select pagination code starts ***/
        $('#select_pagination_id').change(function () {
            var num = $(this).val();
            $.ajax({
                url: "manage_manager_ajax.php",
                data: {paginatin_id: num},
                type: "GET",
                success: function (data) {
                    /** Exact copy of code written above**/
                    $.ajax({
                        url: "all_request_ajax.php",
                        data: {},
                        type: "POST",
                        success: function (data) {
                            document.getElementById('request_table').innerHTML = "";
                            $("#request_table").html(data);
                        }
                    });
                }
            });
        });
        /*** select pagination code ends ***/
    });
</script>
