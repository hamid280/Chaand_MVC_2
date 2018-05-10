<%@include file="include/header.jsp" %>
<%@ page import="java.sql.ResultSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1">
    <th>User ID</th>
    <th>Name </th>
    <th>Email </th>

    <c:forEach var="temp" items="${users }">
        <tr>
            <td>${ temp.user_id} </td>
            <td>${ temp.name} </td>
            <td>${ temp.email} </td>
        </tr>
    </c:forEach>
</table>

<%@include file="include/footer.jsp" %>