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
			 <div class="table-responsive">
							<?php
							if(isset($_GET['delid'])){
								$id = $_GET['delid'];
								$deletequery="delete from customer_tbl where c_code='$id'";
								$rundeletequery =mysqli_query($link,$deletequery);
								if($rundeletequery){
									?>
									<div class="alert alert-success" role="alert">Customer Deleted Successfully</div>
									<?php
									header("refresh:2; url ='all_customer.php'");
								}else
								{
									?>
									<div class="alert alert-danger" role="alert">Customer Not deleted__ ?</div>
									<?php
									header("refresh:2; url ='all_customer.php'");
								}
								
							}
							?>
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Customer Code No</th>
                                        <th>Customer Name</th>
                                        <th>Customer Address</th>
                                        <th>Customer Area</th>
                                        <th>Customer Contact</th>
										<th>Update</th>
										<th>Delete</th>
                                    </tr>
                                </thead>
								<?php
								$query_select_data="select * from customer_tbl";
								$run_select_query=mysqli_query($link,$query_select_data);
								while($fetch_data=mysqli_fetch_array($run_select_query)){
								?>
                                <tbody>
                                    <tr>
                                      <td>
                                       <label><?php echo $fetch_data['c_code']; ?></label>   
                                   </td>
                                   <td><?php echo $fetch_data['c_name']; ?></td>
                                   <td><?php echo $fetch_data['c_address']; ?></td>
                                   <td><?php echo $fetch_data['c_area']; ?></td>
                                   <td><?php echo $fetch_data['c_contact_no']; ?></td>
								   <td>
								   <a href="update_customer.php?updateid=<?php echo $fetch_data['c_code'];  ?>"><button class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" title="Update"><i class="fa fa-pencil" aria-hidden="true"></i></button></a>
								   </td>
                                   <td>
                                    <a href="all_customer.php?delid=<?php echo $fetch_data['c_code'];  ?>"><button onclick="return confirm('Are You Sure To Delete This record..')" class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" title="Delete "><i class="fa fa-trash-o" aria-hidden="true"></i></button></a>
                                </td>
                            </tr>
						</tbody>
						<?php
								}
								?>
</table>
</div>

<?php
require_once 'footer.php';
?>