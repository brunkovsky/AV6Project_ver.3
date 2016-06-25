package Controller;

import FilePackage.FilesWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileListReaderServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File[] files = FilesWorker.getFiles(uploadFilePath);
        Arrays.sort(files);
        List<String> fileList = new ArrayList<>();
        for (File file :files) {
            fileList.add(file.getName());
        }
        request.setAttribute("fileList", fileList);
        request.getRequestDispatcher("response.jsp").forward(request, response);
    }
}
