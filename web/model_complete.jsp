<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Model complete</title>
</head>
<body>
<p>Ошибки:</p>
  <p>
      <c:forEach items="${requestScope.errors}" var="errors">
        <p>${errors}</p>
      </c:forEach>
  </p>
  <br>
  <p>Замечания:</p>
  <p>
      <c:forEach items="${requestScope.warnings}" var="warnings">
        <p>${warnings}</p>
      </c:forEach>
  </p>
  <form>
      <form method="post" action="ToDBServlet">
          <input type="submit" value="занести в БД">
      </form>
  </form>
</body>
</html>
