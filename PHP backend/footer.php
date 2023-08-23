<aside class="main-sidebar">
    <!-- sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="image pull-left">
                <img src="assets/dist/img/avatar5.png" class="img-circle" alt="User Image">
            </div>
            <div class="info">
                <h4>Welcome</h4>
                <p>Admin</p>
            </div>
        </div>

        <!-- sidebar menu -->
        <ul class="sidebar-menu">
            <li class="active">
                <a href="settings.php" target="_blank"><i class="fa fa-gears"></i><span style="color: #fff;">Settings</span>
                </a>
            </li>
           
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-user"></i><span>Products</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="add_product.php" target="_blank">Add product</a></li>
                    <li><a href="all_product.php" target="_blank">All product</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-sitemap"></i><span>Customer</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="add_customer.php" target="_blank">Add Customer</a></li>
                    <li><a href="all_customer.php" target="_blank">All Customer</a></li>
                </ul>
            </li>
             <li class="treeview">
                <a href="android_app.php">
                    <i class="fa fa-android"></i><span>Android App</span>
                    <span class="pull-right-container">
                        <i class=""></i>
                    </span>
                </a>
            </li>



        </ul>
    </div> <!-- /.sidebar -->
</aside>



</div> <!-- /.content-wrapper -->
<footer class="main-footer">
    <strong>Copyright &copy;<a href="#">Max Tech Developers</a>.</strong> All rights reserved.
</footer>
</div> <!-- ./wrapper -->
<!-- ./wrapper -->
<!-- Start Core Plugins
=====================================================================-->
<!-- jQuery -->
<script src="assets/plugins/jQuery/jquery-1.12.4.min.js" type="text/javascript"></script>
<!-- jquery-ui --> 
<script src="assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<!-- Bootstrap -->
<script src="assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- lobipanel -->
<script src="assets/plugins/lobipanel/lobipanel.min.js" type="text/javascript"></script>
<!-- Pace js -->
<script src="assets/plugins/pace/pace.min.js" type="text/javascript"></script>
<!-- SlimScroll -->
<script src="assets/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<!-- FastClick -->
<script src="assets/plugins/fastclick/fastclick.min.js" type="text/javascript"></script>
<!-- Hadmin frame -->
<script src="assets/dist/js/custom1.js" type="text/javascript"></script>
<!-- End Core Plugins
=====================================================================-->
<!-- Start Page Lavel Plugins
=====================================================================-->
<!-- Toastr js -->
<script src="assets/plugins/toastr/toastr.min.js" type="text/javascript"></script>
<!-- Sparkline js -->
<script src="assets/plugins/sparkline/sparkline.min.js" type="text/javascript"></script>
<!-- Data maps js -->
<script src="assets/plugins/datamaps/d3.min.js" type="text/javascript"></script>
<script src="assets/plugins/datamaps/topojson.min.js" type="text/javascript"></script>
<script src="assets/plugins/datamaps/datamaps.all.min.js" type="text/javascript"></script>
<!-- Counter js -->
<script src="assets/plugins/counterup/waypoints.js" type="text/javascript"></script>
<script src="assets/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
<!-- ChartJs JavaScript -->
<script src="assets/plugins/chartJs/Chart.min.js" type="text/javascript"></script>
<script src="assets/plugins/emojionearea/emojionearea.min.js" type="text/javascript"></script>
<!-- Monthly js -->
<script src="assets/plugins/monthly/monthly.js" type="text/javascript"></script>
<!-- Data maps -->
<script src="assets/plugins/datamaps/d3.min.js" type="text/javascript"></script>
<script src="assets/plugins/datamaps/topojson.min.js" type="text/javascript"></script>
<script src="assets/plugins/datamaps/datamaps.all.min.js" type="text/javascript"></script>

<!-- End Page Lavel Plugins
=====================================================================-->
<!-- Start Theme label Script
=====================================================================-->
<!-- Dashboard js -->
<script src="assets/dist/js/custom.js" type="text/javascript"></script>

