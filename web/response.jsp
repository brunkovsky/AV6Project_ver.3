<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Upload File Response</title>
    <link href="style_index.css" rel="stylesheet" type="text/css"/>
    <style>
      table {border-collapse: collapse;}
      td {border: 1px solid gray;}
    </style>
  </head>
  <body>
    <table>
      <tr>
        <th>Загруженные файлы:</th>
      </tr>
      <c:forEach items="${requestScope.fileList}" var="files">
        <tr>
          <td>${files}</td>
        </tr>
      </c:forEach>
    </table>

    <form method="post" action="upload.jsp">
      <input type="submit" value="загрузить еще">
    </form>

    <form method="post" action="FileUploadDeleteServlet">
      <input type="submit" value="удалить xlsx-файлы с сервера">
    </form>

    <form method="post" action="ModelWorkerServlet">
      <input type="submit" value="обработать файлы">
    </form>

  </body>
</html>