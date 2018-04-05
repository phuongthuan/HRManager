<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employees Manager</title>
        <link href="css/menu_style.css" type="text/css" rel="stylesheet" />
        <link href="css/style.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <%
        if (session.getAttribute("username") != null) {
        %>
        <div class="header">
            <br>
            <h1 align="center">Employees Manager</h1>
            <div class="menu bubplastic horizontal orange">
                <ul>
                    <li><span class="menu_r"><a href="login.jsp"><span class="menu_ar">Login</span></a></span></li>
                    <li class="highlight"><span class="menu_r"><a href="employeeManager.jsp"><span class="menu_ar">Employee Manager</span></a></span></li>
                    <li><span class="menu_r"><a href="ProcessEmployee"><span class="menu_ar">Add New Employee</span></a></span></li>
                    <li><span class="menu_r"><a href="searchEmployee.jsp"><span class="menu_ar">Search Employee</span></a></span></li>
                    <li><span class="menu_r"><a href="logout.jsp"><span class="menu_ar">logout</span></a></span></li>
                </ul>
                <br class="clearit" />
            </div>
        </div>
        <div class="content">
            <br><br>
            <table id="tb" width="100%" align="center">
                <tr>
                    <th colspan="11" height="50px"><h4>EMPLOYEE LIST</h4></th>
                </tr>
                <tr>
                    <th>Name</th>
                    <th>Bithday Date</th>
                    <th>Hire Date</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Country</th>
                    <th>Home Phone</th>
                    <th>Mobile</th>
                    <th>Email</th>
                    <th colspan="2"></th>
                </tr>
                <jsp:useBean id="ebo" class="HRManager.bol.EmployeeBO" scope="request"/>
                <%
            HRManager.entities.Employee[] arr=null;
            String option = request.getParameter("option");
            String value = request.getParameter("value");
            if (option == null || value == null) {
                arr = ebo.select();
            } else {
                if (option.equals("Name")) {
                    arr = ebo.find(0, value);
                } else if (option.equals("City")) {
                    arr = ebo.find(1, value);
                }
            }
            HRManager.ConvertData convert = new HRManager.ConvertData();
            if (arr.length > 0) {
                for (int i = 0; i < arr.length; i++) {
                    out.println("<tr><td>" + arr[i].getFirstName() + " " + arr[i].getLastName() + "</td>");
                    out.println("<td>" + convert.date2string(arr[i].getBirthDate()) + "</td>");
                    out.println("<td>" + convert.date2string(arr[i].getHireDate()) + "</td>");
                    out.println("<td>" + arr[i].getAddress() + "</td>");
                    out.println("<td>" + arr[i].getCity() + "</td>");
                    out.println("<td>" + arr[i].getCountry() + "</td>");
                    out.println("<td>" + arr[i].getHomePhone() + "</td>");
                    out.println("<td>" + arr[i].getMobile() + "</td>");
                    out.println("<td>" + arr[i].getEmail() + "</td>");
                    out.println("<td align='center'><a href='ProcessEmployee?action=edit&id=" + arr[i].getEmployeeID() + "'>Edit </a></td>");
                    out.println("<td align='center'><a href='ProcessEmployee?action=delete&id=" + arr[i].getEmployeeID() + "'> Delete</a></td></tr>");
                }
            }
                %>
            </table>
            <br>
            <%} else {
            %>
            <jsp:forward page="login.jsp"/>
            <%}%>
        </div>
    </body>
</html>