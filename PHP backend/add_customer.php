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
							if(isset($_POST['add_customer'])){
								$Customer_Code=$_POST['Customer_Code'];
								$Customer_Name=$_POST['Customer_Name'];
								$Customer_Address=$_POST['Customer_Address'];
								$Customer_Area=$_POST['Customer_Area'];
								$Customer_Contact_Number=$_POST['Customer_Contact_Number'];
								$query = "insert into customer_tbl(c_code,c_name,c_address,c_area,c_contact_no)
								values('$Customer_Code','$Customer_Name','$Customer_Address','$Customer_Area','$Customer_Contact_Number')";
								$runquery = mysqli_query($link,$query);
								if($runquery){
									?>
									<div class="alert alert-success" role="alert">Customer Added Successfully</div>
									<?php
									header("refresh:2; url ='add_customer.php'");
								}else
								{
									?>
									<div class="alert alert-danger" role="alert">Customer Not Added</div>
									<?php
									header("refresh:2; url ='add_customer.php'");
								}
								
							}
							?>
                            <div class="panel panel-bd lobidrag">
                                
                                <div class="panel-body">

                                    <form action="add_customer.php" method="post" class="col-sm-6">
                                        <div class="form-group">
                                            <label>Customer Code</label>
                                            <input type="number" required name="Customer_Code" class="form-control" placeholder="Enter Customer Code">
                                        </div>
										 <div class="form-group">
                                            <label>Customer Name</label>
                                            <input type="text" required name="Customer_Name" class="form-control">
                                        </div>
										 <div class="form-group">
                                            <label>Customer Address</label>
                                            <input type="text" required name="Customer_Address" class="form-control">
                                        </div>
										<div class="form-group">
                                            <label>Customer Area</label>
                                            <input type="text" required name="Customer_Area" class="form-control">
                                        </div>
										<div class="form-group">
                                            <label>Customer Contact Number</label>
                                            <input type="text" required name="Customer_Contact_Number" class="form-control">
                                        </div>	
                                        <div class="form-group">
                                           <div class="form-group">
                                            <input type="submit" name="add_customer" class="btn btn-success" value="Add Customer" class="form-control">
											<input type="reset" class="btn btn-info" value="Clear" class="form-control">
                                        </div>	 
                                          
                                      
                                   </form>
                               </div>
                           </div>
                       </div>
                       
                       
                   </div>
                   </div>
                   </div>

<?php
require_once 'footer.php';
?>