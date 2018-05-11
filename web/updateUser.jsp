<%@include file="include/header.jsp"%>

<form action="${pageContext.request.contextPath}/Controller">
    Name:  <input type="text" name="name" value="${User.name}" >
    <br>
    Email: <input type="text" name="email" value="${User.email}">
    <br>
    <input type="submit" value="Update">
    <input type="hidden" name="page" value="updateUser">
    <input type="hidden" name="action" value="submit">
    <input type="hidden" name="user_id" value="${User.user_id}">
</form>


<%@include file="include/footer.jsp"%>
