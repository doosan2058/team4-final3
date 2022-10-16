<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>경고창 페이지</title>
</head>
<body>
<script type="text/javascript">
    const msg = "<c:out value = '${msg}' />";
    let url = "<c:out value = '${url}' />";
    url = url.replace('amp;', '');
    alert(msg);
    /* alert(url); */
    location.href = url;
</script>
</body>
</html>