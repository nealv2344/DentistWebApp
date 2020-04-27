<%-- 
    Document   : addAppt
    Created on : Apr 26, 2020, 5:26:06 PM
    Author     : Neal Valdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--BOOTSTRAP 4-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <link href="css/bootstrap-datetimepicker.css" rel="stylesheet">
        <script src="js/bootstrap-datetimepicker.min.js"></script>
        <!--FONTS-->
        
        <!--STYLESHEETS-->
        <link rel="stylesheet" href="./stylesheets/style.css">
        <!--ICONS-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
              <h4 class="card-title mt-3 text-center">Add Appointment</h4>
                <form action="..\patHandler?action=addAppt" Method="POST">
                    <div class="form-group input-group" id="date">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-calendar"></i>
                            </span>
                        </div>
                        <input name="Date" class="form-control" placeholder="Date" required autofocus type="date">
                    </div>
                    <div class="form-group" id="dentist">  
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-clock"></i>
                            </span>
                            <input type="time" name="Time" id="default-picker" class="form-control" placeholder="Select time">   
                        </div>
                                           
                    </div>
                    <div class="form-group input-group" id="email_input">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-address-card"></i>
                            </span>
                        </div>
                        <select class="browser-default custom-select" name="Dentist">
                            <option selected disabled>Select your dentist</option>
                            <option value="D201">Bob McDonald</option>
                            <option value="D202">Susan Cassidy</option>
                            <option value="D203">Jerry York</option>
                            <option value="D204">Wayne Patterson</option>
                        </select>
                    </div> 
                    <div class="form-group input-group" id="name_input">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-tasks"></i>
                            </span>
                        </div>
                        <select class="browser-default custom-select" name="Procedure">
                            <option selected disabled>Select your Procedure</option>
                            <option value="P114">Cleaning/Exam</option>
                            <option value="P119">Xrays</option>
                            <option value="P122">Whitening</option>
                            <option value="P321">Cavity</option>
                            <option value="P650">Top Dentures</option>
                            <option value="P660">Bottom Dentures</option>
                            <option value="P780">Crown</option>
                            <option value="P790">Root Canel</option>
                        </select>
                    </div>
                    <!-- form-group// -->
                    <div class="form-group">
                        <button type="submit" class="btn btn-secondary btn-block"> Finalize  </button>
                    </div> <!-- form-group// -->      
                    
                </form>
            
          </div>
        </div>
      </div>
    </div>
  </div>
        
    </body>
</html>
