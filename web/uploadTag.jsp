<%@page pageEncoding="UTF-8"%>

<form action="FileUploadServlet" method="post" enctype="multipart/form-data">
    Выберите файлы для загрузки:
    <input type="file" multiple accept=".xlsx" name="fileName" required>
    <br>
    <input type="submit" value="отправить">
</form>