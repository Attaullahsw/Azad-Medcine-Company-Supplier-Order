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
							if(isset($_POST['u_customer'])){
								$hidden_id=$_POST['hidden_id'];
								$Customer_Code=$_POST['Customer_Code'];
								$Customer_Name=$_POST['Customer_Name'];
								$Customer_Address=$_POST['Customer_Address'];
								$Customer_Area=$_POST['Customer_Area'];
								$Customer_Contact_Number=$_POST['Customer_Contact_Number'];
								$query_update="update customer_tbl set c_code='$Customer_Code',c_name='$Customer_Name'
								,c_address='$Customer_Address',c_area='$Customer_Area',c_contact_no='$Customer_Contact_Number'
								where c_code='$hidden_id'"; 
								$runqueryup = mysqli_query($link,$query_update);
								if($runqueryup){
									?>
									<div class="alert alert-success" role="alert">Customer Updated Successfully</div>
									<?php
									header("refresh:2; url ='all_customer.php'");
								}else
								{
									?>
									<div class="alert alert-danger" role="alert">Customer Not Updated</div>
									<?php
									header("refresh:2; url ='all_customer.php'");
								}
								
							}
							?>
                            <div class="panel panel-bd lobidrag">
                                <?php
									if(isset($_GET['updateid'])){
										$updateId = $_GET['updateid'];
										$updatequery ="select * from customer_tbl where c_code='$updateId'";
										$runqueryupdate =mysqli_query($link,$updatequery);
										if($fetehRecord=mysqli_fetch_array($runqueryupdate)){
								?>
                                <div class="panel-body">

                                    <form action="update_customer.php" method="post" class="col-sm-6">
                                        <div class="form-group">
                                            <label>Customer Code</label>
                                            <input type="number" value="<?php echo $fetehRecord['c_code']; ?>" required name="Customer_Code" class="form-control" placeholder="Enter Customer Code">
                                        </div>
										 <div class="form-group">
                                            <label>Customer Name</label>
                                            <input type="text" value="<?php echo $fetehRecord['c_name']; ?>" required name="Customer_Name" class="form-control">
                                        </div>
										 <div class="form-group">
                                            <label>Customer Address</label>
                                            <input type="text" value="<?php echo $fetehRecord['c_address']; ?>" required name="Customer_Address" class="form-control">
                                        </div>
										<div class="form-group">
                                            <label>Customer Area</label>
                                            <input type="text" value="<?php echo $fetehRecord['c_area']; ?>" required name="Customer_Area" class="form-control">
                                        </div>
										<div class="form-group">
                                            <label>Customer Contact Number</label>
                                            <input type="text" value="<?php echo $fetehRecord['c_contact_no']; ?>" required name="Customer_Contact_Number" class="form-control">
                                        </div>	
                                        <div class="form-group">
                                           <div class="form-group">
                                            <input type="submit" name="u_customer" class="btn btn-success" value="Update Customer" class="form-control">
                                            <input type="hidden" name="hidden_id" value="<?php echo $fetehRecord['c_code']; ?>">
                                        </div>	 
                                          
                                      
                                   </form>
								   <?php
									}
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