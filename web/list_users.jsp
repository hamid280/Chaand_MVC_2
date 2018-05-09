<%@include file="include/header.jsp" %>
<%@page import="java.sql.ResultSet" %>

<table border="1">

    <th>User ID</th>
    <th>Name</th>
    <th>Email</th>

    <%
        ResultSet rs = (ResultSet) request.getAttribute("users");
        while (rs.next()) {
            out.println("<tr>");

            out.println("<td>");
            out.print(rs.getString("user_id"));
            out.println("</td>");

            out.println("<td>");
            out.print(rs.getString("name"));
            out.println("</td>");

            out.println("<td>");
            out.print(rs.getString("email"));
            out.println("</td>");

            out.println("</tr>");
        }

    %>
</table>


<%@include file="include/footer.jsp" %>
