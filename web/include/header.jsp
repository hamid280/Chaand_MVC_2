<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>${param.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/style.css" type="text/css">
</head>
<body>
<div id="header">
    <div class="section">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/index.jsp">SEORG</a>
        </div>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/index.jsp">home</a>
            </li>
            <li >
                <a href="${pageContext.request.contextPath}/Controller?page=list_users">List Users</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/Controller?page=add_user">Add User</a>
            </li>
        </ul>
    </div>
</div>
<div id="body">