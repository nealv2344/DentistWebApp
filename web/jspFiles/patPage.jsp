<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : patPage
    Created on : Apr 24, 2020, 4:51:49 PM
    Author     : Neal Valdez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <nav class="navbar navbar-expand-md">
            <!-- Brand/logo -->
            <a class="navbar-brand" href="#" >Welcome ${p1.getFname()}${p1.getId()}!</a>

            <!-- Links -->
            <ul class="navbar-nav justify-content-end" >
              <li class="nav-item">
                <a class="nav-link" style="color: gainsboro" href="./jspFiles/editCredentials.jsp?dentId=${p1.getId()}">Edit Credentials</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" style="color: gainsboro" href="index.html">Sign Out</a>
              </li>
            </ul>
        </nav>
        <h2 style="text-align: center">Upcoming Appointments</h2>
        
        <div class="row" id="order-table-div">                
          <table id="order-table">
            <tr>
              <th>ProcCode</th>
              <th>Date/Time</th>
              <th>Dentist</th>             
            </tr> 
            
            <tr>                                    
                <td>${a1.getProcCode()}</td>
                <td>${a1.getDateTime()}</td>                                                  
                <td>${a1.getDentId()}</td>
            </tr>                    
          </table>       
        </div> 
            <br><br>
            <div class="container" style="text-align: center">
                 
                <form action="patHandler?action=cancelAppt" method="POST">
                    <input type="hidden" name="cancelAppt" value="${p1.getId()}"> 
                    <button class="btn btn-primary" type="submit" value="Submit">Cancel Appointment</button>
                </form>
                
            </div>
    </body>
</html>

