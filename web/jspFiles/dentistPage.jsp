<%-- 
    Document   : dentistPage
    Created on : Apr 13, 2020, 1:05:02 PM
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
            <a class="navbar-brand" href="#" >Welcome ${d1.getFname()}!</a>

            <!-- Links -->
            <ul class="navbar-nav justify-content-end" >
              <li class="nav-item">
                <a class="nav-link" style="color: gainsboro" href="./jspFiles/editCredentials.jsp?dentId=${d1.getId()}">Edit Credentials</a>
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
              <th>Patient</th>             
            </tr> 
            
            <c:forEach items="${apptList}" var="apptList">
            <tr>                                    
                <td>${apptList.getProcCode()}</td>
                <td>${apptList.getDateTime()}</td>                                                  
                <td>${apptList.getPatId()}</td>
            </tr>
            </c:forEach>                      
          </table>
        
        </div>
        
    </body>
</html>
