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
							if(isset($_POST['add_product'])){
								$Customer_Code=$_POST['Customer_Code'];
								$Customer_Description=$_POST['Customer_Description'];
								$product_Trade_Price=$_POST['product_Trade_Price'];
								$query = "insert into product_tbl(p_code,p_description,p_tp)
								values('$Customer_Code','$Customer_Description','$product_Trade_Price')";
								$runquery = mysqli_query($link,$query);
								if($runquery){
									?>
									<div class="alert alert-success" role="alert">Product Added Successfully</div>
									<?php
									header("refresh:2; url ='add_product.php'");
								}else
								{
									?>
									<div class="alert alert-danger" role="alert">Product Not Added</div>
									<?php
									header("refresh:2; url ='add_product.php'");
								}
								
							}
							?>
                            <div class="panel panel-bd lobidrag">
                                
                                <div class="panel-body">

                                    <form action="add_product.php" method="post" class="col-sm-6">
                                        <div class="form-group">
                                            <label>product Code</label>
                                            <input type="number" required name="Customer_Code" class="form-control" placeholder="Enter Customer Code">
                                        </div>
										 <div class="form-group">
                                            <label>product Trade Price</label>
                                            <input type="text" required name="product_Trade_Price" class="form-control" placeholder="Enter Customer Code">
                                        </div>
										 <div class="form-group">
                                            <label>Customer Description</label>
                                            <textarea required name="Customer_Description" class="form-control">
											</textarea>
                                        </div>
										 	
                                        <div class="form-group">
                                           <div class="form-group">
                                            <input type="submit" name="add_product" class="btn btn-success" value="Add Product" class="form-control">
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