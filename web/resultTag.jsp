<%@page pageEncoding="UTF-8"%>

<form method="post" action="ReadDataServlet">
    Начальная дата: <input type="text" id="datepicker1" name="datepicker1" required size="10"> <br>
    Конечная дата: <input type="text" id="datepicker2" name="datepicker2" required size="10"> <br>
    Порог облачности <= <input type="text" id="cloudiness" name="cloudiness" required pattern="^\d+$" value="350" size="7"> <br>
    Порог видимости <= <input type="text" id="visibility" name="visibility" required pattern="^\d+$" value="1000" size="7"> <br>
    <input type="submit" value="результат">
</form>