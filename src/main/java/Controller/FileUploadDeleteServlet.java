package Controller;

import FilePackage.FilesWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class FileUploadDeleteServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File[] files = FilesWorker.getFiles(uploadFilePath);
        if (files != null) {
            for (File file :files) {
                file.delete();
            }
        }
        request.getRequestDispatcher("IndexServlet").forward(request, response);
    }
}
