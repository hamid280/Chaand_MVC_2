<%@include file="include/header.jsp" %>
<%@ page import="java.sql.ResultSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1">
    <th>User ID</th>
    <th>Name </th>
    <th>Email </th>

    <th>Options</th>

    <c:forEach var="temp" items="${users }">
        <c:url var="tempUrl" value="Controller">
            <c:param name="page" value="updateUser"></c:param>
            <c:param name="user_id" value="${temp.user_id}"></c:param>
        </c:url>

        <c:url var="tempUrl2" value="Controller">
            <c:param name="page" value="deleteUser"></c:param>
            <c:param name="user_id" value="${temp.user_id}"></c:param>
        </c:url>

        <tr>
            <td>${ temp.user_id} </td>
            <td>${ temp.name} </td>
            <td>${ temp.email} </td>
            <td><a href="${tempUrl}">Update</a> |
            <a href="${tempUrl2}" onclick="if(!(confirm('Are your sure you want to delete this user?'))) return false">Delete </a>
            </td>

        </tr>
    </c:forEach>
</table>

<%@include file="include/footer.jsp" %>