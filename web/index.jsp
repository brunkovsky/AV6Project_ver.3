<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>AV6Title</title>
    <link href="style_index.css" rel="stylesheet" type="text/css"/>
    <script>
      /**
      * @return {string}
      */
      function GetMonth(intMonth) {
        var MonthArray = ["января", "февраля", "марта",
                "апреля", "мая", "июня",
                "июля", "августа", "сентября",
                "октября", "ноября", "декабря"];
        return MonthArray[intMonth]
      }
      function getDateStr(ar) {
        var dat = new Date(ar);
        var year = dat.getYear();
        if (year < 1000) year += 1900;
        var todayStr = dat.getDate() + " " + GetMonth(dat.getMonth());
        todayStr += ", " + year + " года";
        return todayStr
      }
    </script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script>
      $(function() {
        $("#datepicker1").datepicker();
      });
    </script>
    <script>
      $(function() {
        $("#datepicker2").datepicker();
      });
    </script>
   </head>
  <body>
  АВ6:
  <br>
  Начало:
  <SCRIPT>
    document.write(getDateStr(${requestScope.DateBegin}))
  </SCRIPT>
  <br>
  Конец:
  <SCRIPT>
    document.write(getDateStr(${requestScope.DateEnd}))
  </SCRIPT>
  <br>
  <%@ include file="uploadTag.jsp" %>
  <%@ include file="resultTag.jsp" %>
  </body>
</html>
