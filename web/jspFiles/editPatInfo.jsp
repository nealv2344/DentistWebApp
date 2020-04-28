<%-- 
    Document   : editPatInfo
    Created on : Apr 27, 2020, 5:34:05 PM
    Author     : Neal Valdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dentist Office</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--BOOTSTRAP 4-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
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
              <h4 class="card-title mt-3 text-center">Enter new Credentials for ${pId}</h4>
                <form action="..\patHandler?action=editPatInfo" Method="POST">
                    <div class="form-group input-group" id="name_input">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-user"></i>
                            </span>
                        </div>
                        <input name="FirstName" class="form-control" placeholder="First Name" required autofocus type="text">
                    </div>
                    <div class="form-group input-group" id="name_input">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-user"></i>
                            </span>
                        </div>
                        <input name="LastName" class="form-control" placeholder="Last Name" required autofocus type="text">
                    </div>
                    <div class="form-group input-group" id="email_input">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-envelope"></i>
                            </span>
                        </div>
                        <input name="Email" class="form-control" placeholder="Email address" type="email">
                    </div> 
                    <div class="form-group input-group" id="name_input">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-address-card"></i>
                            </span>
                        </div>
                        <input name="Address" class="form-control" placeholder="Address" required autofocus type="text">
                    </div>
                    <div class="form-group input-group" id="pass_input">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                        </div>
                        <input name="Password" class="form-control" placeholder="New Password" type="password">
                    </div>
                    <div class="form-group input-group" id="pass_input">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-id-card"></i> </span>
                        </div>
                        <input name="InsCo" class="form-control" placeholder="Insurance Co" type="text">
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
