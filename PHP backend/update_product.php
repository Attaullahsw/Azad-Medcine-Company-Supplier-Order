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
							if(isset($_POST['u_product'])){
								$hidden_id=$_POST['hidden_id'];
								$Product_Code=$_POST['Product_Code'];
								$Product_description=$_POST['Product_description'];
								$Product_Trade_Price=$_POST['Product_Trade_Price'];
								$query_update="update product_tbl set p_code='$Product_Code',p_description='$Product_description'
								,p_tp='$Product_Trade_Price'
								where p_code='$hidden_id'"; 
								$runqueryup = mysqli_query($link,$query_update);
								if($runqueryup){
									?>
									<div class="alert alert-success" role="alert">Product Updated Successfully</div>
									<?php
									header("refresh:2; url ='all_product.php'");
								}else
								{
									?>
									<div class="alert alert-danger" role="alert">Product Not Updated</div>
									<?php
									header("refresh:2; url ='all_product.php'");
								}
								
							}
							?>
                            <div class="panel panel-bd lobidrag">
                                <?php
									if(isset($_GET['updateid'])){
										$updateId = $_GET['updateid'];
										$updatequery ="select * from product_tbl where p_code='$updateId'";
										$runqueryupdate =mysqli_query($link,$updatequery);
										if($fetehRecord=mysqli_fetch_array($runqueryupdate)){
								?>
                                <div class="panel-body">

                                    <form action="update_product.php" method="post" class="col-sm-6">
                                        <div class="form-group">
                                            <label>Product Code</label>
                                            <input type="number" value="<?php echo $fetehRecord['p_code']; ?>" required name="Product_Code" class="form-control" placeholder="Enter Customer Code">
                                        </div>
										 <div class="form-group">
                                            <label>Product Description</label>
                                            <textarea required name="Product_description" class="form-control">
											<?php echo $fetehRecord['p_description']; ?>
											</textarea>
                                        </div>
										 <div class="form-group">
                                            <label>Product Trade Price</label>
                                            <input type="text" value="<?php echo $fetehRecord['p_tp']; ?>" required name="Product_Trade_Price" class="form-control">
                                        </div>
										<div class="form-group">
                                           <div class="form-group">
                                            <input type="submit" name="u_product" class="btn btn-success" value="Update Product" class="form-control">
                                            <input type="hidden" name="hidden_id" value="<?php echo $fetehRecord['p_code']; ?>">
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