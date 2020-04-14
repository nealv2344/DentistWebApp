<%-- 
    Document   : dentistPage
    Created on : Apr 13, 2020, 1:05:02 PM
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
        <div class="jumbotron">
            <h1 style="text-align: center">Welcome</h1>      
        </div> 
        
        <h2 style="text-align: center">Upcoming Appointments</h2>
        
        <div class="row" id="order-table-div">                
          <table id="order-table">
            <tr>
              <th>ProcCode</th>
              <th>Date/Time</th>
              <th>Patient</th>             
            </tr>                 
            <tr>                                    
                <td></td>
                <td></td>                                                  
                <td></td>
            </tr>
            
            
          </table>
        </div>
        
        
        
        
        
        
        
    </body>
</html>
