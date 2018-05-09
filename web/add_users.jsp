<%@include file="include/header.jsp"%>

<form action="${pageContext.request.contextPath}/Controller">
    Name:  <input type="text" name="name" >
    <br>
    Email: <input type="text" name="email">
    <br>
    <input type="submit" value="Submit">
    <input type="hidden" name="page" value="submitUser">
</form>


<%@include file="include/footer.jsp"%>