<!-- End Theme label Script
=====================================================================-->
<script>
    "use strict"; // Start of use strict
    // notification
    setTimeout(function () {
        toastr.options = {
            closeButton: true,
            progressBar: true,
            showMethod: 'slideDown',
            timeOut: 1000
        };
        //toastr.success('', 'Welcome Mr-Sajad Ali');

    }, 1300);

    //counter
    $('.count-number').counterUp({
        delay: 10,
        time: 1000
    });

    //data maps
    var basic_choropleth = new Datamap({
        element: document.getElementById("map1"),
        projection: 'mercator',
        fills: {
            defaultFill: "#009688",
            authorHasTraveledTo: "#fa0fa0"
        },
        data: {
            USA: {fillKey: "authorHasTraveledTo"},
            JPN: {fillKey: "authorHasTraveledTo"},
            ITA: {fillKey: "authorHasTraveledTo"},
            CRI: {fillKey: "authorHasTraveledTo"},
            KOR: {fillKey: "authorHasTraveledTo"},
            DEU: {fillKey: "authorHasTraveledTo"}
        }
    });

    var colors = d3.scale.category10();

    window.setInterval(function () {
        basic_choropleth.updateChoropleth({
            USA: colors(Math.random() * 10),
            RUS: colors(Math.random() * 100),
            AUS: {fillKey: 'authorHasTraveledTo'},
            BRA: colors(Math.random() * 50),
            CAN: colors(Math.random() * 50),
            ZAF: colors(Math.random() * 50),
            IND: colors(Math.random() * 50)
        });
    }, 2000);

    //bar chart
    var ctx = document.getElementById("barChart");
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
            datasets: [
                {
                    label: "My First dataset",
                    data: [65, 59, 80, 81, 56, 55, 40, 25, 35, 51, 94, 16],
                    borderColor: "#009688",
                    borderWidth: "0",
                    backgroundColor: "#009688"
                },
                {
                    label: "My Second dataset",
                    data: [28, 48, 40, 19, 86, 27, 90, 91, 41, 25, 34, 47],
                    borderColor: "#009688",
                    borderWidth: "0",
                    backgroundColor: "#009688"
                }
            ]
        },
        options: {
            scales: {
                yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
            }
        }
    });
    //radar chart
    var ctx = document.getElementById("radarChart");
    var myChart = new Chart(ctx, {
        type: 'radar',
        data: {
            labels: [["Eating", "Dinner"], ["Drinking", "Water"], "Sleeping", ["Designing", "Graphics"], "Coding", "Cycling", "Running"],
            datasets: [
                {
                    label: "My First dataset",
                    data: [65, 59, 66, 45, 56, 55, 40],
                    borderColor: "#00968866",
                    borderWidth: "1",
                    backgroundColor: "rgba(0, 150, 136, 0.35)"
                },
                {
                    label: "My Second dataset",
                    data: [28, 12, 40, 19, 63, 27, 87],
                    borderColor: "rgba(55, 160, 0, 0.7",
                    borderWidth: "1",
                    backgroundColor: "rgba(0, 150, 136, 0.35)"
                }
            ]
        },
        options: {
            legend: {
                position: 'top'
            },
            scale: {
                ticks: {
                    beginAtZero: true
                }
            }
        }
    });

    // Message
    $('.message_inner').slimScroll({
        size: '3px',
        height: '320px'
    });

    //emojionearea
    $(".emojionearea").emojioneArea({
        pickerPosition: "top",
        tonesStyle: "radio"
    });

    //monthly calender
    $('#m_calendar').monthly({
        mode: 'event',
        //jsonUrl: 'events.json',
        //dataType: 'json'
        xmlUrl: 'events.xml'
    });


    //line chart
    var ctx = document.getElementById("lineChart");
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
            datasets: [
                {
                    label: "My First dataset",
                    borderColor: "rgba(0,0,0,.09)",
                    borderWidth: "1",
                    backgroundColor: "rgba(0,0,0,.07)",
                    data: [22, 44, 67, 43, 76, 45, 12, 45, 65, 55, 42, 61, 73]
                },
                {
                    label: "My Second dataset",
                    borderColor: "#009688",
                    borderWidth: "1",
                    backgroundColor: "#009688",
                    pointHighlightStroke: "#009688",
                    data: [16, 32, 18, 26, 42, 33, 44, 24, 19, 16, 67, 71, 65]
                }
            ]
        },
        options: {
            responsive: true,
            tooltips: {
                mode: 'index',
                intersect: false
            },
            hover: {
                mode: 'nearest',
                intersect: true
            }

        }
    });


</script>

</body>

<!-- Mirrored from healthadmin.thememinister.com/ by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 03 Apr 2018 04:46:41 GMT -->
</html>

<script>
    var bflat = new Audio();
    bflat.src = "../assets/sound/pizza.mp3";
    function PlaySound() {
        bflat.play();
    }
    $(document).ready(function () {
        function startTime() {
           // alert("khan");
           
           
            var today = new Date();
            var t = setTimeout(startTime, 5000);
        }

        startTime();
    });
</script>
