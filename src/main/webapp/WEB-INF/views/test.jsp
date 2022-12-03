<%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2022-12-02
  Time: 오전 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/test" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="전송">
    </form>

</body>
</html>
